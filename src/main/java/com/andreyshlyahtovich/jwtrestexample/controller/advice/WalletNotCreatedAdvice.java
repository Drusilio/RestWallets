package com.andreyshlyahtovich.jwtrestexample.controller.advice;

import com.andreyshlyahtovich.jwtrestexample.exception.WalletNotCreatedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class WalletNotCreatedAdvice {
    @ResponseBody
    @ExceptionHandler(WalletNotCreatedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String walletNotFoundHandler(WalletNotCreatedException ex) {
        return ex.getMessage();
    }
}
