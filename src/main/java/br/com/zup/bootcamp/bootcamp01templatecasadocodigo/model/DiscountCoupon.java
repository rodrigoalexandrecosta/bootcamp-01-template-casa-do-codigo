package br.com.zup.bootcamp.bootcamp01templatecasadocodigo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class DiscountCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String code;

    private BigDecimal discountPercentage;

    private LocalDate validUntil;

    @OneToMany(mappedBy = "discountCoupon")
    private List<Order> orders;

    @Deprecated
    public DiscountCoupon() {
    }

    public DiscountCoupon(String code, BigDecimal discountPercentage, LocalDate validUntil) {
        this.code = code;
        this.discountPercentage = discountPercentage;
        this.validUntil = validUntil;
    }
}
