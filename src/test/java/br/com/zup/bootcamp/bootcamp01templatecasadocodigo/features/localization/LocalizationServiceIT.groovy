package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.localization

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest
@ActiveProfiles("it")
class LocalizationServiceIT extends Specification {

    @Autowired
    private LocalizationService localizationService

    def "Create a new country with success"() {
        given: "I have a new country information."
        def request = LocalizationMock.buildCreateCountryRequest()

        when: "I handle this new country to be persistent."
        def countryId = localizationService.createCountry(request)

        then: "The new country is created, stored and returned."
        countryId != null
        countryId.getClass().isAssignableFrom(Long.class)
    }

    def "Create a new country state with success"() {
        given: "I have a new country state information."
        def countryId = localizationService.createCountry(LocalizationMock.buildCreateCountryRequest())
        def request = LocalizationMock.buildCreateCountryStateRequest(countryId)

        when: "I handle this new country state to be persistent."
        def countrySateId = localizationService.createCountryState(request)

        then: "The new country state is created, stored and returned."
        countrySateId != null
        countrySateId.getClass().isAssignableFrom(Long.class)
    }

    def "Create a new country state without an associated country should throw an exception"() {
        given: "I have a new country state information with an invalid country id."
        def request = LocalizationMock.buildCreateCountryStateRequest()

        when: "I handle this new country state to be persistent."
        def countrySateId = localizationService.createCountryState(request)

        then: "The new country state is not stored and an exception is thrown."
        IllegalArgumentException e = thrown()
        e.getMessage() == "message.country-state.country.not-found"
        countrySateId == null
    }

    def "Find a country by id"() {
        given: "I have a country id."
        def request = LocalizationMock.buildCreateCountryRequest()
        def countryId = localizationService.createCountry(request)

        when: "I try to find the country resources using this id."
        def optionalCountry = localizationService.findCountryById(countryId)

        then: "The country resources are returned."
        optionalCountry.isPresent()

        and: "All required information are filled."
        def country = optionalCountry.get()
        country.getId() == countryId
        country.getName() == request.getName().toUpperCase()
    }
}
