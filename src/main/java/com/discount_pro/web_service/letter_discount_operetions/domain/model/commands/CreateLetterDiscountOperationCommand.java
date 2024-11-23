package com.discount_pro.web_service.letter_discount_operetions.domain.model.commands;
import java.time.LocalDate;

///datos primitivos.
public record CreateLetterDiscountOperationCommand(Long letterId,
                                                   Long entidadBancariaId,
                                                   LocalDate discountDate
) { }
