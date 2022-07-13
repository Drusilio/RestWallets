package com.andreyshlyahtovich.jwtrestexample.service;

import com.andreyshlyahtovich.jwtrestexample.model.User;
import com.andreyshlyahtovich.jwtrestexample.model.Wallet;

import java.util.List;

public interface WalletService {

    Wallet register(Wallet wallet);

    List<Wallet> getAll();

    Wallet findByName(String name);

    Wallet findById(long id);

    void delete(Long id);
}
