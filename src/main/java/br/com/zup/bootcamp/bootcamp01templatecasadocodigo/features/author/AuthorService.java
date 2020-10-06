package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.author;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Author;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request.CreateAuthorRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Transactional
    public Long create(final CreateAuthorRequest request) {
        if (this.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("message.author.email.unique");
        }

        final Author author = this.authorRepository.save(request.toAuthor());
        return author.getId();
    }

    public Optional<Author> findById(final Long authorId) {
        return this.authorRepository.findById(authorId);
    }

    boolean existsByEmail(final String email) {
        return this.authorRepository.existsByEmail(email);
    }
}
