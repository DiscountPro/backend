package com.discount_pro.web_service.profiles.interfaces.acl;

import com.discount_pro.web_service.profiles.domain.model.queries.GetProfileByIdQuery;
import com.discount_pro.web_service.profiles.domain.services.ProfileCommandService;
import com.discount_pro.web_service.profiles.domain.services.ProfileQueryService;
import com.discount_pro.web_service.profiles.interfaces.rest.resources.ProfileResource;
import com.discount_pro.web_service.profiles.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfilesContextFacade {
    private final ProfileCommandService profileCommandService;
    private final ProfileQueryService profileQueryService;

    public ProfilesContextFacade(ProfileCommandService profileCommandService, ProfileQueryService profileQueryService) {
        this.profileCommandService = profileCommandService;
        this.profileQueryService = profileQueryService;
    }
    public Optional<ProfileResource> fetchProfileById(Long profileId) {
        var getProfileByIdQuery = new GetProfileByIdQuery(profileId);
        var optionalProfile = profileQueryService.handle(getProfileByIdQuery);
        if (optionalProfile.isEmpty()) {
            return Optional.empty();
        }
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(optionalProfile.get());
        return Optional.of(profileResource);
    }



}
