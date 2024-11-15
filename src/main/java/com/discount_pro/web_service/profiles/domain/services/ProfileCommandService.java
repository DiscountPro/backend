package com.discount_pro.web_service.profiles.domain.services;

import com.discount_pro.web_service.profiles.domain.model.aggregates.Profile;
import com.discount_pro.web_service.profiles.domain.model.commands.CreateProfileCommand;
import com.discount_pro.web_service.profiles.domain.model.commands.DeleteProfileCommand;
import com.discount_pro.web_service.profiles.domain.model.commands.UpdateProfileCommand;

import java.util.Optional;

public interface ProfileCommandService {
    Long handle(CreateProfileCommand command);
    Optional<Profile> handle(UpdateProfileCommand command);
    void handle(DeleteProfileCommand command);
}
