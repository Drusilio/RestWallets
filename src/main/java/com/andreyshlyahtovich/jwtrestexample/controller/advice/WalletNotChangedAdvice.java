package com.andreyshlyahtovich.jwtrestexample.controller.advice;

import com.andreyshlyahtovich.jwtrestexample.exception.WalletNotChangedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class WalletNotChangedAdvice {
    @ResponseBody
    @ExceptionHandler(WalletNotChangedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String walletNotFoundHandler(WalletNotChangedException ex) {
        return ex.getMessage();
    }
}
