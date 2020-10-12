package com.galid.commerce.domains.order.presentation;

import com.galid.commerce.domains.catalog.service.NotEnoughStockQuantityException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OrderExceptionHandler {
    @ExceptionHandler(NotEnoughStockQuantityException.class)
    public String notEnoughStockQuantityExceptionHandler(NotEnoughStockQuantityException e,
                                                         Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error/error";
    }
}
