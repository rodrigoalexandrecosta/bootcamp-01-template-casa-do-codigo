package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.localization;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Country;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.CountryState;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request.CreateCountryRequest;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request.CreateCountryStateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocalizationService {

    private final CountryRepository countryRepository;
    private final CountryStateRepository countryStateRepository;

    @Transactional
    public Long createCountry(final CreateCountryRequest request) {
        final Country country = this.countryRepository.save(request.toCountry());
        return country.getId();
    }

    @Transactional
    public Long createCountryState(final CreateCountryStateRequest request) {
        final Optional<Country> optionalCountry = this.findCountryById(request.getCountryId());
        if (optionalCountry.isEmpty()) {
            throw new IllegalArgumentException("message.country-state.country.not-found");
        }

        final CountryState countryState = this.countryStateRepository.save(request.toCountryState(optionalCountry.get()));
        return countryState.getId();
    }

    public Optional<Country> findCountryById(final Long countryId) {
        return this.countryRepository.findById(countryId);
    }
}
