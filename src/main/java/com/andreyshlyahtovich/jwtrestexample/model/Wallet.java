package com.andreyshlyahtovich.jwtrestexample.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "wallets")
@Data
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "amount")
    private double amount;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @ManyToMany(mappedBy = "participantsWallets", fetch = FetchType.LAZY)
    private List<User> participantsList;

    public Wallet() {
    }
}
