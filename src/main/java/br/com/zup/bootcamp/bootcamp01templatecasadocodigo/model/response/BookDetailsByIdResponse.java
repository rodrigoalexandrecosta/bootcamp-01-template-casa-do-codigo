package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.response;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class BookDetailsByIdResponse {

    private Long id;
    private String title;
    private String synopsis;
    private String summary;
    private BigDecimal price;
    private Integer numberOfPages;
    private Long isbn;
    private String authorName;
    private String authorDescription;

    public static BookDetailsByIdResponse from(final Book book) {
        return BookDetailsByIdResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .synopsis(book.getSynopsis())
                .summary(book.getSummary())
                .price(book.getPrice())
                .numberOfPages(book.getNumberOfPages())
                .isbn(book.getIsbn())
                .authorName(book.getAuthor().getName())
                .authorDescription(book.getAuthor().getDescription())
                .build();
    }
}
