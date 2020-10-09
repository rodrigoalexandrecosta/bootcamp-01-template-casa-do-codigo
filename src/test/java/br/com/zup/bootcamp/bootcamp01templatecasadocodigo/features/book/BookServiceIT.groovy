package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.book

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.author.AuthorMock
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.author.AuthorService
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.category.CategoryMock
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.category.CategoryService
import org.junit.Before
import org.junit.jupiter.api.AfterEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Shared
import spock.lang.Specification

@SpringBootTest
@ActiveProfiles("it")
class BookServiceIT extends Specification {

    @Autowired
    private AuthorService authorService

    @Autowired
    private CategoryService categoryService

    @Autowired
    private BookService bookService

    @Shared
    private Long authorId

    @Shared
    private Long categoryId

    @Before
    def init() {
        authorId = authorService.create(AuthorMock.buildCreateAuthorRequest())
        categoryId = categoryService.create(CategoryMock.buildCreateCategoryRequest())
    }

    @AfterEach
    def cleanup() {
        bookService.deleteAll()
    }

    def "Create a new book with success"() {
        given: "I have a new book information."
        def request = BookMock.buildCreateBookRequest(categoryId, authorId)

        when: "I handle the new book to be persistent."
        def bookId = bookService.create(request)

        then: "The book is created, stored and returned."
        bookId != null
        bookId.getClass().isAssignableFrom(Long.class)
    }

    def "Find the details of a book using a book id"() {
        given: "I have a book id."
        def request = BookMock.buildCreateBookRequest(categoryId, authorId)
        def bookId = bookService.create(request)

        when: "I try to find the details of this book using its id."
        def optionalBookDetails = bookService.findDetailsById(bookId)

        then: "The book details are correctly returned."
        optionalBookDetails.isPresent()
    }

    def "Find all books"() {
        given: "I have two books."
        def request = BookMock.buildCreateBookRequest(categoryId, authorId)
        bookService.create(request)

        request = BookMock.buildCreateBookRequest(categoryId, authorId)
        bookService.create(request)

        when: "I try to find these two book resources."
        def books = bookService.findAll()

        then: "The two books resources are correctly returned."
        books.size() == 2
    }

    def "Delete all books"() {
        given: "I have two books."
        def request = BookMock.buildCreateBookRequest(categoryId, authorId)
        bookService.create(request)

        request = BookMock.buildCreateBookRequest(categoryId, authorId)
        bookService.create(request)

        when: "I try to delete all these two books."
        bookService.deleteAll()
        def books = bookService.findAll()

        then: "All books are deleted."
        books.isEmpty()
    }
}
