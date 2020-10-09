package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.order;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request.CreateOrderItemRequest;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request.CreateOrderRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class OrderMock {

    private OrderMock() {
        throw new IllegalStateException("Class not meant for instantiation.");
    }

    public static CreateOrderRequest buildCreateOrderRequest() {
        return CreateOrderRequest.builder()
                .items(buildListOfCreateOrderItemRequest())
                .totalPrice(new BigDecimal("299.90"))
                .customerId(new Random().nextLong())
                .build();
    }

    public static CreateOrderRequest buildCreateOrderRequest(Long bookId, Long customerId) {
        return CreateOrderRequest.builder()
                .items(buildListOfCreateOrderItemRequest(bookId))
                .totalPrice(new BigDecimal("299.90"))
                .customerId(customerId)
                .build();
    }

    public static CreateOrderItemRequest buildCreateOrderItemRequest() {
        return CreateOrderItemRequest.builder()
                .bookId(new Random().nextLong())
                .quantity(new Random().nextInt(15) + 1)
                .build();
    }

    private static CreateOrderItemRequest buildCreateOrderItemRequest(Long bookId) {
        return CreateOrderItemRequest.builder()
                .bookId(bookId)
                .quantity(new Random().nextInt(15) + 1)
                .build();
    }

    public static List<CreateOrderItemRequest> buildListOfCreateOrderItemRequest() {
        final int numberOfItems = new Random().nextInt(10) + 1;
        List<CreateOrderItemRequest> items = new ArrayList<>();
        for(int i = 0; i <= numberOfItems; i++) {
            CreateOrderItemRequest item = buildCreateOrderItemRequest();
            items.add(item);
        }
        return items;
    }

    public static List<CreateOrderItemRequest> buildListOfCreateOrderItemRequest(Long bookId) {
        final int numberOfItems = new Random().nextInt(10) + 1;
        List<CreateOrderItemRequest> items = new ArrayList<>();
        for(int i = 0; i <= numberOfItems; i++) {
            CreateOrderItemRequest item = buildCreateOrderItemRequest(bookId);
            items.add(item);
        }
        return items;
    }
}
