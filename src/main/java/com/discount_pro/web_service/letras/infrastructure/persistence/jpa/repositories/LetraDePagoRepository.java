package com.discount_pro.web_service.letras.infrastructure.persistence.jpa.repositories;

import com.discount_pro.web_service.letras.domain.model.aggregates.LetraDePago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(exported = false)
public interface LetraDePagoRepository extends JpaRepository<LetraDePago, Integer> {
    List<LetraDePago> findByPropietarioId(int propietarioId);
    List<LetraDePago> findByClienteId(int clienteId);
}