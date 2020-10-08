package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.order;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.book.BookService;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Book;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Order;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.OrderItem;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request.CreateOrderItemRequest;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request.CreateOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final BookService bookService;

    @Transactional
    public Long createOrder(final CreateOrderRequest request) {
        final List<OrderItem> items = this.getOrderItems(request.getItems());
        this.orderItemRepository.saveAll(items);
        final Order order = this.orderRepository.save(request.toOrder(items));
        return order.getId();
    }

    private List<OrderItem> getOrderItems(final List<CreateOrderItemRequest> itemsRequest) {
        return itemsRequest.stream()
                .map(itemRequest -> {
                    final Book book = this.bookService.findById(itemRequest.getBookId())
                            .orElseThrow(() -> new IllegalArgumentException("message.book.not-found"));

                    return itemRequest.toOrderItem(book);
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteAllOrderItems() {
        this.orderItemRepository.deleteAll();
    }
}
