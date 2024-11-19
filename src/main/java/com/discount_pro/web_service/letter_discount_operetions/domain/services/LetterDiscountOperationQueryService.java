package com.discount_pro.web_service.letter_discount_operetions.domain.services;

import com.discount_pro.web_service.letter_discount_operetions.domain.model.aggregates.LetterDiscountOperation;
import com.discount_pro.web_service.letter_discount_operetions.domain.model.queries.GetLetterDiscountOperationByIdQuery;

import java.util.Optional;

public interface LetterDiscountOperationQueryService {
    Optional<LetterDiscountOperation> handle(GetLetterDiscountOperationByIdQuery query);
}
