package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Order;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.OrderItem;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
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


    public Order toOrder(final List<OrderItem> items) {
        return new Order(this.totalPrice, items);
    }
}
