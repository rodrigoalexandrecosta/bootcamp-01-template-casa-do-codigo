package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Category;
import lombok.*;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CreateCategoryRequest {

    @NotBlank(message = "message.category.name.mandatory")
    private String name;

    public Category toCategory() {
        return Category.builder()
                .name(this.name.toUpperCase())
                .build();
    }
}
