package com.spring.flight.models;

import com.sun.jdi.Value;
import jakarta.persistence.*;

import java.util.List;
@Entity
public class Airline extends BaseClass {

    @Column(unique=true)
    private String name;

    @Column(unique=true)
    private String code;
    private String country;
    private Integer noOfSeats;

    public Integer getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(Integer noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    @OneToMany
    private List<Flight> flights;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    @Override
    public String toString() {
        return "Airline{" +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", country='" + country + '\'' +
                ", noOfSeats=" + noOfSeats +
                ", flights=" + flights +
                '}';
    }
}
