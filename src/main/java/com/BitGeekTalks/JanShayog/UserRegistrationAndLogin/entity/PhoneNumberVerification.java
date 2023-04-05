package com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "PhoneNumberVerification")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class PhoneNumberVerification {
    @Id
    @SequenceGenerator(
            name = "NotVerifiedPhoneNumber_seq",
            sequenceName = "NotVerifiedPhoneNumber_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "NotVerifiedPhoneNumber_seq"
    )
    private long id;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "AccountID")
//    @JsonBackReference
    private User AccountId;

    @Column(name = "otp")
    private String otp;

    @Column(name = "GeneratedTime")
    private LocalDateTime generatedTime;

    @Override
    public String toString() {
        return "PhoneNumberVerification{" +
                "id=" + id +
                ", otp='" + otp + '\'' +
                ", generatedTime=" + generatedTime +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getAccountId() {
        return AccountId;
    }

    public void setAccountId(User accountId) {
        AccountId = accountId;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public LocalDateTime getGeneratedTime() {
        return generatedTime;
    }

    public void setGeneratedTime(LocalDateTime generatedTime) {
        this.generatedTime = generatedTime;
    }

    public PhoneNumberVerification() {
    }

    public PhoneNumberVerification(long id, User accountId, String otp, LocalDateTime generatedTime) {
        this.id = id;
        AccountId = accountId;
        this.otp = otp;
        this.generatedTime = generatedTime;
    }
}
