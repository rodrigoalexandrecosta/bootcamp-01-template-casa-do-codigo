package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.category;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Category;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request.CreateCategoryRequest;
import net.bytebuddy.utility.RandomString;

public final class CategoryMock {

    private CategoryMock() {
        throw new IllegalStateException("Class not meant for instantiation");
    }

    public static CreateCategoryRequest buildCreateCategoryRequest() {
        return CreateCategoryRequest.builder().name(RandomString.make(25)).build();
    }

    public static Category buildCategory() {
        return Category.builder().build();
    }
}
