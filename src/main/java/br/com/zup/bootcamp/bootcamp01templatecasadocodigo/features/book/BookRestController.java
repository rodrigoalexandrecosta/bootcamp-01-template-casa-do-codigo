package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.book;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Book;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request.CreateBookRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/books", produces = APPLICATION_JSON_VALUE)
public class BookRestController {

    private final BookService bookService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody @Valid CreateBookRequest request) {
        final Book book = this.bookService.create(request);
        return ResponseEntity.created(URI.create(String.format("/books/%s", book.getId()))).build();
    }
}
