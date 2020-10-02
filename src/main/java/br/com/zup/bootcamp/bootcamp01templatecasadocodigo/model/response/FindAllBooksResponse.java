package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.response;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
@Setter
public class FindAllBooksResponse {

    private Long id;
    private String title;
    private String synopsis;
    private String summary;
    private BigDecimal price;
    private Integer numberOfPages;
    private Long isbn;
    private LocalDate publicationDate;
    private String categoryName;
    private String authorName;
    private String authorEmail;
    private String authorDescription;

    public static FindAllBooksResponse from(final Book book) {
        return FindAllBooksResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .synopsis(book.getSynopsis())
                .summary(book.getSummary())
                .price(book.getPrice())
                .numberOfPages(book.getNumberOfPages())
                .isbn(book.getIsbn())
                .publicationDate(book.getPublicationDate())
                .categoryName(book.getCategory().getName())
                .authorName(book.getAuthor().getName())
                .authorEmail(book.getAuthor().getEmail())
                .authorDescription(book.getAuthor().getDescription())
                .build();
    }
}
