package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.customer;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request.CreateCustomerRequest;
import net.bytebuddy.utility.RandomString;

import java.util.Random;

public final class CustomerMock {

    private CustomerMock() {
        throw new IllegalStateException("Class not meant for instantiation.");
    }

    public static CreateCustomerRequest buildCreateCustomerRequest() {
        return CreateCustomerRequest.builder()
                .email(String.format("beth@%s.com", RandomString.make(10)))
                .firstName("Beth")
                .lastName("Gibbons")
                .socialIdentity("90801823463")
                .zipCode("1234567")
                .address("Downtown st.")
                .complement("A")
                .city("Bristol")
                .phone("19987654321")
                .countryId(123L)
                .build();
    }

    public static CreateCustomerRequest buildCreateCustomerRequest(final Long countryId) {
        return CreateCustomerRequest.builder()
                .email(String.format("beth@%s.com", RandomString.make(10)))
                .firstName("Beth")
                .lastName("Gibbons")
                .socialIdentity("90801823463")
                .zipCode("1234567")
                .address("Downtown st.")
                .complement("A")
                .city("Bristol")
                .phone("19987654321")
                .countryId(countryId)
                .build();
    }
}
