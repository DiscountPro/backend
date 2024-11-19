package com.discount_pro.web_service.letras.application.internal.commandservices;

import com.discount_pro.web_service.letras.domain.model.aggregates.LetraDePago;
import com.discount_pro.web_service.letras.domain.model.commands.CreateLetraDePagoCommand;
import com.discount_pro.web_service.letras.domain.model.commands.UpdateLetraDePagoCommand;
import com.discount_pro.web_service.letras.domain.model.commands.DeleteLetraDePagoCommand;
import com.discount_pro.web_service.letras.domain.services.LetraDePagoCommandService;
import com.discount_pro.web_service.letras.infrastructure.persistence.jpa.repositories.LetraDePagoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LetraDePagoCommandServiceImpl implements LetraDePagoCommandService {
    private final LetraDePagoRepository repository;

    public LetraDePagoCommandServiceImpl(LetraDePagoRepository repository) {
        this.repository = repository;
    }

    @Override
    public LetraDePago handle(CreateLetraDePagoCommand command) {
        LetraDePago letraDePago = new LetraDePago(
                command.tipoMoneda(),
                command.montoLetra(),
                command.fechaEmision(),
                command.fechaVencimiento(),
                command.tasaInteresPorce(),
                command.tipoTasaInteres(),
                command.capitalizacionDias(),
                command.propietarioId(),
                command.clienteId(),
                command.gastosLetrasId(),
                command.serial()
        );
        return repository.save(letraDePago);
    }

    @Override
    public Optional<LetraDePago> handle(UpdateLetraDePagoCommand command) {
        Optional<LetraDePago> optionalLetraDePago = repository.findById(command.id());
        if (optionalLetraDePago.isPresent()) {
            LetraDePago letraDePago = optionalLetraDePago.get();
            letraDePago.setTipoMoneda(command.tipoMoneda());
            letraDePago.setMontoLetra(command.montoLetra());
            letraDePago.setFechaEmision(command.fechaEmision());
            letraDePago.setFechaVencimiento(command.fechaVencimiento());
            letraDePago.setTasaInteresPorce(command.tasaInteresPorce());
            letraDePago.setTipoTasaInteres(command.tipoTasaInteres());
            letraDePago.setCapitalizacionDias(command.capitalizacionDias());
            letraDePago.setPropietarioId(command.propietarioId());
            letraDePago.setClienteId(command.clienteId());
            letraDePago.setGastosLetrasId(command.gastosLetrasId());
            letraDePago.setSerial(command.serial());
            repository.save(letraDePago);
            return Optional.of(letraDePago);
        }
        return Optional.empty();
    }

    @Override
    public void handle(DeleteLetraDePagoCommand command) {
        repository.deleteById(command.id());
    }
}