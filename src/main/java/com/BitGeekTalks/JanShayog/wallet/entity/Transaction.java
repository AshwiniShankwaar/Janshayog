package com.BitGeekTalks.JanShayog.wallet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @SequenceGenerator(
            name = "transaction_seq",
            sequenceName = "transaction_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "transaction_seq"
    )
    @Column(name = "transactionId")
    private long transactionId;
    private double amount;
    private LocalDate date=LocalDate.now();
    private LocalTime time = LocalTime.now();
    private String reason;
    private boolean isDebit;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "walletId")
    @JsonIgnore
    private Wallet walletId;
    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", amount=" + amount +
                ", date=" + date +
                ", time=" + time +
                ", reason='" + reason + '\'' +
                ", isDebit=" + isDebit +
                '}';
    }

    public void performTransaction(Wallet wallet) {
        if (getIsDebit()) {
            wallet.setBalance(wallet.getBalance() - amount);
        } else {
            wallet.setBalance(wallet.getBalance() + amount);
        }
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean getIsDebit() {
        return isDebit;
    }

    public void setIsDebit(boolean debit) {
        this.isDebit = debit;
    }

    public Wallet getWalletId() {
        return walletId;
    }

    public void setWalletId(Wallet walletId) {
        this.walletId = walletId;
    }

    public Transaction() {
    }

    public Transaction(long transactionId, double amount, LocalDate date, LocalTime time, String reason, boolean isDebit, Wallet walletId) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.date = date;
        this.time = time;
        this.reason = reason;
        this.isDebit = isDebit;
        this.walletId = walletId;
    }
}
