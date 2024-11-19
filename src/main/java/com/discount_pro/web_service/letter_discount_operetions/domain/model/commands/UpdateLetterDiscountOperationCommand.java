package com.discount_pro.web_service.letter_discount_operetions.domain.model.commands;

import java.time.LocalDate;

public record UpdateLetterDiscountOperationCommand(
        Long letterDiscountOperationId,
        Long letterId,
        Long entidadBancariaId,
        LocalDate discountDate
) {
}
