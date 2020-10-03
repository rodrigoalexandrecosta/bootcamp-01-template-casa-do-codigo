package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.book

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.author.AuthorMock
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.author.AuthorService
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.category.CategoryMock
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.category.CategoryService
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Book
import org.junit.jupiter.api.AfterEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest
@ActiveProfiles("it")
class BookServiceIT extends Specification{

    @Autowired
    private AuthorService authorService

    @Autowired
    private CategoryService categoryService

    @Autowired
    private BookService bookService

    @AfterEach
    def cleanup() {
        bookService.deleteAll()
    }

    def "Create a new book with success"() {
        given: "I have a new book information."
        def request = BookMock.buildCreateBookRequest()
//        def author = authorService.create(AuthorMock.buildCreateAuthorRequest())
        def category = categoryService.create(CategoryMock.buildCreateCategoryRequest())

//        request.setAuthorId(author.getId())
        request.setCategoryId(category.getId())

        when: "I handle the new book to be persistent."
        def book = bookService.create(request)

        then: "The book is created, stored and returned."
        book != null
        book.getClass().isAssignableFrom(Book.class)
    }

    def "Find the details of a book using a book id"() {
        given: "I have a book id."
        def request = BookMock.buildCreateBookRequest()
        def author = authorService.create(AuthorMock.buildCreateAuthorRequest())
        def category = categoryService.create(CategoryMock.buildCreateCategoryRequest())
        request.setAuthorId(author.getId())
        request.setCategoryId(category.getId())
        def book = bookService.create(request)
        def bookId = book.getId()

        when: "I try to find the details of this book using its id."
        def optionalBookDetails = bookService.findDetailsById(bookId)

        then: "The book details are correctly returned."
        optionalBookDetails.isPresent()
    }

    def "Find all books"() {
        given: "I have two books."
        def request = BookMock.buildCreateBookRequest()
        def author = authorService.create(AuthorMock.buildCreateAuthorRequest())
        def category = categoryService.create(CategoryMock.buildCreateCategoryRequest())

        request.setAuthorId(author.getId())
        request.setCategoryId(category.getId())
        bookService.create(request)

        request = BookMock.buildCreateBookRequest()
        request.setAuthorId(author.getId())
        request.setCategoryId(category.getId())
        bookService.create(request)

        when: "I try to find these two book resources."
        def books = bookService.findAll()

        then: "The two books resources are correctly returned."
        books.size() == 2
    }

    def "Delete all books"() {
        given: "I have two books."
        def request = BookMock.buildCreateBookRequest()
        def author = authorService.create(AuthorMock.buildCreateAuthorRequest())
        def category = categoryService.create(CategoryMock.buildCreateCategoryRequest())

        request.setAuthorId(author.getId())
        request.setCategoryId(category.getId())
        bookService.create(request)

        request = BookMock.buildCreateBookRequest()
        request.setAuthorId(author.getId())
        request.setCategoryId(category.getId())
        bookService.create(request)

        when: "I try to delete all these two books."
        bookService.deleteAll()
        def books = bookService.findAll()

        then: "All books are deleted."
        books.isEmpty()
    }
}
