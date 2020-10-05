package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.localization;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Random;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LocalizationRestController.class)
public class LocalizationRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LocalizationService localizationService;

    @Test
    void createCountry() throws Exception {
        final String body = objectMapper.writeValueAsString(LocalizationMock.buildCreateCountryRequest());
        Mockito.when(this.localizationService.createCountry(any())).thenReturn(new Random().nextLong());

        mockMvc.perform(post("/api/v1/localization/countries")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));

        Mockito.verify(this.localizationService, Mockito.times(1)).createCountry(any());
    }

    @Test
    void createCountryState() throws Exception {
        final String body = objectMapper.writeValueAsString(LocalizationMock.buildCreateCountryStateRequest());
        Mockito.when(this.localizationService.createCountry(any())).thenReturn(new Random().nextLong());

        mockMvc.perform(post("/api/v1/localization/country-states")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));

        Mockito.verify(this.localizationService, Mockito.times(1)).createCountryState(any());
    }
}
