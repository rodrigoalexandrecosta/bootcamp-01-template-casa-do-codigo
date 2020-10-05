package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.localization;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request.CreateCountryRequest;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request.CreateCountryStateRequest;
import net.bytebuddy.utility.RandomString;

import java.util.Random;

public final class LocalizationMock {

    private LocalizationMock() {
        throw new IllegalStateException("Class not meant for instantiation");
    }

    public static CreateCountryRequest buildCreateCountryRequest() {
        return new CreateCountryRequest(RandomString.make(25));
    }

    public static CreateCountryStateRequest buildCreateCountryStateRequest() {
        return new CreateCountryStateRequest(RandomString.make(25), new Random().nextLong());
    }

    public static CreateCountryStateRequest buildCreateCountryStateRequest(final Long countryId) {
        return new CreateCountryStateRequest(RandomString.make(25), countryId);
    }
}
