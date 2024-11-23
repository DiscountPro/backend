package com.discount_pro.web_service.letter_discount_operetions.domain.model.aggregates;

import com.discount_pro.web_service.letter_discount_operetions.domain.model.valueobjects.EntidadBancariaId;
import com.discount_pro.web_service.letter_discount_operetions.domain.model.valueobjects.LetterId;
import com.discount_pro.web_service.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;


@Getter
@Entity
@Table(name = "letter_discount_operations")
public class LetterDiscountOperation extends AuditableAbstractAggregateRoot<LetterDiscountOperation> {

    //letras id long
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "letterId", column = @Column(name = "letter_id", nullable = false))
    })
    private LetterId letterId;
    //entidadBancariaId long
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "entidadBancariaId", column = @Column(name = "entidad_bancaria_id", nullable = false))
    })
    private EntidadBancariaId entidadBancariaId;

    //tasaDescuentoPorcentualdeciaml(3,7)
    @Column(name="discount_rate_percentage", nullable = false)
    private Double discountRatePercentage;
    //fechaDescuento solo dia/mes/año sin hora
    @Column(name="discount_date",nullable = false)
    private LocalDate discountDate;



    public LetterDiscountOperation(LocalDate discountDate) {
        this.discountDate = discountDate;
    }

    public LetterDiscountOperation() {

    }

    public LetterDiscountOperation(
            Long letterId,
            Long entidadBancariaId,
            double discountRatePercentage,
            LocalDate discountDate
    ) {
        this.letterId = new LetterId(letterId);
        this.entidadBancariaId = new EntidadBancariaId(entidadBancariaId);
        this.discountRatePercentage = discountRatePercentage;
        this.discountDate = discountDate;
    }
    // forlumal ara pasar TEP a TEP
    // TEP2= (1+TEP1)^(days/360) - 1

    public static double calculateTep(double tep_1, long days) {
        BigDecimal tep1BigDecimal = BigDecimal.valueOf(tep_1);
        BigDecimal tep1 = tep1BigDecimal.divide(BigDecimal.valueOf(100), 9, RoundingMode.HALF_UP);
        BigDecimal daysBigDecimal = BigDecimal.valueOf(days);
        BigDecimal exponent = daysBigDecimal.divide(BigDecimal.valueOf(360), 20, RoundingMode.HALF_UP);
        // Using BigDecimal for exponentiation
        double exponentDouble = exponent.doubleValue();
        double tep2Double = Math.pow(1 + tep1.doubleValue(), exponentDouble) - 1;
        // Redondear el resultado a 9 decimales usando BigDecimal
        BigDecimal tep2BigDecimal = BigDecimal.valueOf(tep2Double).setScale(9, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)).setScale(7, RoundingMode.HALF_UP);
        return tep2BigDecimal.doubleValue();
    }

    // formula para calcular el tasaDescuentoPorcentual
    // d = TEP/(1+TEP)
    // d = i'/(1+i')
    public static double calculateDiscount(double interestRatePercentage) {
        //
        BigDecimal tep = BigDecimal.valueOf(interestRatePercentage);
        //dividir tep entre 100
        BigDecimal tep2 = tep.divide(BigDecimal.valueOf(100), 9, RoundingMode.HALF_UP);
        BigDecimal one = BigDecimal.ONE;
        BigDecimal discount = tep2.divide(one.add(tep2), 9, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)).setScale(7, RoundingMode.HALF_UP);
        return discount.doubleValue();
    }

    public double getDiscountRatePercentage() {
        return discountRatePercentage;
    }

    public LocalDate getDiscountDate() {
        return discountDate;
    }

    public Long getLetterId() {
        return letterId.letterId();
    }
    public Long getEntidadBancariaId() {
        return entidadBancariaId.entidadBancariaId();
    }

    public void updateInformation(Long letterId,
                                  Long entidadBancariaId,
                                  double discountRatePercentage,
                                  LocalDate discountDate) {
        this.letterId = new LetterId(letterId);
        this.entidadBancariaId = new EntidadBancariaId(entidadBancariaId);
        this.discountRatePercentage = discountRatePercentage;
        this.discountDate = discountDate;
    }
}


