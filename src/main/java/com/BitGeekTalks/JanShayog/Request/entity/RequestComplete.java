package com.BitGeekTalks.JanShayog.Request.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "RequestCompleteOtp")
public class RequestComplete {
    @SequenceGenerator(
            name = "RequestComplete_seq",
            sequenceName = "RequestComplete_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "RequestComplete_seq"
    )
    @Id
    private long id;
    private long requestId;
    private String otp;
    private LocalDate date = LocalDate.now();
    private LocalTime time = LocalTime.now();

    @Override
    public String toString() {
        return "RequestComplete{" +
                "id=" + id +
                ", requestId=" + requestId +
                ", otp=" + otp +
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

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
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

    public RequestComplete() {
    }

    public RequestComplete(long id, long requestId, String otp, LocalDate date, LocalTime time) {
        this.id = id;
        this.requestId = requestId;
        this.otp = otp;
        this.date = date;
        this.time = time;
    }
}
