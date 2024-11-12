package com.discount_pro.web_service.profiles.domain.model.valueobjects;

public enum Role {
    ACREEDOR,
    CLIENTE,
    ENTIDAD_BANCARIA;
    public static Role fromString(String role) {
        try {
            return Role.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role: " + role);
        }
    }
}
