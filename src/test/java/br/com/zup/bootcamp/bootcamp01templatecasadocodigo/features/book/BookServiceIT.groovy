package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.book

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Book
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest
@ActiveProfiles("it")
class BookServiceIT extends Specification{

    @Autowired
    private BookService bookService

    def "Create a new book with success"() {
        given: "I have a new book information."
        def request = BookMock.buildCreateBookRequest()

        when: "I handle the new book to be persistent."
        def book = bookService.create(request)

        then: "The book is created, stored and returned."
        book != null
        book.getClass().isAssignableFrom(Book.class)
    }
}
