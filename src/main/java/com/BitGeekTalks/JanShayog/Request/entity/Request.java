package com.BitGeekTalks.JanShayog.Request.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "Request",uniqueConstraints = {
        @UniqueConstraint(
                name = "Request_unique",
                columnNames = {"requestId"}
        )
})
public class Request {
    @Id
    @SequenceGenerator(
            name = "request_seq",
            sequenceName = "request_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "request_seq"
    )
    @Column(name = "requestId")
    private long id;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name="taskId")
    private Task taskId;
    private long accountId;
    private LocalDate date = LocalDate.now();
    private LocalTime time = LocalTime.now();
    private String requestStatus;
    //complete helperassignment underprocessing
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "requestId",cascade = CascadeType.ALL)
    private Payment payment;

    @OneToMany(mappedBy = "requestId", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<HelperAssign> helperAssignments;

    public void addHelper(HelperAssign helperAssign){
        helperAssign.setRequestId(this);
        helperAssignments.add(helperAssign);
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", taskId=" + taskId +
                ", accountId=" + accountId +
                ", date=" + date +
                ", time=" + time +
                ", requestStatus='" + requestStatus + '\'' +
                ", payment=" + payment +
                ", HelperAssignments=" + helperAssignments +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Task getTaskId() {
        return taskId;
    }

    public void setTaskId(Task taskId) {
        this.taskId = taskId;
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

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public List<HelperAssign> getHelperAssignments() {
        return helperAssignments;
    }

    public void setHelperAssignments(List<HelperAssign> helperAssignments) {
        helperAssignments = helperAssignments;
    }

    public Request() {
    }

    public Request(long id, Task taskId, long accountId, LocalDate date, LocalTime time, String requestStatus, Payment payment, List<HelperAssign> helperAssignments) {
        this.id = id;
        this.taskId = taskId;
        this.accountId = accountId;
        this.date = date;
        this.time = time;
        this.requestStatus = requestStatus;
        this.payment = payment;
        helperAssignments = helperAssignments;
    }
}
