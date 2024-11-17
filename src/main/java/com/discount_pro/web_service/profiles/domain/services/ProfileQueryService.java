package com.discount_pro.web_service.profiles.domain.services;

import com.discount_pro.web_service.profiles.domain.model.aggregates.Profile;
import com.discount_pro.web_service.profiles.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface ProfileQueryService {
    List<Profile> handle(GetAllProfilesQuery query);
    Optional<Profile> handle(GetProfileByIdQuery query);
    List<Profile> handle(GetProfileByRoleQuery query);
    Optional<Profile> handle(GetProfileByRazonSocialQuery query);
    Optional<Profile> handle(GetProfileByUserIdQuery query);

}
