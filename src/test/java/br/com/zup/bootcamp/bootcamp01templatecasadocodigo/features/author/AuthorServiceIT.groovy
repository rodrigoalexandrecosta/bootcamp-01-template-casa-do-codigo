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
        def authorId = this.authorService.create(request)

        then: "The author is created, stored and returned."
        authorId != null
        authorId.getClass().isAssignableFrom(Long.class)
    }

    def "Create a new author using an existing email should thrown an exception"() {
        given: "I have a new author information using an already persisted email."
        def request = AuthorMock.buildCreateAuthorRequest()
        def authorId = this.authorService.create(request)

        when: "I try to persist another author using the same email."
        def anotherAuthorId = this.authorService.create(request)

        then: "The new author is not stored and an exception is thrown."
        IllegalArgumentException e = thrown()
        e.getMessage() == "message.author.email.unique"
        anotherAuthorId == null
    }

    def "Find an author using an id"() {
        given: "I have an author id."
        def request = AuthorMock.buildCreateAuthorRequest()
        def authorId = this.authorService.create(request)

        when: "I try to find the author using this id."
        def optionalAuthor = this.authorService.findById(authorId)

        then: "The author resources are returned."
        optionalAuthor.isPresent()

        and: "All required information are filled."
        def author = optionalAuthor.get()
        author.getId() == authorId
        author.getName() == request.getName()
        author.getEmail() == request.getEmail()
        author.getDescription() == request.getDescription()
    }
}
