package com.BitGeekTalks.JanShayog.wallet.repo;

import com.BitGeekTalks.JanShayog.wallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepo extends JpaRepository<Wallet,Long> {
    Wallet findByAccountId(Long accountId);
}
