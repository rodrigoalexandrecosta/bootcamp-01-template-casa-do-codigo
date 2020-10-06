package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "country", fetch = FetchType.EAGER)
    private Set<CountryState> states;

    @OneToMany(mappedBy = "country")
    private List<Customer> customers;


    @Deprecated
    public Country() {

    }

    public Country(String name) {
        this.name = name;
    }
}
