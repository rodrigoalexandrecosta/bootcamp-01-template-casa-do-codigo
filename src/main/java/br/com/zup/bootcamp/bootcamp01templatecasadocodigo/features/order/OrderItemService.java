package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.order;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.book.BookService;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Book;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Order;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.OrderItem;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request.CreateOrderItemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final BookService bookService;

    @Transactional
    public void create(final Order order, final List<CreateOrderItemRequest> itemsRequests) {
        List<OrderItem> items = this.buildOrderItems(order, itemsRequests);
        this.orderItemRepository.saveAll(items);
    }

    private List<OrderItem> buildOrderItems(final Order order, final List<CreateOrderItemRequest> itemsRequests) {
        return itemsRequests.stream()
                .map(itemRequest -> {
                    final Book book = this.bookService.findById(itemRequest.getBookId())
                            .orElseThrow(() -> new IllegalArgumentException("message.book.not-found"));

                    return itemRequest.toOrderItem(order, book);
                })
                .collect(Collectors.toList());
    }

}
