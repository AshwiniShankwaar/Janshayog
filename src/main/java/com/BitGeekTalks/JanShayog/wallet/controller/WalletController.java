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
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
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
            //System.out.println(wallet);
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
    public ResponseEntity<Map<String,Object>> getAllTransactions(@PathVariable Long walletId){
        Wallet wallet = walletService.getWallet(walletId);
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("wallet",wallet));
    }

    @GetMapping("/walletData")
    public ResponseEntity<Map<String,Object>> getWalletInfo(@RequestParam("id") Long accountId){
        Wallet wallet = walletService.walletByAccountId(accountId);
        if(wallet == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("message","Invalid Account Id"));
        }else{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Collections.singletonMap("wallet",wallet));
        }
    }
}
