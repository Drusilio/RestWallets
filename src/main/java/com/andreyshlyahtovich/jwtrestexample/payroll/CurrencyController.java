package com.andreyshlyahtovich.jwtrestexample.payroll;

import com.andreyshlyahtovich.jwtrestexample.model.Currency;
import com.andreyshlyahtovich.jwtrestexample.payroll.exception.CurrencyNotFoundException;
import com.andreyshlyahtovich.jwtrestexample.repository.CurrencyRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CurrencyController {

    private final CurrencyRepository currencyRepository;

    public CurrencyController(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @GetMapping("/currencies")
    public List<Currency> all() {
        return currencyRepository.findAll();
    }

    @GetMapping("/currencies/{id}")
    public Currency one(@PathVariable Long id) {
        return currencyRepository.findById(id).orElseThrow(() -> new CurrencyNotFoundException(id));
    }

    @PostMapping("/currencies")
    Currency newCurrency(@RequestBody Currency newCurrency) {
        return currencyRepository.save(newCurrency);
    }

    @PutMapping("/currencies/{id}")
    Currency replaceCurrency(@RequestBody Currency newCurrency, @PathVariable Long id) {

        return currencyRepository.findById(id)
                .map(currency -> {
                    currency.setName(newCurrency.getName());
                    return currencyRepository.save(currency);
                })
                .orElseGet(() -> {
                    newCurrency.setId(id);
                    return currencyRepository.save(newCurrency);
                });
    }

   ; @DeleteMapping("/currencies/{id}")
        void deleteCurrency(@PathVariable Long id) {
            currencyRepository.deleteById(id);
    }
}
