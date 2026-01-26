package com.spring.flight.services;

import com.spring.flight.exceptions.AircraftNotFoundException;
import com.spring.flight.exceptions.FlightNotFoundException;
import com.spring.flight.models.Flight;
import com.spring.flight.repo.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface FlightService {

    void saveFlight(Flight flight) throws Exception;
    List<Flight> findAllFlight();
    Flight getFlightById(Integer id) throws FlightNotFoundException;
    void deleteFlightById(Integer id) throws FlightNotFoundException;
    List<Flight> searchFlight(String source, String destination);

}
