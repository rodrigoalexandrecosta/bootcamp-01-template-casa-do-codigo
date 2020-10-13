package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.order;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request.CreateDiscountCouponRequest;
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
@RequestMapping(value = "/api/v1/discount-coupons", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class DiscountCouponRestController {

    private final DiscountCouponService discountCouponService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody @Valid CreateDiscountCouponRequest request) {
        final Long id = this.discountCouponService.create(request);
        return ResponseEntity.created(URI.create(String.format("/discount-coupons/%s", id))).build();
    }
}
