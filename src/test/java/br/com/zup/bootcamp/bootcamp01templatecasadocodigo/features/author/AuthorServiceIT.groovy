package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.author

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest
@ActiveProfiles("it")
class AuthorServiceIT extends Specification {

    @Autowired
    private AuthorService authorService

    def "Create a new author with success"() {
        given: "I have a new author information."
        def request = AuthorMock.buildCreateAuthorRequest()

        when: "I handle the new author information to be persistent."
        def authorId = authorService.create(request)

        then: "The author is created, stored and returned."
        authorId != null
        authorId.getClass().isAssignableFrom(Long.class)
    }
}
