package com.andreyshlyahtovich.jwtrestexample.exception;

import com.andreyshlyahtovich.jwtrestexample.model.User;

public class UserNotCreatedException extends RuntimeException{
    public UserNotCreatedException(User user) {
        super("Could not create user:" + user);
    }
}
