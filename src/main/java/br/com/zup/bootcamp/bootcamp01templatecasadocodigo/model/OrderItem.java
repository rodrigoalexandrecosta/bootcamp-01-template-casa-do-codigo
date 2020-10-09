package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private Book book;

    private Integer quantity;

    private BigDecimal unitaryPrice;

    //    private BigDecimal totalItemPrice = this.unitaryPrice.multiply(new BigDecimal(this.quantity));
    private BigDecimal totalItemPrice;

    @ManyToOne
    private Order order;


    @Deprecated
    public OrderItem() {

    }

    public OrderItem(Order order, Book book, Integer quantity) {
        this.order = order;
        this.book = book;
        this.quantity = quantity;
        this.unitaryPrice = book.getPrice();
        this.totalItemPrice = this.unitaryPrice.multiply(new BigDecimal(this.quantity));
    }
}
