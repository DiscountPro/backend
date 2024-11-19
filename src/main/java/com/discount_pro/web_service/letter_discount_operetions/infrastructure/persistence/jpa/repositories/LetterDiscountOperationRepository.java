package com.discount_pro.web_service.letter_discount_operetions.infrastructure.persistence.jpa.repositories;

import com.discount_pro.web_service.letter_discount_operetions.domain.model.aggregates.LetterDiscountOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LetterDiscountOperationRepository extends JpaRepository<LetterDiscountOperation, Long> {

}
