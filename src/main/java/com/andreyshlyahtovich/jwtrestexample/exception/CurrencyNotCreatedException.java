package com.andreyshlyahtovich.jwtrestexample.exception;

import com.andreyshlyahtovich.jwtrestexample.model.Currency;

public class CurrencyNotCreatedException extends RuntimeException{
    public CurrencyNotCreatedException(Currency currency) {
        super("Could not create currency:" + currency);
    }
}
