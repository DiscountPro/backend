package com.discount_pro.web_service.profiles.interfaces.rest.transform;

import com.discount_pro.web_service.profiles.domain.model.commands.UpdateProfileCommand;
import com.discount_pro.web_service.profiles.interfaces.rest.resources.ProfileResource;

public class UpdateProfileCommandFromResourceAssembler {
    public static UpdateProfileCommand toCommandFromResource(Long profileId, ProfileResource resource) {
        return new UpdateProfileCommand(profileId, resource.ruc(), resource.razonSocial(), resource.role());
    }
}
