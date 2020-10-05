package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.Customer;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.validation.SocialIdentity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

    private Long countryId;


    public Customer toCostumer() {
        return Customer.builder()

                .build();
    }
}
