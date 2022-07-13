package com.andreyshlyahtovich.jwtrestexample.repository;

import com.andreyshlyahtovich.jwtrestexample.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
