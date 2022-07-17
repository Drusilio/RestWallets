package com.andreyshlyahtovich.jwtrestexample.controller.advice;

import com.andreyshlyahtovich.jwtrestexample.exception.CurrencyNotCreatedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CurrencyNotCreatedAdvice {
    @ResponseBody
    @ExceptionHandler(CurrencyNotCreatedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String currencyNotFoundHandler(CurrencyNotCreatedException ex) {
        return ex.getMessage();
    }
}
