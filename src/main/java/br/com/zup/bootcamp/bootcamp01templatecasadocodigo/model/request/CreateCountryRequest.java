package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Country;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateCountryRequest {

    @NotBlank(message = "message.country.name.mandatory")
    private String name;


    @Deprecated
    public CreateCountryRequest() {

    }

    public CreateCountryRequest(String name) {
        this.name = name;
    }

    public Country toCountry() {
        return new Country(this.name.toUpperCase());
    }
}
