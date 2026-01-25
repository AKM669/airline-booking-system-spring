package com.spring.flight.models;

import com.spring.flight.enums.BookingStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Booking extends BaseClass {



    private LocalDate journeyDate;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @ManyToOne
    private Passenger passenger;

    @ManyToOne
    private Flight flight;

    @OneToOne
    private Payment payment;


    public LocalDate getJourneyDate() {
        return journeyDate;
    }

    public void setJourneyDate(LocalDate journeyDate) {
        this.journeyDate = journeyDate;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Booking{" +
                ", journeyDate=" + journeyDate +
                ", status='" + status + '\'' +
                ", passenger=" + passenger +
                ", flight=" + flight +
                ", payment=" + payment +
                '}';
    }
}
