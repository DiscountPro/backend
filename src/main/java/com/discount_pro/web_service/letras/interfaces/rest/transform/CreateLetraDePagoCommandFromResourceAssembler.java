package com.discount_pro.web_service.letras.interfaces.rest.transform;

import com.discount_pro.web_service.letras.domain.model.commands.CreateLetraDePagoCommand;
import com.discount_pro.web_service.letras.interfaces.rest.resources.CreateLetraDePagoResource;

public class CreateLetraDePagoCommandFromResourceAssembler {
    public static CreateLetraDePagoCommand toCommandFromResource(CreateLetraDePagoResource resource) {
        return new CreateLetraDePagoCommand(
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