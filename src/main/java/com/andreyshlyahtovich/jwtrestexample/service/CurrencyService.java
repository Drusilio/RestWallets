package com.andreyshlyahtovich.jwtrestexample.service;

import com.andreyshlyahtovich.jwtrestexample.model.Currency;

import java.util.List;

public interface CurrencyService {

    List<Currency> getAll();
    Currency getById(Long id);
    Currency save(Currency currency);
    Currency replace(Long id, Currency newCurrency);
    void delete(Long id);
}
