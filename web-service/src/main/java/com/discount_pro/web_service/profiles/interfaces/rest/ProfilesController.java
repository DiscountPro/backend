package com.discount_pro.web_service.profiles.interfaces.rest;

import com.discount_pro.web_service.profiles.domain.model.queries.GetProfileByIdQuery;
import com.discount_pro.web_service.profiles.domain.services.ProfileCommandService;
import com.discount_pro.web_service.profiles.domain.services.ProfileQueryService;
import com.discount_pro.web_service.profiles.interfaces.rest.resources.CreateProfileResource;
import com.discount_pro.web_service.profiles.interfaces.rest.resources.ProfileResource;
import com.discount_pro.web_service.profiles.interfaces.rest.transform.CreateProfileCommandFromResourceAssembler;
import com.discount_pro.web_service.profiles.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Profiles", description = "Profile Management Endpoints")
public class ProfilesController {
    private final ProfileQueryService profileQueryService;
    private final ProfileCommandService profileCommandService;

    public ProfilesController(ProfileQueryService profileQueryService, ProfileCommandService profileCommandService) {
        this.profileQueryService = profileQueryService;
        this.profileCommandService = profileCommandService;
    }
    public ResponseEntity<ProfileResource> createProfile(@RequestBody CreateProfileResource resource) {
        var createProfileCommand = CreateProfileCommandFromResourceAssembler
            .toCommandFromResource(resource);
        var profileId = this.profileCommandService.handle(createProfileCommand);

        if (profileId.equals(0L)) {
            return ResponseEntity.badRequest().build();
        }

        var getProfileByIdQuery = new GetProfileByIdQuery(profileId);
        var optionalProfile = this.profileQueryService.handle(getProfileByIdQuery);

        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(optionalProfile.get());
        return new ResponseEntity<>(profileResource, HttpStatus.CREATED);
    }

}
