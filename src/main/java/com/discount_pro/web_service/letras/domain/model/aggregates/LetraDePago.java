package com.discount_pro.web_service.letras.domain.model.aggregates;

import com.discount_pro.web_service.letras.domain.model.valueobjects.TipoMoneda;
import com.discount_pro.web_service.letras.domain.model.valueobjects.TipoTasaInteres;
import com.discount_pro.web_service.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class LetraDePago extends AuditableAbstractAggregateRoot<LetraDePago> {
    @Enumerated(EnumType.STRING)
    private TipoMoneda tipoMoneda;

    private BigDecimal montoLetra;
    private LocalDate fechaEmision;
    private LocalDate fechaVencimiento;
    private BigDecimal tasaInteresPorce;

    @Enumerated(EnumType.STRING)
    private TipoTasaInteres tipoTasaInteres;

    private int capitalizacionDias;
    private int propietarioId;
    private int clienteId;
    private int gastosLetrasId;
    private int serial;

    // Public no-arg constructor
    public LetraDePago() {
    }

    // Constructor with parameters
    public LetraDePago(TipoMoneda tipoMoneda, BigDecimal montoLetra, LocalDate fechaEmision, LocalDate fechaVencimiento, BigDecimal tasaInteresPorce, TipoTasaInteres tipoTasaInteres, int capitalizacionDias, int propietarioId, int clienteId, int gastosLetrasId, int serial) {
        this.tipoMoneda = tipoMoneda;
        this.montoLetra = montoLetra;
        this.fechaEmision = fechaEmision;
        this.fechaVencimiento = fechaVencimiento;
        this.tasaInteresPorce = tasaInteresPorce;
        this.tipoTasaInteres = tipoTasaInteres;
        this.capitalizacionDias = capitalizacionDias;
        this.propietarioId = propietarioId;
        this.clienteId = clienteId;
        this.gastosLetrasId = gastosLetrasId;
        this.serial = serial;
    }

    // Getters and Setters
    public TipoMoneda getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(TipoMoneda tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    public BigDecimal getMontoLetra() {
        return montoLetra;
    }

    public void setMontoLetra(BigDecimal montoLetra) {
        this.montoLetra = montoLetra;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public BigDecimal getTasaInteresPorce() {
        return tasaInteresPorce;
    }

    public void setTasaInteresPorce(BigDecimal tasaInteresPorce) {
        this.tasaInteresPorce = tasaInteresPorce;
    }

    public TipoTasaInteres getTipoTasaInteres() {
        return tipoTasaInteres;
    }

    public void setTipoTasaInteres(TipoTasaInteres tipoTasaInteres) {
        this.tipoTasaInteres = tipoTasaInteres;
    }

    public int getCapitalizacionDias() {
        return capitalizacionDias;
    }

    public void setCapitalizacionDias(int capitalizacionDias) {
        this.capitalizacionDias = capitalizacionDias;
    }

    public int getPropietarioId() {
        return propietarioId;
    }

    public void setPropietarioId(int propietarioId) {
        this.propietarioId = propietarioId;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getGastosLetrasId() {
        return gastosLetrasId;
    }

    public void setGastosLetrasId(int gastosLetrasId) {
        this.gastosLetrasId = gastosLetrasId;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }
}