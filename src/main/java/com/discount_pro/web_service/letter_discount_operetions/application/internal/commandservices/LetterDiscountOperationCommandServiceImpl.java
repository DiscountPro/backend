package com.discount_pro.web_service.letter_discount_operetions.application.internal.commandservices;

import com.discount_pro.web_service.letter_discount_operetions.application.internal.outboundservices.acl.ExternalLetraService;
import com.discount_pro.web_service.letter_discount_operetions.domain.model.aggregates.LetterDiscountOperation;
import com.discount_pro.web_service.letter_discount_operetions.domain.model.commands.CreateLetterDiscountOperationCommand;
import com.discount_pro.web_service.letter_discount_operetions.domain.model.commands.DeleteLetterDiscountOperationCommand;
import com.discount_pro.web_service.letter_discount_operetions.domain.model.commands.UpdateLetterDiscountOperationCommand;
import com.discount_pro.web_service.letter_discount_operetions.domain.services.LetterDiscountOperationCommandService;
import com.discount_pro.web_service.letter_discount_operetions.infrastructure.persistence.jpa.repositories.LetterDiscountOperationRepository;
import org.springdoc.core.converters.SortOpenAPIConverter;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class LetterDiscountOperationCommandServiceImpl implements LetterDiscountOperationCommandService {

    private final LetterDiscountOperationRepository letterDiscountOperationRepository;
    private final ExternalLetraService externalLetraService;
    private final SortOpenAPIConverter sortOpenAPIConverter;

    public LetterDiscountOperationCommandServiceImpl(LetterDiscountOperationRepository letterDiscountOperationRepository, ExternalLetraService externalLetraService, SortOpenAPIConverter sortOpenAPIConverter) {
        this.letterDiscountOperationRepository = letterDiscountOperationRepository;
        this.externalLetraService = externalLetraService;
        this.sortOpenAPIConverter = sortOpenAPIConverter;
    }

    @Override
    public Long handle(CreateLetterDiscountOperationCommand command) {
        var letterIdVar = command.letterId();
        Long letterId = Long.valueOf(letterIdVar);

        var descontDate = command.discountDate();
        var endDate= externalLetraService.fetchFechaVencimientoByLetraDePagoId(letterId);
        //System.out.println("End Date: " + endDate);
        // calcular los dias entre la fecha de descuento y la fecha de vencimiento
        Long daysBetween = ChronoUnit.DAYS.between(descontDate, endDate.get());
        //System.out.println("Days Between: " + daysBetween);
        // calcular la nueva TEP
        // TEP2 = (1 + TEP1)^(daysBetween/360) - 1
        //TEP1
        var tasaInteresPorce = externalLetraService.featTasaInteresPorceByLetraDePagoId(letterId);
        double tep1= tasaInteresPorce.get().doubleValue();
        //System.out.println("Tasa Interes Porce: " + tasaInteresPorce);
        //---------------------------------------------------------------------------------------------------------
        //llamar la funcion de la clase LetterDiscountOperation para calcualr el TEP2
        double tep2Double = LetterDiscountOperation.calculateTep(tep1, daysBetween);
       // System.out.println("Tep2: " + tep2Double);

        //llamar la funcion de la clase LetterDiscountOperation para calcular el descuento
        double discount = LetterDiscountOperation.calculateDiscount(tep2Double);
        //System.out.println("Discount: " + discount);

        var letterDiscountOperation = new LetterDiscountOperation(command.letterId(), command.entidadBancariaId(), discount, command.discountDate());
        try {
            letterDiscountOperationRepository.save(letterDiscountOperation);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving letter discount operation: " + e.getMessage());
        }
        return letterDiscountOperation.getId();
    }

    @Override
    public Optional<LetterDiscountOperation> handle(UpdateLetterDiscountOperationCommand command) {
        var letterIdVar = command.letterId();
        Long letterId = Long.valueOf(letterIdVar);

        var descontDate = command.discountDate();
        var endDate= externalLetraService.fetchFechaVencimientoByLetraDePagoId(letterId);
        // calcular los dias entre la fecha de descuento y la fecha de vencimiento
        Long daysBetween = ChronoUnit.DAYS.between(descontDate, endDate.get());
        // calcular la nueva TEP
        // TEP2 = (1 + TEP1)^(daysBetween/360) - 1
        //TEP1
        var tasaInteresPorce = externalLetraService.featTasaInteresPorceByLetraDePagoId(letterId);
        double tep1= tasaInteresPorce.get().doubleValue();
        //---------------------------------------------------------------------------------------------------------
        //llamar la funcion de la clase LetterDiscountOperation para calcualr el TEP2
        double tep2Double = LetterDiscountOperation.calculateTep(tep1, daysBetween);

        //llamar la funcion de la clase LetterDiscountOperation para calcular el descuento
        double discount = LetterDiscountOperation.calculateDiscount(tep2Double);
        var letterDiscountOperationToUpdate = letterDiscountOperationRepository.findById(command.letterDiscountOperationId()).get();
        letterDiscountOperationToUpdate.updateInformation(command.letterId(), command.entidadBancariaId(), discount, command.discountDate());
        try {
            var updatedLetterDiscountOperation = letterDiscountOperationRepository.save(letterDiscountOperationToUpdate);
            return Optional.of(updatedLetterDiscountOperation);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating letter discount operation: " + e.getMessage());
        }

    }

    @Override
    public void handle(DeleteLetterDiscountOperationCommand command) {
        var letterDiscountOperationId = command.letterDiscountOperationId();
        if (!letterDiscountOperationRepository.existsById(letterDiscountOperationId)) {
            throw new IllegalArgumentException("Letter discount operation with id " + letterDiscountOperationId + " does not exist");
        }
        try {
            letterDiscountOperationRepository.deleteById(letterDiscountOperationId);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting letter discount operation: " + e.getMessage());
        }

    }
}
