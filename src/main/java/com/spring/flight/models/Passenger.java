package com.spring.flight.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Passenger extends BaseClass {


    private String name;
    private Integer age;
    private String passportNumber;

    @OneToMany
    private List<Booking> bookings;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", passportNumber='" + passportNumber + '\'' +
                ", bookings=" + bookings +
                '}';
    }
}
