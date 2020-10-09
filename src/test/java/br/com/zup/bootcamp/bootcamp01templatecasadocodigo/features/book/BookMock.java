package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.book;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Book;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request.CreateBookRequest;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.response.BookDetailsByIdResponse;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.response.FindAllBooksResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

public final class BookMock {

    private BookMock() {
        throw new IllegalStateException("Class not meant for instantiation");
    }

    public static CreateBookRequest buildCreateBookRequest() {
        return CreateBookRequest.builder()
                .title("Clean Code: A Handbook of Agile Software Craftsmanship")
                .synopsis("Even bad code can function. But if code isn’t clean, it can bring a development organization to its knees.")
                .summary("Part 1 - Bad Code; Part 2 - Clean Code")
                .price(new BigDecimal("129.90"))
                .numberOfPages(546)
                .isbn(new Random().nextLong())
                .publicationDate(LocalDate.now().plusDays(1))
                .categoryId(123L)
                .authorId(123L)
                .build();
    }

    public static CreateBookRequest buildCreateBookRequest(final Long categoryId, final Long authorId) {
        return CreateBookRequest.builder()
                .title("Clean Code: A Handbook of Agile Software Craftsmanship")
                .synopsis("Even bad code can function. But if code isn’t clean, it can bring a development organization to its knees.")
                .summary("Part 1 - Bad Code; Part 2 - Clean Code")
                .price(new BigDecimal("129.90"))
                .numberOfPages(546)
                .isbn(new Random().nextLong())
                .publicationDate(LocalDate.now().plusDays(1))
                .categoryId(categoryId)
                .authorId(authorId)
                .build();
    }

    public static BookDetailsByIdResponse buildDetailsResponse() {
        return BookDetailsByIdResponse.builder()
                .build();
    }

    public static FindAllBooksResponse buildFindAllBooksResponse() {
        return FindAllBooksResponse.builder()
                .build();
    }

}
