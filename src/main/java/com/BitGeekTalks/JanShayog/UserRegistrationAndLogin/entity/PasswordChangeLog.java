package com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Password_changeLog")
public class PasswordChangeLog {
    @Id
    @SequenceGenerator(
            name = "password_change_log_seq",
            sequenceName = "password_change_log_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "password_change_log_seq"
    )
    @Column(name = "Log_id")
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private AccountCreation account_id;

    @Column(
            columnDefinition = "VARCHAR(20) DEFAULT 'default'"
    )
    private String oldPassword;

    private String newPassword;

    private LocalDate changeDate = LocalDate.now();
    private LocalTime changeTime = LocalTime.now();

    public PasswordChangeLog() {
    }

    @Override
    public String toString() {
        return "PasswordChangeLog{" +
                "id=" + id +
                ", account_id=" + account_id +
                ", oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", changeDate=" + changeDate +
                ", changeTime=" + changeTime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountCreation getAccount_id() {
        return account_id;
    }

    public void setAccount_id(AccountCreation account_id) {
        this.account_id = account_id;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public LocalDate getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(LocalDate changeDate) {
        this.changeDate = changeDate;
    }

    public LocalTime getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(LocalTime changeTime) {
        this.changeTime = changeTime;
    }

    public PasswordChangeLog(Long id, AccountCreation account_id, String oldPassword, String newPassword, LocalDate changeDate, LocalTime changeTime) {
        this.id = id;
        this.account_id = account_id;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.changeDate = changeDate;
        this.changeTime = changeTime;
    }
}
