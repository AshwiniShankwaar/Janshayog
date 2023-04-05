package com.BitGeekTalks.JanShayog.wallet.controller;

import com.BitGeekTalks.JanShayog.wallet.dataPlayload.TransactionRequest;
import com.BitGeekTalks.JanShayog.wallet.entity.Transaction;
import com.BitGeekTalks.JanShayog.wallet.entity.Wallet;
import com.BitGeekTalks.JanShayog.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {
    @Autowired
    WalletService walletService;

    @PostMapping("/transaction")
    public ResponseEntity<Map<String,Object>> transaction(
            @RequestBody TransactionRequest transactionRequest) {
//        System.out.println(transactionRequest.toString());
        Wallet wallet = walletService.walletByAccountId(
                Long.parseLong(transactionRequest.getAccountId()));

        wallet = walletService.performTransaction(
                wallet,Double.parseDouble(transactionRequest.getAmount()),
                transactionRequest.getReason(),
                transactionRequest.getIsDebit());
        if(wallet != null){
            System.out.println(wallet);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(Collections.singletonMap("wallet",wallet));
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("wallet","low balance"));
        }
//        return ResponseEntity.ok(Collections.singletonMap("wallet","wallet"));
    }

    @GetMapping("/{walletId}")
    public ResponseEntity<List<Transaction>> getAllTransactions(@PathVariable Long walletId){
        List<Transaction> transactionList = walletService.getAllTransactions(walletId);
        Iterator<Transaction> i = transactionList.iterator();
        while (i.hasNext()) {
            System.out.println(i.next());
        }
        return new ResponseEntity<>(transactionList, HttpStatus.OK);
    }
}
