package com.andreyshlyahtovich.jwtrestexample.payroll;

import com.andreyshlyahtovich.jwtrestexample.model.Currency;
import com.andreyshlyahtovich.jwtrestexample.repository.CurrencyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDataBase(CurrencyRepository currencyRepository) {
        return args -> {
            currencyRepository.save(new Currency(0, "BYN"));
            currencyRepository.save(new Currency(0, "USD"));
        };
    }
}
