package com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.service;

import com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.DataPayload.Password;
import com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.entity.AccountCreation;
import com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.DataPayload.LogInDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class AccountCreationServiceTest {

    @Autowired
    AccountCreationService accountCreationService;

    @Test
    public void testPasswordChange(){
        accountCreationService.changePassword(3,"Abcdefg@123","Ashwini@786");
    }
    @Test
    public void testAccountCreationService(){
        AccountCreation accountCreation = new AccountCreation();
        accountCreation.setEmailAddress("test2@gmail.com");
        accountCreation.setPassword("Ashwini@123");
        accountCreation.setAccountType("helper");
        accountCreation.setAccountStatus("Active");
        accountCreationService.createAccount(accountCreation);
    }

    @Test
    public void testAccountLogIn(){
        LogInDetails logInDetails = new LogInDetails();
        logInDetails.setEmail_id("test@gmail.com");
        logInDetails.setPassword("Ashwini@123");
        AccountCreation accountId = accountCreationService.getAccount(
                logInDetails.getEmail_id(),
                logInDetails.getPassword()
        );
        System.out.println(accountId);
    }

    @Test
    public void setnewpasswordtest(){
        Password password = new Password();
        password.setAccountId(1);
        password.setPassword("Ashwinishankwaar@786");
        password.setToken("ebaa46b3-7140-4fba-b103-b986edf07106");
        System.out.println(password.toString());
        String passwordset = accountCreationService.setNewPassword(password);
        System.out.println(passwordset);

    }

//    @Test
//    public void tesycount(){
//        long x = accountCreationService.getcount();
//        System.out.println(x);
//    }
}