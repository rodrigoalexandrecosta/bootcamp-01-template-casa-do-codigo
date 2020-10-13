package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.order;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request.CreateDiscountCouponRequest;

import java.math.BigDecimal;
import java.time.LocalDate;

public final class DiscountCouponMock {

    private DiscountCouponMock() {
        throw new IllegalStateException("Class not meant for instantiation.");
    }

    public static CreateDiscountCouponRequest buildCreateDiscountCouponRequest() {
        return CreateDiscountCouponRequest.builder()
                .code("PROMO10")
                .discountPercentage(new BigDecimal("10.00"))
                .validUntil(LocalDate.now())
                .build();
    }
}
