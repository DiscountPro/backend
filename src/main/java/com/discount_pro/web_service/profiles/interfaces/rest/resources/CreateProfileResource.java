package com.discount_pro.web_service.profiles.interfaces.rest.resources;

import com.discount_pro.web_service.profiles.domain.model.valueobjects.Role;


public record CreateProfileResource(String userName,String password,String ruc, String razonSocial, Role role) {
}
