package com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.Repo;

import com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.entity.ForgetPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForgetPasswordRepo extends JpaRepository<ForgetPassword,Long> {
    ForgetPassword findDistinctByAccountIdAndToken(long accountId, String token);
    long countByTokenAndAccountId(String token,long accountId);
}
