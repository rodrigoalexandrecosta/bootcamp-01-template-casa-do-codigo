package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.order;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.DiscountCoupon;
import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.request.CreateDiscountCouponRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiscountCouponService {

    private final DiscountCouponRepository discountCouponRepository;

    @Transactional
    public Long create(final CreateDiscountCouponRequest request) {
        final DiscountCoupon coupon = this.discountCouponRepository.save(request.toDiscountCoupon());
        return coupon.getId();
    }

    public Optional<DiscountCoupon> findByCode(final String code) {
        return this.discountCouponRepository.findByCode(code);
    }

    @Transactional
    public void deleteAll() {
        this.discountCouponRepository.deleteAll();
    }

}
