package com.spring.flight.services;

import com.spring.flight.exceptions.*;
import com.spring.flight.models.Airline;
import com.spring.flight.models.Passenger;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PassengerService {

    Passenger getPassengerById(Integer id) throws NoPassengerFoundException;
    void savePassenger(Passenger Passenger) throws PassengerValidationException, PassengerNotFoundException;
    List<Passenger> findAllPassenger();
    void deletePassengerById(Integer id) throws NoPassengerFoundException;

}
