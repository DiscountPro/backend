package com.discount_pro.web_service.letras.domain.services;

import com.discount_pro.web_service.letras.domain.model.aggregates.LetraDePago;
import com.discount_pro.web_service.letras.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface LetraDePagoQueryService {
    Optional<LetraDePago> handle(GetLetraDePagoByIdQuery query);
    List<LetraDePago> handle(GetLetrasDePagoByPropietarioIdQuery query);
    List<LetraDePago> handle(GetLetrasDePagoByClienteIdQuery query);
    List<LetraDePago> handle(GetAllLetrasDePagoQuery query);
}