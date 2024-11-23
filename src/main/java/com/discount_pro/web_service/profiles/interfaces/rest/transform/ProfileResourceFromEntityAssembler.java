package com.discount_pro.web_service.profiles.interfaces.rest.transform;

import com.discount_pro.web_service.profiles.domain.model.aggregates.Profile;
import com.discount_pro.web_service.profiles.domain.model.valueobjects.UserId;
import com.discount_pro.web_service.profiles.interfaces.rest.resources.ProfileResource;

public class ProfileResourceFromEntityAssembler {
    public static ProfileResource toResourceFromEntity(Profile entity) {
        var userId = new UserId(entity.getUserId());
        // UserId userId = new UserId(entity.getUserId());
        //var userId = entity.getUserId();
        return new ProfileResource(entity.getId(),entity.getUserName(), entity.getPassword(), entity.getRuc(), entity.getRazonSocial(), entity.getRole(),userId);
    }
}
