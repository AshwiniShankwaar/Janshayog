package com.BitGeekTalks.JanShayog.Request.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Task",uniqueConstraints = {
        @UniqueConstraint(
                name = "Task_unique",
                columnNames = {"taskId"}
        )
})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "taskId")
public class Task {
    @Id
    @SequenceGenerator(
            name = "task_seq",
            sequenceName = "task_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_seq"
    )
    @Column(name = "taskId")
    private long taskId;
    @NotNull
    private String title;
    @NotNull
    private LocalDate date;
    @NotNull
    private LocalTime time;
    @NotNull
    private String address;
    @NotNull
    private String timePeriod;
    @NotNull
    private long numberOfPeople;
    @NotNull
    private String skills;
    @NotNull
    private double amount;
    @NotNull
    private String description;
    @NotNull
    private boolean legal;
    @NotNull
    private boolean agreed;

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", address='" + address + '\'' +
                ", timePeriod='" + timePeriod + '\'' +
                ", NoOfPeople='" + numberOfPeople + '\'' +
                ", skills='" + skills + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", illegal=" + legal +
                ", agreed=" + agreed +
                '}';
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
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

    public long getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(long numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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

    public boolean getAgreed() {
        return agreed;
    }

    public void setAgreed(boolean agreed) {
        this.agreed = agreed;
    }

    public Task() {
    }

    public Task(long taskId, String title, LocalDate date, LocalTime time, String address, String timePeriod, long numberOfPeople, String skills, double amount, String description, boolean legal, boolean agreed) {
        this.taskId = taskId;
        this.title = title;
        this.date = date;
        this.time = time;
        this.address = address;
        this.timePeriod = timePeriod;
        this.numberOfPeople = numberOfPeople;
        this.skills = skills;
        this.amount = amount;
        this.description = description;
        this.legal = legal;
        this.agreed = agreed;
    }
}
