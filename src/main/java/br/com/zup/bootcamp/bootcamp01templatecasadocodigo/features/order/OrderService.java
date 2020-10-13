package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.order;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.customer.CustomerService;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Customer;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.DiscountCoupon;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Order;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request.CreateOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;
    private final CustomerService customerService;
    private final DiscountCouponService discountCouponService;

    @Transactional
    public Long create(final CreateOrderRequest request) {
        final Customer customer = this.getCustomer(request.getCustomerId());
        final DiscountCoupon discountCoupon = this.getDiscountCoupon(request.getDiscountCouponCode());
        final Order order = this.orderRepository.save(request.toOrder(customer, discountCoupon));
        this.orderItemService.create(order, request.getItems());
        return order.getId();
    }

    public Optional<Order> findById(final Long orderId) {
        return this.orderRepository.findById(orderId);
    }

    private Customer getCustomer(final Long customerId) {
        return this.customerService.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("message.customer.not-found"));
    }

    private DiscountCoupon getDiscountCoupon(final String couponCode) {
        return this.discountCouponService.findByCode(couponCode)
                .orElse(null);
//                .orElseGet(() -> DiscountCoupon.builder()
//                        .discountPercentage(BigDecimal.ZERO)
//                        .build());
    }

}
