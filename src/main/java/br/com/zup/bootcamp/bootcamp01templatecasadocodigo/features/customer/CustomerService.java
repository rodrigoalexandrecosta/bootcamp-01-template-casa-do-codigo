package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.customer;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.localization.LocalizationService;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Country;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.CountryState;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Customer;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request.CreateCustomerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final LocalizationService localizationService;

    @Transactional
    public Long create(final CreateCustomerRequest request) {

        final Country country = this.localizationService.findCountryById(request.getCountryId())
                .orElseThrow();

        final CountryState state = country.stateBelongsToCountry(request.getCountryStateId()) ?
                this.localizationService.findCountryStateById(request.getCountryStateId()).orElse(null)
                :
                null;

        final Customer customer = this.customerRepository.save(request.toCostumer(country, state));
        return customer.getId();
    }
}
