package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.customer

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.localization.LocalizationMock
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.localization.LocalizationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest
@ActiveProfiles("it")
class CustomerServiceIT  extends Specification {

    @Autowired
    private CustomerService customerService

    @Autowired
    private LocalizationService localizationService

    def "Create a new customer with success"() {
        given: "I have a new customer information."
        def countryId = this.localizationService.createCountry(LocalizationMock.buildCreateCountryRequest())
        def request = CustomerMock.buildCreateCustomerRequest(countryId)

        when: "I handle this new customer to be persistent."
        def customerId = this.customerService.create(request)

        then: "The new customer is created and stored, and its id is returned."
        customerId != null
        customerId.getClass().isAssignableFrom(Long.class)
    }

    def "Try to create a new customer without country information should throw an exception"() {
        given: "I have a new customer information without a respective country information."
        def request = CustomerMock.buildCreateCustomerRequest()

        when: "I try to persist this new customer without its country information."
        def customerId = this.customerService.create(request)

        then: "The customer is not stored and an exception is thrown."
        NoSuchElementException e = thrown()
        customerId == null
    }
}
