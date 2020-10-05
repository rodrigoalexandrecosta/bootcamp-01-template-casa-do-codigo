package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.book;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request.CreateBookRequest;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.response.BookDetailsByIdResponse;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.response.FindAllBooksResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookRestController.class)
public class BookRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookService bookService;

    @Test
    void create() throws Exception {
        final String body = objectMapper.writeValueAsString(BookMock.buildCreateBookRequest());
        Mockito.when(bookService.create(any())).thenReturn(new Random().nextLong());

        final ResultActions resultActions = mockMvc.perform(post("/api/v1/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body));

        resultActions.andExpect(status().isCreated()).andExpect(header().exists("Location"));
        Mockito.verify(bookService, Mockito.times(1)).create(any());
    }

    @Test
    void createWithoutOrMalformedBody() throws Exception {
        final String body = "";
        mockMvc.perform(post("/api/v1/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void createWithoutMandatoryFields() throws Exception {
        final String body = objectMapper.writeValueAsString(CreateBookRequest.builder().build());
        final List<String> mandatoryMessages = Stream.of(
                "message.book.title.mandatory",
                "message.book.synopsis.mandatory",
                "message.book.price.mandatory",
                "message.book.number-of-pages.mandatory",
                "message.book.isbn.mandatory",
                "message.book.publication-date.mandatory",
                "message.book.category-id.mandatory",
                "message.book.author-id.mandatory")
                .collect(Collectors.toList());

        mockMvc.perform(post("/api/v1/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().is4xxClientError())
                .andExpect(content().json(objectMapper.writeValueAsString(mandatoryMessages)));
    }

    @Test
    void createOverCharacterLimits() throws Exception {
        CreateBookRequest request = BookMock.buildCreateBookRequest();
        request.setSynopsis(RandomString.make(501));
        final String body = objectMapper.writeValueAsString(request);

        final List<String> lengthMessages = List.of("message.book.synopsis.length");

        mockMvc.perform(post("/api/v1/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().is4xxClientError())
                .andExpect(content().json(objectMapper.writeValueAsString(lengthMessages)));
    }

    @Test
    void createUnderMinimumValue() throws Exception {
        CreateBookRequest request = BookMock.buildCreateBookRequest();
        request.setPrice(new BigDecimal("19.90"));
        request.setNumberOfPages(99);
        final String body = objectMapper.writeValueAsString(request);

        final List<String> minimumValueMessages = List.of(
                "message.book.price.min-value", "message.book.number-of-pages.min-value");

        mockMvc.perform(post("/api/v1/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().is4xxClientError())
                .andExpect(content().json(objectMapper.writeValueAsString(minimumValueMessages)));
    }

    @Test
    void createWithPastOrPresentDate() throws Exception {
        CreateBookRequest request = BookMock.buildCreateBookRequest();
        request.setPublicationDate(LocalDate.now());
        final String body = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/v1/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().is4xxClientError())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of("message.book.publication-date.future-date"))));
    }

    @Test
    void findBookDetailsById() throws Exception {
        final BookDetailsByIdResponse response = BookMock.buildDetailsResponse();

        when(this.bookService.findDetailsById(any())).thenReturn(Optional.of(response));

        mockMvc.perform(get("/api/v1/books/" + new Random().nextLong())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    void findAll() throws Exception {
        final FindAllBooksResponse response1 = BookMock.buildFindAllBooksResponse();
        final FindAllBooksResponse response2 = BookMock.buildFindAllBooksResponse();

        when(this.bookService.findAll()).thenReturn(List.of(response1, response2));

        mockMvc.perform(get("/api/v1/books/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(response1, response2))));
    }

}
