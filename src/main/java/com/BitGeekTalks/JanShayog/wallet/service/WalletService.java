package com.BitGeekTalks.JanShayog.wallet.service;

import com.BitGeekTalks.JanShayog.wallet.entity.Transaction;
import com.BitGeekTalks.JanShayog.wallet.entity.Wallet;
import com.BitGeekTalks.JanShayog.wallet.repo.WalletRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletService {
    @Autowired
    WalletRepo walletRepository;

    public Wallet getWalletByAccountId(long id) {
        return walletRepository.findByAccountId(id);
    }

    public Wallet walletByAccountId(long accountId) {
        Wallet getWallet = getWalletByAccountId(accountId);
        //System.out.println(getWallet);
        if(getWallet==null){
            Wallet wallet = new Wallet();
            wallet.setAccountId(accountId);
            wallet.setBalance(0.00);

            getWallet =createWallet(wallet);
        }
        return getWallet;
    }
    public Wallet createWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    public Wallet performTransaction(Wallet wallet, double amount, String reason, boolean isDebit) {
        //System.out.println(isDebit);
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
        wallet.addTransaction(transaction);

        // update the wallet in the database
        return walletRepository.save(wallet);
    }

    public Wallet getWallet(long walletId){
        Optional<Wallet> wallet = walletRepository.findById(walletId);
        return wallet.orElse(null);
    }


}
