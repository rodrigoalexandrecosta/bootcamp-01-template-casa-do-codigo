package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private BigDecimal totalPrice;

    private BigDecimal totalPriceWithDiscount;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderItem> items;

    @OneToOne
    private Customer customer;

    @ManyToOne
    private DiscountCoupon discountCoupon;

    @Deprecated
    public Order() {

    }

    public Order(BigDecimal totalPrice, Customer customer) {
        this.totalPrice = totalPrice;
        this.customer = customer;
    }

    public Order(BigDecimal totalPrice, Customer customer, DiscountCoupon discountCoupon) {
        this.totalPrice = totalPrice;
        this.customer = customer;
        this.discountCoupon = discountCoupon;
    }


}
