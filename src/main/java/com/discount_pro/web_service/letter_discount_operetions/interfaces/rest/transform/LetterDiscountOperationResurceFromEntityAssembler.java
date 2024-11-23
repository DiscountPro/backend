package com.discount_pro.web_service.letter_discount_operetions.interfaces.rest.transform;

import com.discount_pro.web_service.letter_discount_operetions.domain.model.aggregates.LetterDiscountOperation;
import com.discount_pro.web_service.letter_discount_operetions.interfaces.rest.resources.LetterDiscountOperationResource;

public class LetterDiscountOperationResurceFromEntityAssembler {
    public static LetterDiscountOperationResource toResourceFromEntity(LetterDiscountOperation entity){
        return new LetterDiscountOperationResource(entity.getId(), entity.getLetterId(), entity.getEntidadBancariaId(), entity.getDiscountDate());
    }
}
