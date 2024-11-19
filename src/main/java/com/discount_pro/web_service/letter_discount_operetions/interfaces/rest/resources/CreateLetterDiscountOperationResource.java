package com.discount_pro.web_service.letter_discount_operetions.interfaces.rest.resources;

import java.time.LocalDate;

public record CreateLetterDiscountOperationResource(Long letterId,
                                                    Long entidadBancariaId,
                                                    LocalDate discountDate
) { }
