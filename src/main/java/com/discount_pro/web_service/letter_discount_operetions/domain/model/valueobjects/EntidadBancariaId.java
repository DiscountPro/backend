package com.discount_pro.web_service.letter_discount_operetions.domain.model.valueobjects;

import jakarta.persistence.Embeddable;


@Embeddable
public record EntidadBancariaId(Long entidadBancariaId) {
    public EntidadBancariaId {
        if (entidadBancariaId < 0) {
            throw new IllegalArgumentException("Entidad Bancaria id cannot be negative");
        }
    }

    public EntidadBancariaId() {
        this(0L);
    }
}
