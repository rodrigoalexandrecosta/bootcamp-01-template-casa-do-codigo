package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.customer

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest
@ActiveProfiles("it")
class CustomerServiceIT  extends Specification {

    @Autowired
    private CustomerService customerService

    def "Create a new customer with success"() {
        given: "I have a new customer information."

        when: "I handle this new customer to be persistent."

        then: "The new customer is created and stored, and its id is returned."

    }
}
