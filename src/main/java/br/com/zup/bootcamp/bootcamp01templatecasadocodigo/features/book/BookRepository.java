package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.book;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {

}
