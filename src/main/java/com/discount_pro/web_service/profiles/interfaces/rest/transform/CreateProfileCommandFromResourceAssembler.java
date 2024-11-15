package com.discount_pro.web_service.profiles.interfaces.rest.transform;

import com.discount_pro.web_service.profiles.domain.model.commands.CreateProfileCommand;
import com.discount_pro.web_service.profiles.interfaces.rest.resources.CreateProfileResource;

public class CreateProfileCommandFromResourceAssembler {
    public static CreateProfileCommand toCommandFromResource(CreateProfileResource resource) {
        return new CreateProfileCommand(resource.ruc(), resource.razonSocial(), resource.role());
    }
}
