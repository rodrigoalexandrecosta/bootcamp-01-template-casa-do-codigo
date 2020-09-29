package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.category;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Category;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request.CreateCategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public Category create(final CreateCategoryRequest request) {
        return this.categoryRepository.save(Category.from(request));
    }

}
