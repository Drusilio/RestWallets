package com.andreyshlyahtovich.jwtrestexample.exception;

import com.andreyshlyahtovich.jwtrestexample.model.Currency;

public class CurrencyNotChangedException extends RuntimeException{
    public CurrencyNotChangedException(Currency currency) {
        super("Could not change currency: " + currency);
    }
}
