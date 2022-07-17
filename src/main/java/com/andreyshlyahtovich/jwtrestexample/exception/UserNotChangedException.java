package com.andreyshlyahtovich.jwtrestexample.exception;

import com.andreyshlyahtovich.jwtrestexample.model.User;

public class UserNotChangedException extends RuntimeException{
    public UserNotChangedException(User user) {
        super("Could not change user: " + user);
    }
}
