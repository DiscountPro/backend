package com.discount_pro.web_service.letras.domain.model.commands;

import com.discount_pro.web_service.letras.domain.model.valueobjects.TipoMoneda;
import com.discount_pro.web_service.letras.domain.model.valueobjects.TipoTasaInteres;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateLetraDePagoCommand(
        TipoMoneda tipoMoneda,
        BigDecimal montoLetra,
        LocalDate fechaEmision,
        LocalDate fechaVencimiento,
        BigDecimal tasaInteresPorce,
        TipoTasaInteres tipoTasaInteres,
        int capitalizacionDias,
        int propietarioId,
        int clienteId,
        int gastosLetrasId,
        int serial) {
}