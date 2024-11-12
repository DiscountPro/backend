package com.discount_pro.web_service.profiles.interfaces.rest.resources;

import com.discount_pro.web_service.profiles.domain.model.valueobjects.Role;

public record ProfileResource(Long id, String ruc, String razonSocial, Role role) {
}
