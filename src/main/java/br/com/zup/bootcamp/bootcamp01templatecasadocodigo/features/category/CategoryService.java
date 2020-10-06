package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.category;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Category;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request.CreateCategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public Long create(final CreateCategoryRequest request) {
        if (this.existsByName(request.getName().toUpperCase())) {
            throw new IllegalArgumentException("message.category.name.unique");
        }
        final Category category = this.categoryRepository.save(request.toCategory());
        return category.getId();
    }

    public Optional<Category> findById(final Long categoryId) {
        return this.categoryRepository.findById(categoryId);
    }

    boolean existsByName(final String name) {
        return this.categoryRepository.existsByName(name);
    }

}
