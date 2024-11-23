package com.discount_pro.web_service.profiles.interfaces.rest;

import com.discount_pro.web_service.profiles.domain.model.commands.DeleteProfileCommand;
import com.discount_pro.web_service.profiles.domain.model.queries.*;
import com.discount_pro.web_service.profiles.domain.model.valueobjects.Role;
import com.discount_pro.web_service.profiles.domain.model.valueobjects.UserId;
import com.discount_pro.web_service.profiles.domain.services.ProfileCommandService;
import com.discount_pro.web_service.profiles.domain.services.ProfileQueryService;
import com.discount_pro.web_service.profiles.interfaces.rest.resources.CreateProfileResource;
import com.discount_pro.web_service.profiles.interfaces.rest.resources.ProfileResource;
import com.discount_pro.web_service.profiles.interfaces.rest.transform.CreateProfileCommandFromResourceAssembler;
import com.discount_pro.web_service.profiles.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import com.discount_pro.web_service.profiles.interfaces.rest.transform.UpdateProfileCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

    @PostMapping
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
    @GetMapping
    public ResponseEntity<List<ProfileResource>> getAllProfiles(){
        var getAllProfilesQuery = new GetAllProfilesQuery();
        var profiles = this.profileQueryService.handle(getAllProfilesQuery);
        var profileResources = profiles.stream()
            .map(ProfileResourceFromEntityAssembler::toResourceFromEntity)
            .collect(Collectors.toList());
        return ResponseEntity.ok(profileResources);
    }
    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileResource> getProfileById(@PathVariable Long profileId) {
        var getProfileByIdQuery = new GetProfileByIdQuery(profileId);
        var optionalProfile = this.profileQueryService.handle(getProfileByIdQuery);
        if (optionalProfile.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(optionalProfile.get());
        return ResponseEntity.ok(profileResource);
    }
    @PutMapping("/{profileId}")
    public ResponseEntity<ProfileResource> updateProfile(@PathVariable Long profileId, @RequestBody ProfileResource resource) {
        var updateProfileCommand = UpdateProfileCommandFromResourceAssembler.toCommandFromResource(profileId, resource);
        var optionalProfile = this.profileCommandService.handle(updateProfileCommand);
        if (optionalProfile.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(optionalProfile.get());
        return ResponseEntity.ok(profileResource);
    }
    @DeleteMapping("/{profileId}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long profileId) {
        var deleteProfileCommand = new DeleteProfileCommand(profileId);
        this.profileCommandService.handle(deleteProfileCommand);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/razonSocial/{razonSocial}")
    public ResponseEntity<ProfileResource> getProfileByRazonSocial(@PathVariable String razonSocial) {
        var getProfileByRazonSocialQuery = new GetProfileByRazonSocialQuery(razonSocial);
        var optionalProfile = this.profileQueryService.handle(getProfileByRazonSocialQuery);
        if (optionalProfile.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(optionalProfile.get());
        return ResponseEntity.ok(profileResource);
    }
    @GetMapping("/role/{role}")
    public ResponseEntity<List<ProfileResource>> getProfileByRole(@PathVariable String role) {
        Role roleEnum;
        try {
            roleEnum = Role.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }

        var getProfileByRoleQuery = new GetProfileByRoleQuery(roleEnum);
        var profilesRoles = this.profileQueryService.handle(getProfileByRoleQuery);
        var profileResources = profilesRoles.stream()
                .map(ProfileResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(profileResources);
    }
    @GetMapping("/user_id/{userId}")
    public ResponseEntity<ProfileResource> getProfileByUserId(@PathVariable Long userId) {
        UserId userIdValue = new UserId(userId);
        var getProfileByUserIdQuery = new GetProfileByUserIdQuery(userIdValue);
        var optionalProfile = this.profileQueryService.handle(getProfileByUserIdQuery);
        if (optionalProfile.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(optionalProfile.get());
        return ResponseEntity.ok(profileResource);
    }
}
