package com.discount_pro.web_service.letras.interfaces.acl;

import com.discount_pro.web_service.letras.domain.model.queries.GetLetraDePagoByIdQuery;
import com.discount_pro.web_service.letras.domain.services.LetraDePagoCommandService;
import com.discount_pro.web_service.letras.domain.services.LetraDePagoQueryService;
import com.discount_pro.web_service.letras.interfaces.rest.resources.LetraDePagoResource;
import com.discount_pro.web_service.letras.interfaces.rest.transform.LetraDePagoResourceFromEntityAssembler;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LetrasContextFacade {
    private final LetraDePagoCommandService letrasCommandService;
    private final LetraDePagoQueryService letrasQueryService;

    public LetrasContextFacade(LetraDePagoCommandService letrasCommandService, LetraDePagoQueryService letrasQueryService) {
        this.letrasCommandService = letrasCommandService;
        this.letrasQueryService = letrasQueryService;
    }

    public Optional<LetraDePagoResource> fetchLetraDePagoById(int letraId) {
        var getLetraDePagoByIdQuery = new GetLetraDePagoByIdQuery(letraId);
        var optionalLetraDePago = letrasQueryService.handle(getLetraDePagoByIdQuery);
        if (optionalLetraDePago.isEmpty()) {
            return Optional.empty();
        }
        var letraDePagoResource = LetraDePagoResourceFromEntityAssembler.toResourceFromEntity(optionalLetraDePago.get());
        return Optional.of(letraDePagoResource);
    }
}
