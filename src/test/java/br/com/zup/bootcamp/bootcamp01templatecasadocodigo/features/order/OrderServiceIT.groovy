package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.order

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.author.AuthorMock
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.author.AuthorService
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.book.BookMock
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.book.BookService
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.category.CategoryMock
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.category.CategoryService
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.customer.CustomerMock
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.customer.CustomerService
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.localization.LocalizationMock
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.localization.LocalizationService
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

    @Autowired
    private CustomerService customerService

    @Autowired
    private LocalizationService localizationService

    @Autowired
    private DiscountCouponService discountCouponService

    @Shared
    private Long bookId

    @Shared
    private Long customerId

    @Before
    def init() {
        def bookRequest = BookMock.buildCreateBookRequest()
        def authorId = authorService.create(AuthorMock.buildCreateAuthorRequest())
        def categoryId = categoryService.create(CategoryMock.buildCreateCategoryRequest())
        bookRequest.setAuthorId(authorId)
        bookRequest.setCategoryId(categoryId)
        bookId = this.bookService.create(bookRequest)

        def countryId = this.localizationService.createCountry(LocalizationMock.buildCreateCountryRequest())
        customerId = this.customerService.create(CustomerMock.buildCreateCustomerRequest(countryId))
    }

    def "Create a new order with success"() {
        given: "I have a new order information."
        def request = OrderMock.buildCreateOrderRequest(bookId, customerId)

        when: "I handle this new order to be persistent."
        def orderId = this.orderService.create(request)

        then: "The new order is created and persisted, and its id is returned."
        orderId != null
        orderId.getClass().isAssignableFrom(Long.class)
    }

    def "Try to create a new order without a book inside the order items should thrown an exception"() {
        given: "I have a new order information without the respective book resource."
        def request = OrderMock.buildCreateOrderRequest()
        request.setCustomerId(customerId)

        when: "I try to persist this new order."
        def orderId = this.orderService.create(request)

        then: "The order is not stored and an exception is thrown."
        IllegalArgumentException e = thrown()
        e.getMessage() == "message.book.not-found"
        orderId == null
    }

    def "Try to create a new order without a customer inside the order should thrown an exception"() {
        given: "I have a new order information without the respective customer resource."
        def request = OrderMock.buildCreateOrderRequest(bookId, customerId)
        request.setCustomerId(0L)

        when: "I try to persist this new order."
        def orderId = this.orderService.create(request)

        then: "The order is not stored and an exception is thrown."
        IllegalArgumentException e = thrown()
        e.getMessage() == "message.customer.not-found"
        orderId == null
    }

    def "Find a persisted order using a given id"() {
        given: "I have an order id."
        def request = OrderMock.buildCreateOrderRequest(bookId, customerId)
        def orderId = this.orderService.create(request)

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

    def "Create a new order applying a discount coupon with success"() {
        given: "I have a new order information and a discount coupon."
        def request = OrderMock.buildCreateOrderRequest(bookId, customerId)
        this.discountCouponService.create(DiscountCouponMock.buildCreateDiscountCouponRequest())
        request.setDiscountCouponCode("PROMO10")

        when: "I handle this new order to be persistent."
        def orderId = this.orderService.create(request)
        def optionalOrder = this.orderService.findById(orderId)

        then: "The new order is created and stored, and its id is returned."
        orderId != null
        orderId.getClass().isAssignableFrom(Long.class)
        optionalOrder.isPresent()

        and: "The discount is correctly applied."
        def order = optionalOrder.get()
        order.getTotalPrice() != order.getTotalPriceWithDiscount()
        order.getTotalPrice() > order.getTotalPriceWithDiscount()
    }

    def "Create an order without a discount coupon does not apply any discount"() {
        given: "I have a new order information without any discount coupon."
        def request = OrderMock.buildCreateOrderRequest(bookId, customerId)

        when: "I handle this new order without discount to be persistent."
        def orderId = this.orderService.create(request)
        def optionalOrder = this.orderService.findById(orderId)

        then: "The new order is created and stored, and its id is returned."
        orderId != null
        orderId.getClass().isAssignableFrom(Long.class)
        optionalOrder.isPresent()

        and: "No discount is applied."
        def order = optionalOrder.get()
        order.getTotalPrice() == order.getTotalPriceWithDiscount()
    }
}
