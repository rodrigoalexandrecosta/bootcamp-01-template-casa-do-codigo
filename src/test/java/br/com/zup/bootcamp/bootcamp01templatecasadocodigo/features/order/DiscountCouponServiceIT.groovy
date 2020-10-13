package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.order

import org.junit.jupiter.api.AfterEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

import java.time.LocalDate

@SpringBootTest
@ActiveProfiles("it")
class DiscountCouponServiceIT extends Specification {

    @Autowired
    private DiscountCouponService discountCouponService

    @AfterEach
    def cleanup() {
        this.discountCouponService.deleteAll()
    }

    def "Create a new discount coupon with success"() {
        given: "I have a new discount coupon information."
        def request = DiscountCouponMock.buildCreateDiscountCouponRequest()

        when: "I handle this new discount coupon to be persistent."
        def discountCouponId = this.discountCouponService.create(request)

        then: "The discount coupon is created and stored, and its id is returned."
        discountCouponId != null
        discountCouponId.getClass().isAssignableFrom(Long.class)
    }

    def "Find a discount coupon using a given coupon code"() {
        given: "I have a coupon code."
        def request = DiscountCouponMock.buildCreateDiscountCouponRequest()
        def discountCouponId = this.discountCouponService.create(request)

        when: "I try to find the discount coupon using the given code."
        def optionalDiscountCoupon = this.discountCouponService.findByCode("PROMO10")

        then: "The discount coupon is correctly returned."
        optionalDiscountCoupon.isPresent()

        and: "All necessary information is filled."
        def discountCoupon = optionalDiscountCoupon.get()
        discountCoupon.getId() == discountCouponId
        discountCoupon.getCode() == "PROMO10"
        discountCoupon.getValidUntil() == LocalDate.now()
    }
}
