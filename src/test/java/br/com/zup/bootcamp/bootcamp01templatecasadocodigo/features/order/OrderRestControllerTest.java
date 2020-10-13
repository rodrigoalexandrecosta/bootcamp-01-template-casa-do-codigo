package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.order;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request.CreateOrderRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderRestController.class)
public class OrderRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OrderService orderService;

    @Test
    void create() throws Exception {
        final String body = objectMapper.writeValueAsString(OrderMock.buildCreateOrderRequest());
        Mockito.when(this.orderService.create(any())).thenReturn(new Random().nextLong());

        mockMvc.perform(post("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    void createWithoutOrMalformedBody() throws Exception {
        final String body = "";
        mockMvc.perform(post("/api/v1/localization/countries")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void createWithoutMandatoryFields() throws Exception {
        final String body = objectMapper.writeValueAsString(CreateOrderRequest.builder().build());
        final List<String> mandatoryMessages = List.of("message.order.total-price.mandatory",
                "message.order.items.mandatory", "message.order.customer-id.mandatory");

        mockMvc.perform(post("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().is4xxClientError())
                .andExpect(content().json(objectMapper.writeValueAsString(mandatoryMessages)));
    }

    @Test
    void createWithNegativeTotalPrice() throws Exception {
        final CreateOrderRequest request = OrderMock.buildCreateOrderRequest();
        request.setTotalPrice(new BigDecimal("-50"));
        final String body = objectMapper.writeValueAsString(request);

        final String invalidValueMessage = "message.order.total-price.positive-value";

        mockMvc.perform(post("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().is4xxClientError())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(invalidValueMessage))));
    }

    @Test
    void createOrderItemsWithoutBookId() throws Exception {
        final CreateOrderRequest request = OrderMock.buildCreateOrderRequest();
        final List<String> mandatoryMessages = new ArrayList<>();

        request.getItems().forEach(item -> {
            item.setBookId(null);
            mandatoryMessages.add("message.order-item.book-id.mandatory");
        });

        final String body = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().is4xxClientError())
                .andExpect(content().json(objectMapper.writeValueAsString(mandatoryMessages)));
    }

    @Test
    void createOrderItemsWithoutQuantity() throws Exception {
        final CreateOrderRequest request = OrderMock.buildCreateOrderRequest();
        final List<String> mandatoryMessages = new ArrayList<>();

        request.getItems().forEach(item -> {
            item.setQuantity(null);
            mandatoryMessages.add("message.order-item.quantity.mandatory");
        });

        final String body = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().is4xxClientError())
                .andExpect(content().json(objectMapper.writeValueAsString(mandatoryMessages)));
    }

    @Test
    void createOrderItemsWithNegativeQuantity() throws Exception {
        final CreateOrderRequest request = OrderMock.buildCreateOrderRequest();
        final List<String> positiveValueMessage = new ArrayList<>();

        request.getItems().forEach(item -> {
            item.setQuantity(-1);
            positiveValueMessage.add("message.order-item.quantity.positive-value");
        });

        final String body = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().is4xxClientError())
                .andExpect(content().json(objectMapper.writeValueAsString(positiveValueMessage)));
    }


}
