package com.discount_pro.web_service.letter_discount_operetions.application.internal.outboundservices.acl;

import com.discount_pro.web_service.letras.interfaces.acl.LetrasContextFacade;
import com.discount_pro.web_service.letras.interfaces.rest.resources.LetraDePagoResource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class ExternalLetraService {
    private final LetrasContextFacade letrasContextFacade;

    public ExternalLetraService(LetrasContextFacade letrasContextFacade) {
        this.letrasContextFacade = letrasContextFacade;
    }
    public Optional<LocalDate> fetchFechaVencimientoByLetraDePagoId(Long letraId) {
        int letraIdInt = letraId.intValue();
        var optionalLetraDePagoResource = letrasContextFacade.fetchLetraDePagoById(letraIdInt);
        if (optionalLetraDePagoResource.isEmpty()) {
            return Optional.empty();
        }
        var letraDePagoResource = optionalLetraDePagoResource.get();
        LocalDate fechaVencimiento = letraDePagoResource.fechaVencimiento();
        return Optional.of(fechaVencimiento);
    }
    public Optional<BigDecimal>  featTasaInteresPorceByLetraDePagoId(Long letraId) {
        int letraIdInt = letraId.intValue();
        var optionalLetraDePagoResource = letrasContextFacade.fetchLetraDePagoById(letraIdInt);
        if (optionalLetraDePagoResource.isEmpty()) {
            return Optional.empty();
        }
        var letraDePagoResource = optionalLetraDePagoResource.get();
        BigDecimal tasaInteresPorce = letraDePagoResource.tasaInteresPorce();
        return Optional.of(tasaInteresPorce);
    }
}
