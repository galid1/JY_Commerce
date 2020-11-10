package com.galid.commerce.common.config.advice;

import com.galid.commerce.domains.catalog.domain.review.NotOrderedProductReviewException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalRestControllerAdvice {
    @ExceptionHandler(value = {NotOrderedProductReviewException.class})
    public ResponseEntity handleRestControllerException(Exception e) {
        return ResponseEntity.badRequest()
                .body(e.getMessage());
    }
}
