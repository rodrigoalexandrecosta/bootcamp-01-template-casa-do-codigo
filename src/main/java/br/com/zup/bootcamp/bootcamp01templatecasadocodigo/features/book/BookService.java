package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.book;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.author.AuthorService;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.category.CategoryService;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Author;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Book;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Category;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request.CreateBookRequest;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.response.BookDetailsByIdResponse;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.response.FindAllBooksResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        return this.bookRepository.save(request.toBook(author, category));
    }

    public Optional<BookDetailsByIdResponse> findDetailsById(final Long bookId) {
        return this.bookRepository.findById(bookId)
                .stream()
                .map(BookDetailsByIdResponse::from)
                .findFirst();
    }

    public List<FindAllBooksResponse> findAll() {
        return this.bookRepository.findAll()
                .stream()
                .map(FindAllBooksResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteAll() {
        this.bookRepository.deleteAll();;
    }
}
