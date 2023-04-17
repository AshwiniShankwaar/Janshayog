package com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.Controller;

import com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.DataPayload.Password;
import com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.entity.AccountCreation;
import com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.DataPayload.LogInDetails;
import com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.service.AccountCreationService;
import com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/account")
@CrossOrigin
public class AccountCreationController {

    @Autowired
    private AccountCreationService accountCreationService;
    @Autowired
    private EmailService emailService;

    @GetMapping("/test")
    public String check(){
        return "Welcome to JanShayog";
    }
    @PostMapping("/changepassword")
    public ResponseEntity<String> changePassword(
            @RequestParam("id") long id,
            @RequestParam("newpassword") String newpassword,
            @RequestParam("oldpassword") String oldpassword
            ) {
        if(newpassword!=null && newpassword!=oldpassword){
        accountCreationService.changePassword(id, newpassword, oldpassword);
        return ResponseEntity.status(HttpStatus.OK).body("Password Changed Successfully");}
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                  .body("Please Provide different old password and new password but new password should not be null");
        }
    }
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> Login(
            @RequestBody LogInDetails logInDetails
            ){
        AccountCreation accountCreation = accountCreationService.getAccount(
                logInDetails.getEmail_id(),
                logInDetails.getPassword());
        if(accountCreation == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("message", "User not found"));
        }else{
            if(accountCreation.getAccountType().equals(logInDetails.getType())){
                if(accountCreation.getVerified().equals("verified")){
                    if(accountCreation.getAccountStatus().equals("Deactivated")){
                        System.out.println(accountCreation.getAccountStatus());
                        accountCreation.setAccountStatus("Active");
                        accountCreation = accountCreationService.UpdateAccount(accountCreation);
                    }
                    Map<String, Object> response = new HashMap<>();
                    response.put("AccountId",accountCreation.getAccountId());
                    response.put("EmailId",accountCreation.getEmailAddress());
                    return ResponseEntity.status(HttpStatus.OK).body(response);
                }
                else{
                    return ResponseEntity.status(HttpStatus.FORBIDDEN)
                            .body(Collections.singletonMap("message","Not_Verified"));
                }
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Collections.singletonMap("message", "User not found"));
            }
        }
    }

    @PostMapping("/registration")
    public ResponseEntity<Map<String, Object>> CreateAccount(@Valid @RequestBody AccountCreation accountCreation) throws MessagingException {
        System.out.println(accountCreation.toString());
        AccountCreation account = accountCreationService.createAccount(accountCreation);
        if(account == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("message", "There was an error while creating the account"));
        }
        //send email verification mail to the user email address

        String to = accountCreation.getEmailAddress();
        String subject = "Please verify your email address";
        String body = "Click the link to verify your email address: http://localhost:8080/api/account/verify?token=" + accountCreation.getVerificationToken();
        System.out.println(to+" "+subject+" "+body);
        emailService.sendVerificationEmail(to, subject, body);

        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("message", "account created successfully"));
    }


    @GetMapping("/verify")
    public RedirectView verifyAccount(@RequestParam("token") String token){
        AccountCreation accountCreation = accountCreationService.findByAccountVerificationToken(token);
        if(accountCreation == null){
            return new RedirectView("http://localhost:3000/verify?message=invalidToken");
        }else{
            accountCreation.setVerificationToken(null);
            accountCreation.setVerified("verified");
            accountCreationService.createAccount(accountCreation);
            return new RedirectView("http://localhost:3000/verify?message=verified");
        }
    }

    @PostMapping("/forgetPassword")
    public ResponseEntity<Map<String,Object>> forgetPassword(
            @RequestBody Map<String, Object> payload
    ){
        System.out.println((String)payload.get("email"));
        //check whether the email exit in the table or not
        String value = null;
        try {
            value = accountCreationService.forgetPassword((String)payload.get("email"));
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        if(value.equals("success")) {
            return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("success",true));
        }
        //if email exit then send mail for password change and send a success response
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("success",false));
    }

    @PostMapping("/setPassword")
    public ResponseEntity<Map<String,Object>> setPassword(@RequestBody Password password){
        String response = accountCreationService.setNewPassword(password);
        HashMap<String,Object> map = new HashMap<String,Object>();
        if(response.equals("success")){
            map.put("success",true);
            map.put("message","Password updated successfully");
            return ResponseEntity.status(HttpStatus.OK).body(map);
        }else if(response.equals("invalid")){
            map.put("success",false);
            map.put("message","Invalid password change request token");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        }else if(response.equals("sameAsOldPassword")){
            map.put("success",false);
            map.put("message","New password can not be same as old password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        }else{
            map.put("success",false);
            map.put("message","Internal server error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(map);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<Map<String,Object>> checkUser(@RequestParam("accountId") String accountId){
        //System.out.println(accountId);
        String status = accountCreationService.checkverifiedAndDetails(accountId);
//        if(status.equals("not verified")){
//            return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("message",status));
//        }else
        if(status.equals("profile")){
            return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("message",status));
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("message",status));
        }

    }

    @GetMapping("/deactivate")
    public ResponseEntity<Map<String,Object>> deactivateAccount(
            @RequestParam("accountId") String accountId){
        AccountCreation accountCreation = accountCreationService.getAccountData(Long.parseLong(accountId));
        if(accountCreation!=null){
            accountCreation.setAccountStatus("Deactivated");
            AccountCreation updatedAccountData =  accountCreationService.UpdateAccount(accountCreation);
            if(updatedAccountData!=null){
                System.out.println(updatedAccountData);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(Collections.singletonMap("message","Deactivated"));
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Collections.singletonMap("message","Try again later"));
            }
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("message","Account not found"));
        }
    }

}
