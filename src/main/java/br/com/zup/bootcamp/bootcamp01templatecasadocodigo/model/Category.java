package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request.CreateCategoryRequest;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<Book> books;

    public static Category from(final CreateCategoryRequest request) {
        return Category.builder()
                .name(request.getName())
                .build();
    }
}
