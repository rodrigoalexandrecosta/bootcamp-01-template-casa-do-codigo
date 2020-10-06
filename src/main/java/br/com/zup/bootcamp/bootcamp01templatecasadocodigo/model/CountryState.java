package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class CountryState {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne
    private Country country;

    @OneToMany(mappedBy = "state")
    private List<Customer> customers;


    @Deprecated
    public CountryState() {

    }

    public CountryState(String name, Country country) {
        this.name = name;
        this.country = country;
    }
}
