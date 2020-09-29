package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.author;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Author;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request.CreateAuthorRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Transactional
    public Author create(CreateAuthorRequest request) {
        return authorRepository.save(request.toAuthor());
    }
}
