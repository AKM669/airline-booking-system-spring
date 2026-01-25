package com.spring.flight.models;

import com.spring.flight.enums.PaymentMode;
import jakarta.persistence.*;

@Entity
public class Payment extends BaseClass {



    private double amount;

    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;

    @OneToOne
    private Booking booking;


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "Payment{" +
                ", amount=" + amount +
                ", paymentMode='" + paymentMode + '\'' +
                ", booking=" + booking +
                '}';
    }
}
