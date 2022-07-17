package com.andreyshlyahtovich.jwtrestexample.exception;

import com.andreyshlyahtovich.jwtrestexample.model.Wallet;

public class WalletNotChangedException extends RuntimeException{
    public WalletNotChangedException(Wallet wallet) {
        super("Could not change wallet: " + wallet);
    }
}
