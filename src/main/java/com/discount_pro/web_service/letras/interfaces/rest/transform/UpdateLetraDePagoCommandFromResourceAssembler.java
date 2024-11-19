package com.discount_pro.web_service.letras.interfaces.rest.transform;

import com.discount_pro.web_service.letras.domain.model.commands.UpdateLetraDePagoCommand;
import com.discount_pro.web_service.letras.interfaces.rest.resources.UpdateLetraDePagoResource;

public class UpdateLetraDePagoCommandFromResourceAssembler {
    public static UpdateLetraDePagoCommand toCommandFromResource(int id, UpdateLetraDePagoResource resource) {
        return new UpdateLetraDePagoCommand(
                id,
                resource.tipoMoneda(),
                resource.montoLetra(),
                resource.fechaEmision(),
                resource.fechaVencimiento(),
                resource.tasaInteresPorce(),
                resource.tipoTasaInteres(),
                resource.capitalizacionDias(),
                resource.propietarioId(),
                resource.clienteId(),
                resource.gastosLetrasId(),
                resource.serial()
        );
    }
}