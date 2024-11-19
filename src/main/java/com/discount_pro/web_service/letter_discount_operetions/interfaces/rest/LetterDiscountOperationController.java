package com.discount_pro.web_service.letter_discount_operetions.interfaces.rest;

import com.discount_pro.web_service.letter_discount_operetions.domain.model.queries.GetLetterDiscountOperationByIdQuery;
import com.discount_pro.web_service.letter_discount_operetions.domain.services.LetterDiscountOperationCommandService;
import com.discount_pro.web_service.letter_discount_operetions.domain.services.LetterDiscountOperationQueryService;
import com.discount_pro.web_service.letter_discount_operetions.interfaces.rest.resources.CreateLetterDiscountOperationResource;
import com.discount_pro.web_service.letter_discount_operetions.interfaces.rest.resources.LetterDiscountOperationResource;
import com.discount_pro.web_service.letter_discount_operetions.interfaces.rest.transform.CreateLetterDiscountOperationCommandFromResourceAssembler;
import com.discount_pro.web_service.letter_discount_operetions.interfaces.rest.transform.LetterDiscountOperationResurceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/letter_discount_operations", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "LetterDiscountOperations", description = "Letter Discount Operations Management Endpoints")
public class LetterDiscountOperationController {
    private final LetterDiscountOperationCommandService letterDiscountOperationCommandService;
    private final LetterDiscountOperationQueryService letterDiscountOperationQueryService;

    public LetterDiscountOperationController(LetterDiscountOperationCommandService letterDiscountOperationCommandService, LetterDiscountOperationQueryService letterDiscountOperationQueryService) {
        this.letterDiscountOperationCommandService = letterDiscountOperationCommandService;
        this.letterDiscountOperationQueryService = letterDiscountOperationQueryService;
    }

    @PostMapping
    public ResponseEntity<LetterDiscountOperationResource> createLetterDiscountOperation(@RequestBody CreateLetterDiscountOperationResource resource) {
        var createLetterDiscountOperationCommand = CreateLetterDiscountOperationCommandFromResourceAssembler
            .toCommandFromResource(resource);
        var letterDiscountOperationId = this.letterDiscountOperationCommandService.handle(createLetterDiscountOperationCommand);

        if (letterDiscountOperationId.equals(0L)) {
            return ResponseEntity.badRequest().build();
        }

        var getLetterDiscountOperationByIdQuery = new GetLetterDiscountOperationByIdQuery(letterDiscountOperationId);
        var optionalLetterDiscountOperation = this.letterDiscountOperationQueryService.handle(getLetterDiscountOperationByIdQuery);

        var letterDiscountOperationResource = LetterDiscountOperationResurceFromEntityAssembler.toResourceFromEntity(optionalLetterDiscountOperation.get());
        return new ResponseEntity<>(letterDiscountOperationResource, HttpStatus.CREATED);
    }
}
