package com.discount_pro.web_service.profiles.domain.model.commands;

import com.discount_pro.web_service.profiles.domain.model.valueobjects.Role;

public record UpdateProfileCommand(Long profileId,
                                   String ruc,
                                   String razonSocial,
                                   Role role ){ }
