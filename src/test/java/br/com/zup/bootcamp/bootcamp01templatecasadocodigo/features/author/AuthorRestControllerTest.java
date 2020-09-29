package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.author;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request.CreateAuthorRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthorRestController.class)
public class AuthorRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthorService authorService;

    @Test
    void create() throws Exception {
        final String body = objectMapper.writeValueAsString(AuthorMock.buildCreateAuthorRequest());
        Mockito.when(authorService.create(any())).thenReturn(AuthorMock.buildAuthor());

        final ResultActions resultActions = mockMvc.perform(post("/api/v1/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body));

        resultActions.andExpect(status().isCreated()).andExpect(header().exists("Location"));
        Mockito.verify(authorService, Mockito.times(1)).create(any());
    }

    @Test
    void createWithoutOrMalformedBody() throws Exception {
        final String body = "";
        mockMvc.perform(post("/api/v1/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void createWithoutMandatoryFields() throws Exception {
        final String body = objectMapper.writeValueAsString(CreateAuthorRequest.builder().build());
        final List<String> mandatoryMessages = Stream.of(
                "message.author.name.mandatory",
                "message.author.email.mandatory",
                "message.author.description.mandatory")
                .collect(Collectors.toList());

        mockMvc.perform(post("/api/v1/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().is4xxClientError())
                .andExpect(content().json(objectMapper.writeValueAsString(mandatoryMessages)));
    }

    @Test
    void createOverCharacterLimits() throws Exception {
        CreateAuthorRequest request = AuthorMock.buildCreateAuthorRequest();
        request.setDescription(RandomString.make(501));
        final String body = objectMapper.writeValueAsString(request);

        final List<String> lengthMessages = List.of("message.author.description.length");

        mockMvc.perform(post("/api/v1/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().is4xxClientError())
                .andExpect(content().json(objectMapper.writeValueAsString(lengthMessages)));
    }

    @Test
    void createWithMalformedEmail() throws Exception {
        CreateAuthorRequest request = AuthorMock.buildCreateAuthorRequest();
        request.setEmail("email");
        final String body = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/v1/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().is4xxClientError())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of("message.author.email.invalid-format"))));
    }

    @Test
    void createWithSameEmail() throws Exception {
        final String body = objectMapper.writeValueAsString(AuthorMock.buildCreateAuthorRequest());
        when(authorService.create(any())).thenThrow(new DuplicateKeyException("message.author.email.unique"));

        mockMvc.perform(post("/api/v1/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("message.author.email.unique"));
    }
}
