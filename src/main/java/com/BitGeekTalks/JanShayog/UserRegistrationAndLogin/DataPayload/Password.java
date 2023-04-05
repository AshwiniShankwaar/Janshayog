package com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.DataPayload;

public class Password {

    private long accountId;
    private String token;
    private String password;

    @Override
    public String toString() {
        return "Password{" +
                "accountId=" + accountId +
                ", token='" + token + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Password() {
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Password(long accountId, String token, String password) {
        this.accountId = accountId;
        this.token = token;
        this.password = password;
    }
}
