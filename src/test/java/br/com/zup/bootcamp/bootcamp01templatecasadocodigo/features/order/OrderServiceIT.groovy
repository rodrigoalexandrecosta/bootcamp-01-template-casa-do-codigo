package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.order

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.author.AuthorMock
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.author.AuthorService
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.book.BookMock
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.book.BookService
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.category.CategoryMock
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.category.CategoryService
import org.junit.Before
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Shared
import spock.lang.Specification

@SpringBootTest
@ActiveProfiles("it")
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

    def "Find a persisted order using a given id"() {
        given: "I have an order id."
        def request = OrderMock.buildCreateOrderRequest(bookId)
        def orderId = this.orderService.createOrder(request)

        when: "I try to find the order resources using its id."
        def optionalOrder = this.orderService.findById(orderId)

        then: "The order is correctly returned."
        optionalOrder.isPresent()

        and: "All required information are filled."
        def order = optionalOrder.get()
        order.getId() == orderId
        order.getTotalPrice() == request.getTotalPrice()
        order.getItems().size() > 0
    }
}
