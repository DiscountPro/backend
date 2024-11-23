package com.discount_pro.web_service.profiles.infrastructure.persistence.jpa.repositories;

import com.discount_pro.web_service.profiles.domain.model.aggregates.Profile;
import com.discount_pro.web_service.profiles.domain.model.valueobjects.Role;
import com.discount_pro.web_service.profiles.domain.model.valueobjects.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile,Long> {
    boolean existsByRazonSocial(String razonSocial);
    boolean existsByRazonSocialAndIdIsNot(String razonSocial, Long id);
    boolean existsByRuc(String RUC);
    boolean existsByRucAndIdIsNot(String ruc,Long id);
    Optional<Profile> findByRazonSocial(String razonSocial);
    List<Profile> findByRole(Role role);
    Optional<Profile> findByUserId(UserId userId);

    //boolean existsByRuc(String ruc);
}
