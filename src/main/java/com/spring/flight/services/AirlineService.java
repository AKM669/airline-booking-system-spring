package com.spring.flight.services;

import com.spring.flight.exceptions.AircraftNotFoundException;
import com.spring.flight.exceptions.NoAircraftFoundException;
import com.spring.flight.models.Airline;

import java.util.List;

public interface AirlineService {

    Airline getAirlineById(Integer id) throws AircraftNotFoundException, NoAircraftFoundException;
    void saveAirline(Airline airline) throws Exception;
    List<Airline> findAllAirline() ;
    void deleteAirlineById(Integer id) throws NoAircraftFoundException;

}
