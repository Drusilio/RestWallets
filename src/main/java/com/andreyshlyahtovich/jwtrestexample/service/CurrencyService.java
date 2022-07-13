package com.andreyshlyahtovich.jwtrestexample.service;

import com.andreyshlyahtovich.jwtrestexample.model.Currency;

import java.util.List;

public interface CurrencyService {

    Currency register(Currency currency);

    List<Currency> getAll();

    Currency findByName(String name);

    Currency findById(long id);

    void delete(Long id);
}
