package com.discount_pro.web_service.profiles.interfaces.rest.resources;

import com.discount_pro.web_service.profiles.domain.model.valueobjects.Role;
import com.discount_pro.web_service.profiles.domain.model.valueobjects.UserId;

public record CreateProfileResource(String ruc, String razonSocial, Role role, UserId userId) {
}
