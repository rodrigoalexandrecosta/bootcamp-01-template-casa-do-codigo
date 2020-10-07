package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.order;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request.CreateOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/api/v1/orders")
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody @Valid CreateOrderRequest request) {
        final Long id = this.orderService.createOrder(request);
        return ResponseEntity.created(URI.create(String.format("/orders/%s", id))).build();
    }
}
