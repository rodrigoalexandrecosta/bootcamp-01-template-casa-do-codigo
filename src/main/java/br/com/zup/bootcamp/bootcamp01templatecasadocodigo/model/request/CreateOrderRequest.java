package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Customer;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.DiscountCoupon;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class CreateOrderRequest {

    @NotNull(message = "message.order.total-price.mandatory")
    @Positive(message = "message.order.total-price.positive-value")
    private BigDecimal totalPrice;

    @NotNull(message = "message.order.items.mandatory")
    @Valid
    private List<CreateOrderItemRequest> items;

    @NotNull(message = "message.order.customer-id.mandatory")
    private Long customerId;

    private String discountCouponCode;


    public Order toOrder(Customer customer, DiscountCoupon discountCoupon) {
        if (discountCoupon == null) {
            return new Order(this.totalPrice, this.totalPrice, customer);
        }

        if (discountCoupon.getValidUntil().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("message.coupon.valid-until.future-or-present-date");
        }

        final BigDecimal decimalDiscount = BigDecimal.ONE
                .subtract((discountCoupon.getDiscountPercentage())
                .setScale(2, RoundingMode.HALF_UP)
                .divide(new BigDecimal("100"), new MathContext(5, RoundingMode.HALF_UP)));

        final BigDecimal totalDiscount = this.totalPrice.multiply(decimalDiscount);

        return new Order(this.totalPrice, this.totalPrice.subtract(totalDiscount), customer, discountCoupon);
    }
}
