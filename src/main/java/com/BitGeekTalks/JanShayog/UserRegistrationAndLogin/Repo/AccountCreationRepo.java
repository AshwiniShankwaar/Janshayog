package com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.Repo;

import com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.entity.AccountCreation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountCreationRepo extends JpaRepository<AccountCreation,Long> {

    AccountCreation findDistinctByEmailAddress(String username);

    AccountCreation findByVerificationToken(String verificationToken);


}
