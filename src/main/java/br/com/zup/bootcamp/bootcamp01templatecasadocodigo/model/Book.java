package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request.CreateBookRequest;
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

    @Column(nullable = false)
    private String synopsis;

    @Column
    private String summary;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer numberOfPages;

    @Column(nullable = false)
    private Long isbn;

    @Column(nullable = false)
    private LocalDate publicationDate;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "authorId")
    private Author author;

    public static Book from(final CreateBookRequest request) {
        return Book.builder()
                .title(request.getTitle())
                .synopsis(request.getSynopsis())
                .summary(request.getSummary())
                .price(request.getPrice())
                .numberOfPages(request.getNumberOfPages())
                .isbn(request.getIsbn())
                .publicationDate(request.getPublicationDate())
                .category(request.getCategory())
                .author(request.getAuthor())
                .build();
    }
}
