package com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.service;

import com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.DataPayload.Password;
import com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.Repo.AccountCreationRepo;
import com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.Repo.ForgetPasswordRepo;
import com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.Repo.PasswordChangeLogRepo;
import com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.entity.AccountCreation;
import com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.entity.ForgetPassword;
import com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.entity.PasswordChangeLog;
import com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.entity.User;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AccountCreationService {

    @Autowired
    private AccountCreationRepo accountCreationRepo;

    @Autowired
    private PasswordChangeLogRepo passwordChangeLogRepo;
    @Autowired
    private EmailService emailService;
    @Autowired
    private ForgetPasswordRepo forgetPasswordRepo;
    @Autowired
    private UserService userService;
    public AccountCreation createAccount(AccountCreation accountCreation) {
        accountCreation.setVerificationToken(UUID.randomUUID().toString());
        AccountCreation accountCreationData = accountCreationRepo.save(accountCreation);
        PasswordChangeLog passwordChange = new PasswordChangeLog();
        passwordChange.setAccount_id(accountCreationData);
        passwordChange.setNewPassword(accountCreation.getPassword());
        addPasswordChangeLog(passwordChange);
        return accountCreationData;
    }

    public AccountCreation getAccountData(long id){
        Optional<AccountCreation> op = accountCreationRepo.findById(id);
        return op.orElse(null);
    }
    public AccountCreation getAccount(String email,String password) {

        AccountCreation accountCreation = accountCreationRepo.findDistinctByEmailAddress(email);
        if(accountCreation!= null && accountCreation.getPassword().equals(password)) {
            //check for the account status and if the account is suspended then inform
            // that the account is suspended and ask for reactivation by sending the mail to activate
            //where the user will send to the account activate page with reactivating token and if token is valid
            //ask for the password once password is verified then activate the account else alert that password
            // is invalid of if token is invalid then alert that the token is invalid.

            return accountCreation;
        }
        return null;
    }
    public void addPasswordChangeLog(PasswordChangeLog passwordChangeLog) {
        System.out.println(passwordChangeLogRepo.save(passwordChangeLog));
    }

    public void changePassword(long id,String newPassword,String oldPaaword){
        AccountCreation accountCreation = accountCreationRepo.findById(id).get();
        accountCreation.setPassword(newPassword);

        PasswordChangeLog passwordChangeLog = new PasswordChangeLog();
        passwordChangeLog.setAccount_id(accountCreation);
        passwordChangeLog.setNewPassword(newPassword);
        passwordChangeLog.setOldPassword(oldPaaword);

        accountCreationRepo.save(accountCreation);
        passwordChangeLogRepo.save(passwordChangeLog);
    }
    public AccountCreation findByAccountVerificationToken(String accountVerificationToken){
        return accountCreationRepo.findByVerificationToken(accountVerificationToken);
    }
    public String forgetPassword(String email) throws MessagingException {
    	AccountCreation account = accountCreationRepo.findDistinctByEmailAddress(email);
    	if(account != null) {
    		long accountId = account.getAccountId();
            String token = UUID.randomUUID().toString();
            ForgetPassword forgetPassword = new ForgetPassword();
            forgetPassword.setAccountId(accountId);
            forgetPassword.setToken(token);
            System.out.println(forgetPassword.toString());
            forgetPasswordRepo.save(forgetPassword);
    		//send email
            String to = account.getEmailAddress();
            String subject = "Set new Password";
            String body = "Click the link to set new password:" +
                    " http://localhost:3000/setPassword?accountId="
                    +accountId +"&&token="
                    + token;
            System.out.println(to+" "+subject+" "+body);
            emailService.sendVerificationEmail(to, subject, body);
    		return "success";
    	}
    	return null;
    }

    public String setNewPassword(Password password){
        ForgetPassword forgetPassword;
        try{
           forgetPassword  = forgetPasswordRepo
                    .findDistinctByAccountIdAndToken(
                            password.getAccountId(),
                            password.getToken());
        }catch (Exception e){
            forgetPassword = null;
        }
        if(forgetPassword != null) {
            Optional<AccountCreation> account = accountCreationRepo.findById(password.getAccountId());
            AccountCreation accountCreation = account.get();
            String currentPassword = accountCreation.getPassword();
            if(currentPassword.equals(password.getPassword())){
                return "sameAsOldPassword";
            }
            changePassword(password.getAccountId(),password.getPassword(),currentPassword);
            forgetPasswordRepo.deleteById(forgetPassword.getId());
            return "success";
        }else{
            return "invalid";
        }
    }

    public String checkverifiedAndDetails(String Id) {
        long accountId = Long.parseLong(Id);
        //check for the account verification
        //if account verified then check the details
        //everything is good then send message dashboard if details is not filled then send to profile else if
        //account not verified then alert to account verification
//        Optional<AccountCreation> op = accountCreationRepo.findById(accountId);
//        AccountCreation accountCreation = op.get();

//        if(accountCreation.getVerified().equals("verified")){
            User user = userService.getUser(accountId);
            //now check for the user details
            if(user!=null){
                return "dashboard";
            }else{
                return "profile";
            }
//        }else{
//            return "not verified";
//        }
    }

    public AccountCreation UpdateAccount(AccountCreation accountCreation){
        return accountCreationRepo.save(accountCreation);
    }
}
