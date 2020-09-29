package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateAuthorRequest {

    @NotBlank(message = "message.author.name.mandatory")
    private String name;

    @NotBlank(message = "message.author.email.mandatory")
    @Email(message = "message.author.email.invalid-format")
    private String email;

    @NotBlank(message = "message.author.description.mandatory")
    @Length(min = 1, max = 400, message = "message.author.description.length")
    private String description;

    public Author toAuthor() {
        return Author.builder()
                .name(this.getName())
                .email(this.getEmail())
                .description(this.getDescription())
                .createdAt(Instant.now())
                .build();
    }
}
