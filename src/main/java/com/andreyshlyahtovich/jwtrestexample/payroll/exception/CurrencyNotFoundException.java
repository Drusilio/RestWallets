package com.andreyshlyahtovich.jwtrestexample.payroll.exception;

public class CurrencyNotFoundException extends RuntimeException{
    public CurrencyNotFoundException(Long id) {
        super("Could not find currency " + id);
    }
}
