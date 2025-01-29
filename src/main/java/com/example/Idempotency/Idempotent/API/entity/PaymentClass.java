package com.example.Idempotency.Idempotent.API.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "payment")
@AllArgsConstructor
@NoArgsConstructor
public class PaymentClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private long transactionId;
    private String userName;
    private double amountPaid;
    private String paymentStatus;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
