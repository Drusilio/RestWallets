package com.andreyshlyahtovich.jwtrestexample.controller.advice;

import com.andreyshlyahtovich.jwtrestexample.exception.UserNotChangedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserNotChangedAdvice {
    @ResponseBody
    @ExceptionHandler(UserNotChangedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String userNotFoundHandler(UserNotChangedException ex) {
        return ex.getMessage();
    }
}
