package com.discount_pro.web_service.letter_discount_operetions.domain.model.valueobjects;
import jakarta.persistence.Embeddable;

@Embeddable
public record LetterId(Long letterId) {
    public LetterId {
        if (letterId < 0) {
            throw new IllegalArgumentException("Letter id cannot be negative");
        }
    }

    public LetterId() {
        this(0L);
    }
}
