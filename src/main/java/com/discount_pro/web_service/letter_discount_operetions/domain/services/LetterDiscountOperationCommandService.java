package com.discount_pro.web_service.letter_discount_operetions.domain.services;

import com.discount_pro.web_service.letter_discount_operetions.domain.model.aggregates.LetterDiscountOperation;
import com.discount_pro.web_service.letter_discount_operetions.domain.model.commands.CreateLetterDiscountOperationCommand;
import com.discount_pro.web_service.letter_discount_operetions.domain.model.commands.DeleteLetterDiscountOperationCommand;
import com.discount_pro.web_service.letter_discount_operetions.domain.model.commands.UpdateLetterDiscountOperationCommand;

import java.util.Optional;

public interface LetterDiscountOperationCommandService {
    Long handle(CreateLetterDiscountOperationCommand command);
    Optional<LetterDiscountOperation> handle(UpdateLetterDiscountOperationCommand command);
    void handle(DeleteLetterDiscountOperationCommand command);

}
