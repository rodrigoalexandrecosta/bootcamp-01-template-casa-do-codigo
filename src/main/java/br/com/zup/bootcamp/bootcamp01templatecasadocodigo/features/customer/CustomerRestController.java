package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.customer;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request.CreateCustomerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/customers" , produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CustomerRestController {

    private final CustomerService customerService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody @Valid CreateCustomerRequest request) {
        final Long id = this.customerService.create(request);
        return ResponseEntity.created(URI.create(String.format("/customers/%s", id))).build();
    }
}
