package com.andreyshlyahtovich.jwtrestexample.service;

import com.andreyshlyahtovich.jwtrestexample.model.Wallet;
import com.andreyshlyahtovich.jwtrestexample.model.Wallet;
import com.andreyshlyahtovich.jwtrestexample.payroll.exception.WalletNotFoundException;
import com.andreyshlyahtovich.jwtrestexample.repository.WalletRepository;
import com.andreyshlyahtovich.jwtrestexample.repository.WalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class WalletServiceImpl implements WalletService{


    private final WalletRepository walletRepository;

    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }


    @Override
    public List<Wallet> getAll() {
        return walletRepository.findAll();
    }

    @Override
    public Wallet getById(Long id) {
        return walletRepository.findById(id).orElseThrow(() -> new WalletNotFoundException(id));
    }

    @Override
    public Wallet save(Wallet newWallet) {
        return walletRepository.save(newWallet);
    }

    @Override
    public Wallet replace(Long id, Wallet newWallet) {
        return walletRepository.findById(id)
                .map(wallet -> {
                    wallet.setName(newWallet.getName());
                    wallet.setAmount(newWallet.getAmount());
                    wallet.setCurrency(newWallet.getCurrency());
                    return walletRepository.save(wallet);
                })
                .orElseGet(() -> {
                    newWallet.setId(id);
                    return walletRepository.save(newWallet);
                });
    }

    @Override
    public void delete(Long id) {
        walletRepository.deleteById(id);
    }
}
