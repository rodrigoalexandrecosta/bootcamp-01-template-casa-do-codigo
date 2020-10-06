package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Country;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.CountryState;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Customer;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.validation.SocialIdentity;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CreateCustomerRequest {

    @NotBlank(message = "message.customer.email.mandatory")
    @Email(message = "message.customer.email.invalid-format")
    private String email;

    @NotBlank(message = "message.customer.first-name.mandatory")
    private String firstName;

    @NotBlank(message = "message.customer.last-name.mandatory")
    private String lastName;

    @NotBlank(message = "message.customer.social-identity.mandatory")
    @SocialIdentity(message = "message.customer.social-identity.invalid-format")
    private String socialIdentity;

    @NotBlank(message = "message.customer.zip-code.mandatory")
    private String zipCode;

    @NotBlank(message = "message.customer.address.mandatory")
    private String address;

    @NotBlank(message = "message.customer.complement.mandatory")
    private String complement;

    @NotBlank(message = "message.customer.city.mandatory")
    private String city;

    @NotBlank(message = "message.customer.phone.mandatory")
    private String phone;

    private Long countryStateId;

    @NotNull(message = "message.customer.country-id.mandatory")
    private Long countryId;


    public Customer toCostumer(final Country country, final CountryState state) {
        return Customer.builder()
                .email(this.getEmail())
                .firstName(this.getFirstName())
                .lastName(this.getLastName())
                .socialIdentity(this.getSocialIdentity())
                .zipCode(this.getZipCode())
                .address(this.getAddress())
                .complement(this.getComplement())
                .city(this.getCity())
                .phone(this.getPhone())
                .country(country)
                .state(state)
                .build();
    }
}
