package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 500)
    private String synopsis;

    @Column
    private String summary;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer numberOfPages;

    @Column(nullable = false, unique = true)
    private Long isbn;

    @Column(nullable = false)
    private LocalDate publicationDate;

    @ManyToOne
//    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Category category;

    @ManyToOne
//    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    private Author author;
}
