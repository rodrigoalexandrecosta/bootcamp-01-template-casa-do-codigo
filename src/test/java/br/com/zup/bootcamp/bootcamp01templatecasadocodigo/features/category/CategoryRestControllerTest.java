package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.category;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request.CreateCategoryRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryRestController.class)
public class CategoryRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CategoryService categoryService;

    @Test
    void create() throws Exception {
        final String body = objectMapper.writeValueAsString(CategoryMock.buildCreateCategoryRequest());
        Mockito.when(categoryService.create(any())).thenReturn(CategoryMock.buildCategory());

        final ResultActions resultActions = mockMvc.perform(post("/api/v1/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body));

        resultActions.andExpect(status().isCreated()).andExpect(header().exists("Location"));
        Mockito.verify(categoryService, Mockito.times(1)).create(any());
    }

    @Test
    void createWithoutOrMalformedBody() throws Exception {
        final String body = "";
        mockMvc.perform(post("/api/v1/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void createWithoutMandatoryFields() throws Exception {
        final String body = objectMapper.writeValueAsString(CreateCategoryRequest.builder().build());
        final List<String> mandatoryMessages = List.of("message.category.name.mandatory");

        mockMvc.perform(post("/api/v1/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().is4xxClientError())
                .andExpect(content().json(objectMapper.writeValueAsString(mandatoryMessages)));
    }
}
