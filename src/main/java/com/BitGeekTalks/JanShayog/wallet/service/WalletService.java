package com.BitGeekTalks.JanShayog.wallet.service;

import com.BitGeekTalks.JanShayog.wallet.entity.Transaction;
import com.BitGeekTalks.JanShayog.wallet.entity.Wallet;
import com.BitGeekTalks.JanShayog.wallet.repo.TransactionRepository;
import com.BitGeekTalks.JanShayog.wallet.repo.WalletRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.List;

@Service
public class WalletService {
    @Autowired
    WalletRepo walletRepository;

    @Autowired
    TransactionRepository transactionRepository;
    public Wallet getWalletByAccountId(long id) {
        return walletRepository.findByAccountId(id);
    }

    public Wallet walletByAccountId(long accountId) {
        Wallet getWallet = getWalletByAccountId(accountId);
        if(getWallet==null){
            Wallet wallet = new Wallet();
            wallet.setAccountId(1);
            wallet.setBalance(0.00);

            getWallet =createWallet(wallet);
        }
        return getWallet;
    }
    public Wallet createWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    public Wallet performTransaction(Wallet wallet, double amount, String reason, boolean isDebit) {
        // create a new transaction
        if(wallet.getBalance() <= 0 && isDebit){
            return null;
        }else if(wallet.getBalance() < amount && isDebit){
            return null;
        }
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setReason(reason);
        transaction.setIsDebit(isDebit);
        transaction.setWalletId(wallet);
        // perform the transaction
        wallet.performTransaction(transaction);

        // update the wallet in the database
        return walletRepository.save(wallet);
    }

    public List<Transaction> getAllTransactions(long walletId) {
        List<Transaction> transactionsList = transactionRepository.findByWalletId(walletId);
        return transactionsList;
    }
}
