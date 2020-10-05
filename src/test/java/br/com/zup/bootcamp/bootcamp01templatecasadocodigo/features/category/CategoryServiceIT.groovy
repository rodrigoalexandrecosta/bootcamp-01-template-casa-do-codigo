package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.category

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest
@ActiveProfiles("it")
class CategoryServiceIT extends Specification {

    @Autowired
    private CategoryService categoryService

    def "Create a new category with success"() {
        given: "I have a new category information."
        def request = CategoryMock.buildCreateCategoryRequest()

        when: "I handle this new category to be persistent."
        def categoryId = categoryService.create(request)

        then: "The new category is created, stored and returned."
        categoryId != null
        categoryId.getClass().isAssignableFrom(Long.class)
    }
}
