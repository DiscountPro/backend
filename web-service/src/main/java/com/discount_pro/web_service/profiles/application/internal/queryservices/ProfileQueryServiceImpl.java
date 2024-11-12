package com.discount_pro.web_service.profiles.application.internal.queryservices;

import com.discount_pro.web_service.profiles.domain.model.aggregates.Profile;
import com.discount_pro.web_service.profiles.domain.model.queries.GetAllProfilesQuery;
import com.discount_pro.web_service.profiles.domain.model.queries.GetProfileByIdQuery;
import com.discount_pro.web_service.profiles.domain.model.queries.GetProfileByRazonSocialQuery;
import com.discount_pro.web_service.profiles.domain.model.queries.GetProfileByRoleQuery;
import com.discount_pro.web_service.profiles.domain.services.ProfileQueryService;
import com.discount_pro.web_service.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileQueryServiceImpl implements ProfileQueryService {

    private final ProfileRepository profileRepository;

    public ProfileQueryServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public List<Profile> handle(GetAllProfilesQuery query) {
        return this.profileRepository.findAll();
    }

    @Override
    public Optional<Profile> handle(GetProfileByIdQuery query) {
        return this.profileRepository.findById(query.profileId());
    }

    @Override
    public List<Profile> handle(GetProfileByRoleQuery query) {
        return this.profileRepository.findByRole(query.role());
    }

    @Override
    public Optional<Profile> handle(GetProfileByRazonSocialQuery query) {
        return this.profileRepository.findByRazonSocial(query.razonSocial());
    }
}
