package com.andreyshlyahtovich.jwtrestexample.payroll.exception;

public class WalletNotFoundException extends RuntimeException{
    public WalletNotFoundException(Long id) {
        super("Could not find wallet " + id);
    }
}
