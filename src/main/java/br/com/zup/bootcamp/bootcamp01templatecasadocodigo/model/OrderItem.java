package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

    @ManyToOne
    private Order order;

    @Deprecated
    public OrderItem() {

    }

    public OrderItem(Book book, Integer quantity) {
        this.book = book;
        this.quantity = quantity;
    }
}
