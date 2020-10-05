package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.localization;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request.CreateCountryRequest;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request.CreateCountryStateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/localization", produces = APPLICATION_JSON_VALUE)
public class LocalizationRestController {

    private final LocalizationService localizationService;

    @PostMapping(value = "/countries", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCountry(@RequestBody @Valid CreateCountryRequest request) {
        final Long id = this.localizationService.createCountry(request);
        return ResponseEntity.created(URI.create(String.format("/countries/%s", id))).build();
    }

    @PostMapping(value = "/country-states", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCountryState(@RequestBody @Valid CreateCountryStateRequest request) {
        final Long id = this.localizationService.createCountryState(request);
        return ResponseEntity.created(URI.create(String.format("/country-states/%s", id))).build();
    }
}
