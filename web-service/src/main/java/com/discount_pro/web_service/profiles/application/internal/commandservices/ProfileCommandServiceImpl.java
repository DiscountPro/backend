package com.discount_pro.web_service.profiles.application.internal.commandservices;

import com.discount_pro.web_service.profiles.domain.model.aggregates.Profile;
import com.discount_pro.web_service.profiles.domain.model.commands.CreateProfileCommand;
import com.discount_pro.web_service.profiles.domain.model.commands.DeleteProfileCommand;
import com.discount_pro.web_service.profiles.domain.model.commands.UpdateProfileCommand;
import com.discount_pro.web_service.profiles.domain.services.ProfileCommandService;
import com.discount_pro.web_service.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {

    private final ProfileRepository profileRepository;

    public ProfileCommandServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }


    @Override
    public Long handle(CreateProfileCommand command) {
        var razonsocial = command.razonSocial();
        if (this.profileRepository.existsByRazonSocial(razonsocial)) {
            throw new IllegalArgumentException("Profile with razon social " + razonsocial + " already exists");
        }
        var profile = new Profile(command);
        try{
            this.profileRepository.save(profile);
        }catch (Exception e){
            throw new IllegalArgumentException("Error while saving profile: " + e.getMessage());
        }
        return profile.getId();
    }

    @Override
    public Optional<Profile> handle(UpdateProfileCommand command) {
        var profileId = command.profileId();
        var razonsocial = command.razonSocial();
        if (this.profileRepository.existsByRazonSocialAndIdIsNot(razonsocial, profileId)) {
            throw new IllegalArgumentException("Profile with razon social " + razonsocial + " already exists");
        }
        if(!this.profileRepository.existsById(profileId)){
            throw new IllegalArgumentException("Profile with id " + profileId + " does not exist");
        }
        var profileToUpdate = this.profileRepository.findById(profileId).get();
        profileToUpdate.updateInformation(command.ruc(),command.razonSocial(), command.role());
        try{
            var updatedProfile = this.profileRepository.save(profileToUpdate);
            return Optional.of(updatedProfile);
        }catch (Exception e){
            throw new IllegalArgumentException("Error while updating profile: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteProfileCommand command) {
        var profileId = command.profileId();
        if(!this.profileRepository.existsById(profileId)){
            throw new IllegalArgumentException("Profile with id " + profileId + " does not exist");
        }
        try{
            this.profileRepository.deleteById(profileId);
        }catch (Exception e){
            throw new IllegalArgumentException("Error while deleting profile: " + e.getMessage());
        }

    }
}
