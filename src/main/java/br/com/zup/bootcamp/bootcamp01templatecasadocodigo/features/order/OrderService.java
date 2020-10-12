package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.order;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.book.BookService;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.customer.CustomerService;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.*;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request.CreateOrderItemRequest;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request.CreateOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final DiscountCouponRepository discountCouponRepository;
    private final BookService bookService;
    private final CustomerService customerService;

    @Transactional
    public Long createOrder(final CreateOrderRequest request) {

//        DiscountCoupon discountCoupon = this.discountCouponRepository.findByCode(request.getDiscountCouponCode())
//                .orElse(null);

        final Customer customer = this.customerService.findById(request.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("message.customer.not-found"));

        final Order order = this.orderRepository.save(request.toOrder(customer));
        final List<OrderItem> items = this.getOrderItems(order, request.getItems());
        this.orderItemRepository.saveAll(items);
        return order.getId();
    }

    public Optional<Order> findById(final Long orderId) {
        return this.orderRepository.findById(orderId);
    }

    private List<OrderItem> getOrderItems(final Order order, final List<CreateOrderItemRequest> itemsRequest) {
        return itemsRequest.stream()
                .map(itemRequest -> {
                    final Book book = this.bookService.findById(itemRequest.getBookId())
                            .orElseThrow(() -> new IllegalArgumentException("message.book.not-found"));

                    return itemRequest.toOrderItem(order, book);
                })
                .collect(Collectors.toList());
    }

    private void applyDiscount(final BigDecimal totalPrice, final BigDecimal discountPercentage) {

    }
}
