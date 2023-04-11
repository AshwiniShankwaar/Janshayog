package com.BitGeekTalks.JanShayog.wallet.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wallet",uniqueConstraints = {
        @UniqueConstraint(
                name = "Wallet_unique",
                columnNames = {"walletId"}
        )
})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "walletId")
public class Wallet {
    @Id
    @SequenceGenerator(
            name = "wallet_seq",
            sequenceName = "wallet_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "wallet_seq"
    )
    @Column(name = "walletId")
    private long walletId;
    @NotNull
    private long accountId;
    @NotNull
    private double balance = 0.00;
    @OneToMany(mappedBy = "walletId", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Transaction> transaction;


    @Override
    public String toString() {
        return "Wallet{" +
                "walletId=" + walletId +
                ", accountId=" + accountId +
                ", balance=" + balance +
                ", transaction=" + transaction +
                '}';
    }

    public void addTransaction(Transaction transactions) {
        transactions.performTransaction(this);
        transaction.add(transactions);
        transactions.setWalletId(this);
    }

    //    public void performTransaction(Transaction transaction) {
//        transaction.performTransaction(this);
//        this.setTransaction(transaction);
//        // update the wallet in the database
//    }
    public long getWalletId() {
        return walletId;
    }

    public void setWalletId(long walletId) {
        this.walletId = walletId;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<Transaction> transaction) {
        this.transaction = transaction;
    }

    public Wallet() {
    }

    public Wallet(long walletId, long accountId, double balance, List<Transaction> transaction) {
        this.walletId = walletId;
        this.accountId = accountId;
        this.balance = balance;
        this.transaction = transaction;
    }
}
