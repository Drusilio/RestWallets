package com.andreyshlyahtovich.jwtrestexample.service;

import com.andreyshlyahtovich.jwtrestexample.exception.CurrencyNotChangedException;
import com.andreyshlyahtovich.jwtrestexample.exception.CurrencyNotCreatedException;
import com.andreyshlyahtovich.jwtrestexample.model.Currency;
import com.andreyshlyahtovich.jwtrestexample.exception.CurrencyNotFoundException;
import com.andreyshlyahtovich.jwtrestexample.repository.CurrencyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CurrentServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    public CurrentServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public List<Currency> getAll() {
        return currencyRepository.findAll();
    }

    @Override
    public Currency getById(Long id) {
        return currencyRepository.findById(id).orElseThrow(() -> new CurrencyNotFoundException(id));
    }

    @Override
    public Currency save(Currency newCurrency) {
        try {
            return currencyRepository.save(newCurrency);
        } catch (Exception e) {
            throw new CurrencyNotCreatedException(newCurrency);
        }
    }

    @Override
    public Currency replace(Long id, Currency newCurrency) {
        try {
            return currencyRepository.findById(id)
                    .map(currency -> {
                        currency.setName(newCurrency.getName());
                        return currencyRepository.save(currency);
                    })
                    .orElseGet(() -> {
                        newCurrency.setId(id);
                        return save(newCurrency);
                    });
        } catch (Exception e) {
            throw new CurrencyNotChangedException(newCurrency);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            currencyRepository.deleteById(id);
        } catch (Exception e) {
            throw new CurrencyNotFoundException(id);
        }
    }
}
