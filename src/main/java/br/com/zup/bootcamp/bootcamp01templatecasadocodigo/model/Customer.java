package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String socialIdentity;

    @Column(nullable = false)
    private String zipCode;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String complement;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String phone;

//    @Column(nullable = false)
//    private CountryState state;
//
//    @Column(nullable = false)
//    private Country country;
}
