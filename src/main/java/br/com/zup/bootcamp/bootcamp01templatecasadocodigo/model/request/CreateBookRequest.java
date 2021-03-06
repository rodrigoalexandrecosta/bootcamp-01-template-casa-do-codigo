package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Author;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Book;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Category;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CreateBookRequest {

    @NotBlank(message = "message.book.title.mandatory")
    private String title;

    @NotBlank(message = "message.book.synopsis.mandatory")
    @Length(max = 500, message = "message.book.synopsis.length")
    private String synopsis;

    private String summary;

    @NotNull(message = "message.book.price.mandatory")
    @DecimalMin(value = "20", message = "message.book.price.min-value")
    private BigDecimal price;

    @NotNull(message = "message.book.number-of-pages.mandatory")
    @DecimalMin(value = "100", message = "message.book.number-of-pages.min-value")
    private Integer numberOfPages;

    @NotNull(message = "message.book.isbn.mandatory")
    private Long isbn;

    @NotNull(message = "message.book.publication-date.mandatory")
    @Future(message = "message.book.publication-date.future-date")
    private LocalDate publicationDate;

    @NotNull(message = "message.book.author-id.mandatory")
    private Long authorId;

    @NotNull(message = "message.book.category-id.mandatory")
    private Long categoryId;

    public Book toBook(final Author author, final Category category) {
        return Book.builder()
                .title(this.title)
                .synopsis(this.synopsis)
                .summary(this.summary)
                .price(this.price)
                .numberOfPages(this.numberOfPages)
                .isbn(this.isbn)
                .publicationDate(this.publicationDate)
                .author(author)
                .category(category)
                .build();
    }
}
