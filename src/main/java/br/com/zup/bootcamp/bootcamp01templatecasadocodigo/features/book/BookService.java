package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.book;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.author.AuthorService;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.category.CategoryService;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Author;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Book;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Category;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request.CreateBookRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @Transactional
    public Book create(final CreateBookRequest request) {
        Author author = authorService.findById(request.getAuthorId())
                .orElseThrow();

        Category category = categoryService.findById(request.getCategoryId())
                .orElseThrow();

        return this.bookRepository.save(Book.from(request, author, category));
    }
}
