package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.author;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Author;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request.CreateAuthorRequest;
import net.bytebuddy.utility.RandomString;

public final class AuthorMock {

    private AuthorMock() {
        throw new IllegalStateException("Class not meant for instantiation");
    }

    public static CreateAuthorRequest buildCreateAuthorRequest() {
        return CreateAuthorRequest.builder()
                .name("Bjarne Stroustrup")
                .email("bjarne@" + RandomString.make(10) + ".org")
                .description("Creator of C++ programming language and visiting professor at Columbia University.")
                .build();
    }

    public static Author buildAuthor() {
        return Author.builder()
                .build();
    }
}
