package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class CountryState {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne
    private Country country;


    @Deprecated
    public CountryState() {

    }

    public CountryState(String name, Country country) {
        this.name = name;
        this.country = country;
    }
}
