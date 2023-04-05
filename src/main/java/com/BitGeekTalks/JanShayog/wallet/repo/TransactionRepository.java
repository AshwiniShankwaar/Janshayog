package com.BitGeekTalks.JanShayog.wallet.repo;

import com.BitGeekTalks.JanShayog.wallet.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.walletId.walletId = :walletId")
    List<Transaction> findByWalletId(@Param("walletId") Long walletId);

}

