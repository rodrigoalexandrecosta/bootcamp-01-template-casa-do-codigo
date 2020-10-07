package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Order;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.OrderItem;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
public class CreateOrderRequest {

    private BigDecimal totalPrice;

    private List<CreateOrderItemRequest> items;


    public Order toOrder(final List<OrderItem> items) {
        return new Order(this.totalPrice, items);
    }
}
