package com.discount_pro.web_service.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;


@Embeddable
public record IamId(Long iamId) {
    public IamId {
        if (iamId < 0) {
            throw new IllegalArgumentException("IAM iamId cannot be negative");
        }
    }

    public IamId() {
        this(0L);
    }
}
