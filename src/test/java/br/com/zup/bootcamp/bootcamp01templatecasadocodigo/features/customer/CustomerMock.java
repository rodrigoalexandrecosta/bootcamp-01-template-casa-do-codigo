package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.customer;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request.CreateCustomerRequest;

public final class CustomerMock {

    private CustomerMock() {
        throw new IllegalStateException("Class not meant for instantiation.");
    }

    public static CreateCustomerRequest buildCreateCustomerRequest() {
        return CreateCustomerRequest.builder()
                .build();
    }
}
