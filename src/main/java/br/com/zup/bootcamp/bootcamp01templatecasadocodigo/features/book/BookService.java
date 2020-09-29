package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.book;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Book;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request.CreateBookRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public Book create(final CreateBookRequest request) {
        return this.bookRepository.save(Book.from(request));
    }
}
