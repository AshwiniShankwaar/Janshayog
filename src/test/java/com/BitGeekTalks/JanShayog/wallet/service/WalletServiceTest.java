package com.BitGeekTalks.JanShayog.wallet.service;

import com.BitGeekTalks.JanShayog.wallet.entity.Transaction;
import com.BitGeekTalks.JanShayog.wallet.entity.Wallet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class WalletServiceTest {
    @Autowired
    WalletService walletService;
    Wallet getWallet(){
        Wallet wallet = new Wallet();
        wallet.setAccountId(1);
        wallet.setBalance(0.00);

//        Transaction transaction = new Transaction();
//        transaction.setAmount(50.0);
//        transaction.setReason("test");
//        transaction.setDebit(false);
//
//        wallet.setTransaction(transaction);

        return wallet;
    }

    @Test
    void testWalletCreate(){
        Wallet wallet = walletService.createWallet(getWallet());
        System.out.println(wallet);
    }

    @Test
    void testWalletTransaction(){
        Wallet wallet = walletService.walletByAccountId(1);
        wallet = walletService.performTransaction(wallet,20.0,"test",true);
        System.out.println(wallet);
    }

    @Test
    void getAllTransactions(){
        List<Transaction> transactions = walletService.getAllTransactions(1);
        Iterator i = transactions.iterator();
        while(i.hasNext()){
            System.out.println(i.next());
        }
    }
}