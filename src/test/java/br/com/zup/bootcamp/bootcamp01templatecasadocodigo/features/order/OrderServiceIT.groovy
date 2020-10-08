package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.order

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.author.AuthorMock
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.author.AuthorService
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.book.BookMock
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.book.BookService
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.category.CategoryMock
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.category.CategoryService
import org.junit.Before
import org.junit.jupiter.api.AfterEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Shared
import spock.lang.Specification

@SpringBootTest
class OrderServiceIT extends Specification {

    @Autowired
    private OrderService orderService

    @Autowired
    private BookService bookService

    @Autowired
    private AuthorService authorService

    @Autowired
    private CategoryService categoryService

    @Shared
    private Long bookId

    @Before
    def init() {
        def bookRequest = BookMock.buildCreateBookRequest()
        def authorId = authorService.create(AuthorMock.buildCreateAuthorRequest())
        def categoryId = categoryService.create(CategoryMock.buildCreateCategoryRequest())
        bookRequest.setAuthorId(authorId)
        bookRequest.setCategoryId(categoryId)

        bookId = this.bookService.create(bookRequest)
    }

    @AfterEach
    def cleanup() {
        orderService.deleteAllOrderItems()
        bookService.deleteAll()
    }

    def "Create a new order with success"() {
        given: "I have a new order information."
        def request = OrderMock.buildCreateOrderRequest(bookId)

        when: "I handle this new order to be persistent."
        def orderId = this.orderService.createOrder(request)

        then: "The new order is created and persisted, and its id is returned."
        orderId != null
        orderId.getClass().isAssignableFrom(Long.class)
    }

    def "Try to create a new order without a book inside the order items should thrown an exception"() {
        given: "I have a new order information without the respective book resource."
        def request = OrderMock.buildCreateOrderRequest()

        when: "I try to persist this new order."
        def orderId = this.orderService.createOrder(request)

        then: "The order is not stored and an exception is thrown."
        IllegalArgumentException e = thrown()
        e.getMessage() == "message.book.not-found"
        orderId == null
    }
}
