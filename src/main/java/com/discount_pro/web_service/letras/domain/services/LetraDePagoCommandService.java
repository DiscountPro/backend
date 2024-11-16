package com.discount_pro.web_service.letras.domain.services;

import com.discount_pro.web_service.letras.domain.model.aggregates.LetraDePago;
import com.discount_pro.web_service.letras.domain.model.commands.CreateLetraDePagoCommand;
import com.discount_pro.web_service.letras.domain.model.commands.UpdateLetraDePagoCommand;
import com.discount_pro.web_service.letras.domain.model.commands.DeleteLetraDePagoCommand;

import java.util.Optional;

public interface LetraDePagoCommandService {
    LetraDePago handle(CreateLetraDePagoCommand command);
    Optional<LetraDePago> handle(UpdateLetraDePagoCommand command);
    void handle(DeleteLetraDePagoCommand command);
}