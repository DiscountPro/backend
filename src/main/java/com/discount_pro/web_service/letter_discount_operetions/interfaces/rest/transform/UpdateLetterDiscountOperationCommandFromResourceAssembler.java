package com.discount_pro.web_service.letter_discount_operetions.interfaces.rest.transform;

import com.discount_pro.web_service.letter_discount_operetions.domain.model.commands.UpdateLetterDiscountOperationCommand;
import com.discount_pro.web_service.letter_discount_operetions.interfaces.rest.resources.LetterDiscountOperationResource;

public class UpdateLetterDiscountOperationCommandFromResourceAssembler {
    public static UpdateLetterDiscountOperationCommand toCommandFromResource(Long letterDiscountOperationId, LetterDiscountOperationResource resource) {
        return new UpdateLetterDiscountOperationCommand(letterDiscountOperationId, resource.letterId(), resource.entidadBancariaId(), resource.discountDate());
    }
}
