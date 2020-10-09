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

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderItem> items;


    @Deprecated
    public Order() {

    }

    public Order(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Order(BigDecimal totalPrice, List<OrderItem> items) {
        this.totalPrice = totalPrice;
        this.items = items;
    }
}
