package com.discount_pro.web_service.profiles.domain.model.commands;

import com.discount_pro.web_service.profiles.domain.model.valueobjects.Role;

public record CreateProfileCommand(String RUC, String razonSocial, Role role) {
}
