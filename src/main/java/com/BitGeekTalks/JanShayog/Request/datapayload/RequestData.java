package com.BitGeekTalks.JanShayog.Request.datapayload;

import java.time.LocalDate;
import java.time.LocalTime;

public class RequestData {
    private long accountId;
    private String title;
    private LocalDate date;
    private LocalTime time;
    private String address;
    private String timePeriod;
    private long noPeople;
    private String skills;
    private double payableAmount;
    private String description;
    private boolean legal;
    private boolean agree;

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }

    public long getNoPeople() {
        return noPeople;
    }

    public void setNoPeople(long noPeople) {
        this.noPeople = noPeople;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public double getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(double payableAmount) {
        this.payableAmount = payableAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getLegal() {
        return legal;
    }

    public void setLegal(boolean legal) {
        this.legal = legal;
    }

    public boolean getAgree() {
        return agree;
    }

    public void setAgree(boolean agree) {
        this.agree = agree;
    }

    public RequestData() {
    }

    public RequestData(long accountId, String title, LocalDate date, LocalTime time, String address, String timePeriod, long noPeople, String skills, double payableAmount, String description, boolean illegal, boolean agree) {
        this.accountId = accountId;
        this.title = title;
        this.date = date;
        this.time = time;
        this.address = address;
        this.timePeriod = timePeriod;
        this.noPeople = noPeople;
        this.skills = skills;
        this.payableAmount = payableAmount;
        this.description = description;
        this.legal = illegal;
        this.agree = agree;
    }
}
