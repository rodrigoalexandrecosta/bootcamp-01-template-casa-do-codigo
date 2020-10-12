package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CreateDiscountCouponRequest {

    @NotBlank(message = "message.coupon.code.mandatory")
    private String code;

    @NotNull(message = "message.coupon.discount-percentage.mandatory")
    @Positive(message = "message.coupon.discount-percentage.positive-value")
    private BigDecimal discountPercentage;

    @FutureOrPresent(message = "message.coupon.valid-until.future-or-present-date")
    private LocalDate validUntil;
}
