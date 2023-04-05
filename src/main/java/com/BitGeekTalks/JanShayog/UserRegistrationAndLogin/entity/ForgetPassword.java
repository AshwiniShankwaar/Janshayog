package com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ForgetPassword")
public class ForgetPassword {
    @Id
    @SequenceGenerator(
            name = "ForgetPassword_sequence",
            sequenceName = "ForgetPassword_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ForgetPassword_sequence"
    )
    private long id;
    private String token;
    private long accountId;

    @Override
    public String toString() {
        return "ForgetPassword{" +
                "id=" + id +
                ", Token='" + token + '\'' +
                ", accountId=" + accountId +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public ForgetPassword() {
    }

    public ForgetPassword(long id, String token, long accountId) {
        this.id = id;
        this.token = token;
        this.accountId = accountId;
    }
}
