package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.author;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {

}
