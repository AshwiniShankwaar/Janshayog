package com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;


@Entity
@Table(
        name = "Account",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "Email_unique",
                        columnNames = {"email_address"}
                )
        }
)
public class AccountCreation {
    @SequenceGenerator(
            name = "Account_Id_sequence",
            sequenceName = "Account_Id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Account_Id_sequence"
    )
    @Id
    @Column(
            name = "account_id"
    )
    private long accountId;

    @NotEmpty
    @Column(
            name = "email_address",
            nullable = false
    )
    @Email(message = "Please enter correct email address")
    private String emailAddress;

    @NotEmpty
    @Column(
            name = "password",
            nullable = false
    )
    @Size(min = 8, max = 20, message = "Password length must be between 8 and 20 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$", message = "Password must contain at least one lowercase letter, one uppercase letter, and one digit")
    private String password;

    @NotEmpty
    @Column(
            name = "account_type",
            nullable = false
    )
    private String AccountType;
    @Column(
            name = "account_created_at_date",
            nullable = false
    )
    private LocalDate date = LocalDate.now();
    @Column(
            name = "account_created_at_time",
            nullable = false
    )
    private LocalTime time = LocalTime.now();
    @NotEmpty
    @Column(
            name = "account_status",
            nullable = false
    )
    private String AccountStatus="Active";

    @Column(name = "verificationtoken")
    private String verificationToken;

    @Column(name = "verified")
    private String verified="NotVerified";
    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }


    @Override
    public String toString() {
        return "AccountCreation{" +
                "accountId=" + accountId +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                ", AccountType='" + AccountType + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", AccountStatus='" + AccountStatus + '\'' +
                '}';
    }

    public String getVerificationToken() {
        return verificationToken;
    }
    public void setVerificationToken(String verificationToken) {
        this.verificationToken = verificationToken;
    }

    public String getAccountStatus() {
        return AccountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        AccountStatus = accountStatus;
    }

    public AccountCreation(long accountId,
                           String emailAddress,
                           String password,
                           String accountType,
                           String accountStatus) {
        this.accountId = accountId;
        this.emailAddress = emailAddress;
        this.password = password;
        this.AccountType = accountType;
        this.AccountStatus = accountStatus;
    }

    public String getAccountType() {
        return AccountType;
    }

    public void setAccountType(String accountType) {
        AccountType = accountType;
    }

    public AccountCreation() {
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
