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
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    private Author author;

    public static Book from(final CreateBookRequest request, final Author author, final Category category) {
        return Book.builder()
                .title(request.getTitle())
                .synopsis(request.getSynopsis())
                .summary(request.getSummary())
                .price(request.getPrice())
                .numberOfPages(request.getNumberOfPages())
                .isbn(request.getIsbn())
                .publicationDate(request.getPublicationDate())
                .author(author)
                .category(category)
                .build();
    }
}
