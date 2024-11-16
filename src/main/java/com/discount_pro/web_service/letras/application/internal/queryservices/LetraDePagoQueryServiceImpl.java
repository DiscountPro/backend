package com.discount_pro.web_service.letras.application.internal.queryservices;

import com.discount_pro.web_service.letras.domain.model.aggregates.LetraDePago;
import com.discount_pro.web_service.letras.domain.model.queries.*;
import com.discount_pro.web_service.letras.domain.services.LetraDePagoQueryService;
import com.discount_pro.web_service.letras.infrastructure.persistence.jpa.repositories.LetraDePagoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LetraDePagoQueryServiceImpl implements LetraDePagoQueryService {
    private final LetraDePagoRepository repository;

    public LetraDePagoQueryServiceImpl(LetraDePagoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<LetraDePago> handle(GetLetraDePagoByIdQuery query) {
        return repository.findById(query.id());
    }

    @Override
    public List<LetraDePago> handle(GetLetrasDePagoByPropietarioIdQuery query) {
        return repository.findByPropietarioId(query.propietarioId());
    }

    @Override
    public List<LetraDePago> handle(GetLetrasDePagoByClienteIdQuery query) {
        return repository.findByClienteId(query.clienteId());
    }

    @Override
    public List<LetraDePago> handle(GetAllLetrasDePagoQuery query) {
        return repository.findAll();
    }
}