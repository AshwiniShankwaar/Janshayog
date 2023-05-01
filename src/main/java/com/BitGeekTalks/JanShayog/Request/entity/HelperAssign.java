package com.BitGeekTalks.JanShayog.Request.entity;

import com.BitGeekTalks.JanShayog.wallet.entity.Wallet;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "HelperAssignment",
uniqueConstraints = {
        @UniqueConstraint(
                name = "HelperAssignment",
                columnNames = "helperAssignmentId"
        )
})
public class HelperAssign {
    @Id
    @SequenceGenerator(
            name = "helperAssign_seq",
            sequenceName = "helperAssign_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "helperAssign_seq"
    )
    @Column(name = "helperAssignmentId")
    private long id;
    @Column(name = "helperId")
    private long accountId;
    private LocalDate date = LocalDate.now();
    private LocalTime time = LocalTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requestId")
    @JsonIgnore
    private Request requestId;

    @Override
    public String toString() {
        return "HelperAssign{" +
                "id=" + id +
                ", accountId=" + accountId +
                ", date=" + date +
                ", time=" + time +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
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

    public Request getRequestId() {
        return requestId;
    }

    public void setRequestId(Request requestId) {
        this.requestId = requestId;
    }

    public HelperAssign() {
    }

    public HelperAssign(long id, long accountId, LocalDate date, LocalTime time, Request requestId) {
        this.id = id;
        this.accountId = accountId;
        this.date = date;
        this.time = time;
        this.requestId = requestId;
    }
}
