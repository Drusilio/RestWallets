package com.andreyshlyahtovich.jwtrestexample.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_wallnets",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "wallet_id"))
    private List<Wallet> participantsWallets;

    @OneToMany(mappedBy = "owner")
    private List<Wallet> ownWallets;

    public void addOwnWallet(Wallet wallet) {
        if(ownWallets == null) {
            ownWallets = new ArrayList<Wallet>();
        }
        ownWallets.add(wallet);
    }

    public void addParticipantsWallet(Wallet wallet) {
        if(participantsWallets == null) {
            participantsWallets = new ArrayList<Wallet>();
        }
        participantsWallets.add(wallet);
    }

}
