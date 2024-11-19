package com.discount_pro.web_service.letras.interfaces.rest.transform;

import com.discount_pro.web_service.letras.domain.model.aggregates.LetraDePago;
import com.discount_pro.web_service.letras.interfaces.rest.resources.LetraDePagoResource;

public class LetraDePagoResourceFromEntityAssembler {
    public static LetraDePagoResource toResourceFromEntity(LetraDePago entity) {
        return new LetraDePagoResource(
                entity.getId(), // Include the id field
                entity.getTipoMoneda(),
                entity.getMontoLetra(),
                entity.getFechaEmision(),
                entity.getFechaVencimiento(),
                entity.getTasaInteresPorce(),
                entity.getTipoTasaInteres(),
                entity.getCapitalizacionDias(),
                entity.getPropietarioId(),
                entity.getClienteId(),
                entity.getGastosLetrasId(),
                entity.getSerial()
        );
    }
}