package com.andreyshlyahtovich.jwtrestexample.payroll.advice;

import com.andreyshlyahtovich.jwtrestexample.payroll.exception.WalletNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class WalletNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(WalletNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(WalletNotFoundException ex) {
        return ex.getMessage();
    }
}
