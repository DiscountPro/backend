package com.discount_pro.web_service.profiles.interfaces.rest.transform;

import com.discount_pro.web_service.profiles.domain.model.aggregates.Profile;
import com.discount_pro.web_service.profiles.interfaces.rest.resources.ProfileResource;

public class ProfileResourceFromEntityAssembler {
    public static ProfileResource toResourceFromEntity(Profile entity) {
        return new ProfileResource(entity.getId(), entity.getRuc(), entity.getRazonSocial(), entity.getRole());
    }
}
