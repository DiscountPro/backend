package com.discount_pro.web_service.letter_discount_operetions.interfaces.rest.transform;

import com.discount_pro.web_service.letter_discount_operetions.domain.model.commands.CreateLetterDiscountOperationCommand;
import com.discount_pro.web_service.letter_discount_operetions.interfaces.rest.resources.CreateLetterDiscountOperationResource;

public class CreateLetterDiscountOperationCommandFromResourceAssembler {
    public static CreateLetterDiscountOperationCommand toCommandFromResource(CreateLetterDiscountOperationResource resource){
        return new CreateLetterDiscountOperationCommand(
                resource.letterId(),
                resource.entidadBancariaId(),
                resource.discountDate()
        );
    }
}
