package com.discount_pro.web_service.profiles.domain.model.commands;

import com.discount_pro.web_service.profiles.domain.model.valueobjects.Role;
//import com.discount_pro.web_service.profiles.domain.model.valueobjects.UserId;

public record CreateProfileCommand(String userName,String password,String RUC, String razonSocial, Role role) {
}
