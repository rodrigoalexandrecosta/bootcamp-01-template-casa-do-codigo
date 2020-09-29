package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.category;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {

}
