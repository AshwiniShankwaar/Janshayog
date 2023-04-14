package com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmailServiceTest {
    @Autowired
    EmailService emailService;
    @Test
    void testEmailService() {
        try{
            emailService.sendVerificationEmail(
                    "academyforbrilliance99@gmail.com",
                    "testing",
                    "Email sending verification test"
            );
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}