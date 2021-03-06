package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Book;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Order;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.OrderItem;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@Builder
public class CreateOrderItemRequest {

    @NotNull(message = "message.order-item.book-id.mandatory")
    private Long bookId;

    @NotNull(message = "message.order-item.quantity.mandatory")
    @Positive(message = "message.order-item.quantity.positive-value")
    private Integer quantity;

    public OrderItem toOrderItem(final Order order, final Book book) {
        return new OrderItem(order, book, this.quantity);
    }
}
