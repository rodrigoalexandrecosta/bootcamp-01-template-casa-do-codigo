package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.customer;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request.CreateCustomerRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerRestController.class)
public class CustomerRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerService customerService;

    @Test
    void create() throws Exception {
        final String body = objectMapper.writeValueAsString(CustomerMock.buildCreateCustomerRequest());
        Mockito.when(this.customerService.create(any())).thenReturn(new Random().nextLong());

        mockMvc.perform(post("/api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    void createWithoutOrMalformedBody() throws Exception {
        final String body = "";
        mockMvc.perform(post("/api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void createWithoutMandatoryFields() throws Exception {
        final String body = objectMapper.writeValueAsString(CreateCustomerRequest.builder().build());
        final List<String> mandatoryMessages = Stream.of(
                "message.customer.email.mandatory",
                "message.customer.first-name.mandatory",
                "message.customer.last-name.mandatory",
                "message.customer.social-identity.mandatory",
                "message.customer.zip-code.mandatory",
                "message.customer.address.mandatory",
                "message.customer.complement.mandatory",
                "message.customer.city.mandatory",
                "message.customer.phone.mandatory",
                "message.customer.country-id.mandatory")
                .collect(Collectors.toList());

        mockMvc.perform(post("/api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().is4xxClientError())
                .andExpect(content().json(objectMapper.writeValueAsString(mandatoryMessages)));
    }

    @Test
    void createWithMalformedEmail() throws Exception {
        final CreateCustomerRequest request = CustomerMock.buildCreateCustomerRequest();
        request.setEmail("email");
        final String body = objectMapper.writeValueAsString(request);

        final String malformedEmail = "message.customer.email.invalid-format";

        mockMvc.perform(post("/api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().is4xxClientError())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(malformedEmail))));
    }

    @Test
    void createWithInvalidSocialIdentity() throws Exception {
        final CreateCustomerRequest request = CustomerMock.buildCreateCustomerRequest();
        request.setSocialIdentity("123");
        final String body = objectMapper.writeValueAsString(request);

        final String malformedEmail = "message.customer.social-identity.invalid-format";

        mockMvc.perform(post("/api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().is4xxClientError())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(malformedEmail))));
    }

}
