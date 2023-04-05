package com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.DataPayload;

public class LogInDetails {
    private String email_id;
    private String password;
    private String type;

    @Override
    public String toString() {
        return "LogInDetails{" +
                "email_id='" + email_id + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LogInDetails() {
    }

    public LogInDetails(String email_id, String password, String type) {
        this.email_id = email_id;
        this.password = password;
        this.type = type;
    }
}
