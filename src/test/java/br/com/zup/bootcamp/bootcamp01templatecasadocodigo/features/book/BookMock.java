package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.book;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Author;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Book;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Category;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request.CreateBookRequest;
import net.bytebuddy.utility.RandomString;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

public final class BookMock {

    private BookMock() {
        throw new IllegalStateException("Class not meant for instantiation");
    }

    public static CreateBookRequest buildCreateBookRequest() {
//        final Category category = Category.builder().name("Programming").build();
//        final Author author = Author.builder()
//                .name("Robert C. Martin")
//                .email("unclebob@" + RandomString.make(12) + ".org")
//                .description("Uncle Bob always saves the day.")
//                .createdAt(Instant.now())
//                .build();

        return CreateBookRequest.builder()
                .title("Clean Code: A Handbook of Agile Software Craftsmanship")
                .synopsis("Even bad code can function. But if code isn’t clean, it can bring a development organization to its knees.")
                .summary("Part 1 - Bad Code; Part 2 - Clean Code")
                .price(new BigDecimal("129.90"))
                .numberOfPages(546)
                .isbn(Long.valueOf("9780132350884"))
                .publicationDate(LocalDate.now().plusDays(1))
                .categoryId(123L)
                .authorId(123L)
                .build();
    }

    public static Book buildBook() {
        return Book.builder()
                .build();
    }

}
