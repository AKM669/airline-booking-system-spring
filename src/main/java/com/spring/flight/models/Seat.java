package com.spring.flight.models;

import com.spring.flight.enums.SeatClass;
import jakarta.persistence.*;

@Entity
public class Seat extends BaseClass {



    private String seatNumber;

    @Enumerated(EnumType.STRING)
    private SeatClass seatClass;
    private boolean isAvailable;

    @ManyToOne
    private Flight flight;


    public String getSeatNumber() {
        return seatNumber;
    }

    public SeatClass getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(SeatClass seatClass) {
        this.seatClass = seatClass;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }


    @Override
    public String toString() {
        return "Seat{" +
                ", seatNumber='" + seatNumber + '\'' +
                ", seatClass='" + seatClass + '\'' +
                ", isAvailable=" + isAvailable +
                ", flight=" + flight +
                '}';
    }
}
