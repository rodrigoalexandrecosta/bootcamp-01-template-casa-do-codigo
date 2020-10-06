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
        def categoryId = this.categoryService.create(request)

        then: "The new category is created, stored and returned."
        categoryId != null
        categoryId.getClass().isAssignableFrom(Long.class)
    }

    def "Try to create a new category using an existing name should thrown an exception"() {
        given: "I have a new category information using an already persisted name."
        def request = CategoryMock.buildCreateCategoryRequest()
        def categoryId = this.categoryService.create(request)

        when: "I handle this new category to be persistent."
        def anotherCategoryId = this.categoryService.create(request)

        then: "The new category is not stored and an exception is thrown."
        IllegalArgumentException e = thrown()
        e.getMessage() == "message.category.name.unique"
        anotherCategoryId == null

    }

    def "Find a category using an id"() {
        given: "I have a category id."
        def request = CategoryMock.buildCreateCategoryRequest()
        def categoryId = this.categoryService.create(request)

        when: "I try to find this category using its id."
        def optionalCategory = this.categoryService.findById(categoryId)

        then: "The category is returned and all required information are filled."
        optionalCategory.isPresent()
        optionalCategory.get().getName() == request.getName()
    }
}
