package com.andreyshlyahtovich.jwtrestexample.repository;

import com.andreyshlyahtovich.jwtrestexample.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
