package com.BitGeekTalks.JanShayog.Request.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Payment_status",uniqueConstraints = {
        @UniqueConstraint(
                name = "Payment_status_unique",
                columnNames = {"paymentId"}
        )
})
public class Payment {
    @Id
    @SequenceGenerator(
            name = "payment_seq",
            sequenceName = "payment_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "payment_seq"
    )
    @Column(name = "paymentId")
    private long id;
    private double amount;
    private String status = "UNPAID";
    //status can be paid unpaid underprocessing
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "requestId")
    private Request requestId;

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Request getRequestId() {
        return requestId;
    }

    public void setRequestId(Request requestId) {
        this.requestId = requestId;
    }

    public Payment() {
    }

    public Payment(long id, double amount, String status, Request requestId) {
        this.id = id;
        this.amount = amount;
        this.status = status;
        this.requestId = requestId;
    }
}
