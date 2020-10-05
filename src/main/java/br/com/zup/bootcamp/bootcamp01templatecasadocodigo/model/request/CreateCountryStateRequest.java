package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Country;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.CountryState;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class CreateCountryStateRequest {

    @NotBlank(message = "message.country-state.name.mandatory")
    private String name;

    @NotNull(message = "message.country-state.country.mandatory")
    private Long countryId;

    @Deprecated
    public CreateCountryStateRequest() {

    }

    public CreateCountryStateRequest(String name, Long countryId) {
        this.name = name;
        this.countryId = countryId;
    }

    public CountryState toCountryState(Country country) {
        return new CountryState(this.name.toUpperCase(), country);
    }
}
