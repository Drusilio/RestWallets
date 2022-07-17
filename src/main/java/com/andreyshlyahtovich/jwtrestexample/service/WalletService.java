package com.andreyshlyahtovich.jwtrestexample.service;

import com.andreyshlyahtovich.jwtrestexample.model.Wallet;

import java.util.List;

public interface WalletService {

    List<Wallet> getAll();
    Wallet getById(Long id);
    Wallet save(Wallet wallet);
    Wallet replace(Long id, Wallet newWallet);
    void delete(Long id);
    void addUserToWallet(Long walletId, Long userId);
}
