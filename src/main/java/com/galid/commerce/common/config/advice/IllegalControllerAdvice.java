package com.galid.commerce.common.config.advice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
@ControllerAdvice
public class IllegalControllerAdvice {
    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    public String handleError() {
        return "error/error";
    }
}
