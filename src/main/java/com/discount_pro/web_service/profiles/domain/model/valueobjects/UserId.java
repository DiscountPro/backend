package com.discount_pro.web_service.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record UserId( Long userId) {
    public UserId {
        if (userId < 0) {
            throw new IllegalArgumentException("User userId cannot be negative");
        }
    }

    public UserId() {
        this(0L);
    }
}
