package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = {SocialIdentityValidator.class})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface SocialIdentity {

    String message() default "{message.customer.social-identity.invalid-format}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
