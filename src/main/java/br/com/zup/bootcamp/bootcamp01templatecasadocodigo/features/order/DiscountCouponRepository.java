package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.features.order;

import br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model.DiscountCoupon;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountCouponRepository extends CrudRepository<DiscountCoupon, Long> {

    Optional<DiscountCoupon> findByCode(String code);
}
