package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.book;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request.CreateBookRequest;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.response.BookDetailsByIdResponse;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.response.FindAllBooksResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/books", produces = APPLICATION_JSON_VALUE)
public class BookRestController {

    private final BookService bookService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody @Valid CreateBookRequest request) {
        final Long id = this.bookService.create(request);
        return ResponseEntity.created(URI.create(String.format("/books/%s", id))).build();
    }

    @GetMapping(value = "/{bookId}")
    public ResponseEntity<BookDetailsByIdResponse> findBookDetailsById(@PathVariable Long bookId) {
        return this.bookService.findDetailsById(bookId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<FindAllBooksResponse>> findAll() {
        return ResponseEntity.ok(this.bookService.findAll());
    }
}
