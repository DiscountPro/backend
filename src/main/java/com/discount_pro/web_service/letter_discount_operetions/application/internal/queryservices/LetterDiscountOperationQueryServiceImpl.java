package com.discount_pro.web_service.letter_discount_operetions.application.internal.queryservices;

import com.discount_pro.web_service.letter_discount_operetions.domain.model.aggregates.LetterDiscountOperation;
import com.discount_pro.web_service.letter_discount_operetions.domain.model.queries.GetLetterDiscountOperationByIdQuery;
import com.discount_pro.web_service.letter_discount_operetions.domain.services.LetterDiscountOperationQueryService;
import com.discount_pro.web_service.letter_discount_operetions.infrastructure.persistence.jpa.repositories.LetterDiscountOperationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LetterDiscountOperationQueryServiceImpl implements LetterDiscountOperationQueryService {
    private final LetterDiscountOperationRepository letterDiscountOperationRepository;

    public LetterDiscountOperationQueryServiceImpl(LetterDiscountOperationRepository letterDiscountOperationRepository) {
        this.letterDiscountOperationRepository = letterDiscountOperationRepository;
    }


    @Override
    public Optional<LetterDiscountOperation> handle(GetLetterDiscountOperationByIdQuery query) {
        return this.letterDiscountOperationRepository.findById(query.letterDiscountOperationId());
    }
}
