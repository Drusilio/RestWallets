package com.andreyshlyahtovich.jwtrestexample.exception;

import com.andreyshlyahtovich.jwtrestexample.model.Wallet;

public class WalletNotCreatedException extends RuntimeException{
    public WalletNotCreatedException(Wallet wallet) {
        super("Could not create wallet:" + wallet);
    }
}
