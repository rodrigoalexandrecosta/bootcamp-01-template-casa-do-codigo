package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;


    @Deprecated
    public Country() {

    }

    public Country(String name) {
        this.name = name;
    }
}
