package com.andreyshlyahtovich.jwtrestexample.service;

import com.andreyshlyahtovich.jwtrestexample.exception.WalletNotChangedException;
import com.andreyshlyahtovich.jwtrestexample.exception.WalletNotCreatedException;
import com.andreyshlyahtovich.jwtrestexample.model.User;
import com.andreyshlyahtovich.jwtrestexample.model.Wallet;
import com.andreyshlyahtovich.jwtrestexample.exception.UserNotFoundException;
import com.andreyshlyahtovich.jwtrestexample.exception.WalletNotFoundException;
import com.andreyshlyahtovich.jwtrestexample.repository.UserRepository;
import com.andreyshlyahtovich.jwtrestexample.repository.WalletRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@Slf4j
public class WalletServiceImpl implements WalletService{

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    public WalletServiceImpl(WalletRepository walletRepository, UserRepository userRepository) {
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
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
        try {
                return walletRepository.save(newWallet);
             }
        catch (Exception e) {
            throw new WalletNotCreatedException(newWallet);
        }
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
        try{
            walletRepository.deleteById(id);
        } catch (Exception e) {
            throw new WalletNotFoundException(id);
        }
    }

    @Override
    public void addUserToWallet(Long walletId, Long userId) {
        Wallet wallet = getById(walletId);
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        user.addParticipantsWallet(wallet);
        userRepository.save(user);
    }
}
