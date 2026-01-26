package com.spring.flight.services.impl;

import com.spring.flight.exceptions.AircraftNotFoundException;
import com.spring.flight.exceptions.FlightNotFoundException;
import com.spring.flight.models.Airline;
import com.spring.flight.models.Flight;
import com.spring.flight.repo.AirlineRepository;
import com.spring.flight.repo.FlightRepository;
import com.spring.flight.services.FlightService;
import com.spring.flight.validators.FlightDataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirlineRepository airlineRepository;

    @Autowired
    private FlightDataValidator flightDataValidator;

    @Override
    public void saveFlight(Flight flight) throws Exception {

        if(flight.getId()==null)
        {
            flightRepository.save(flight);
        }
        else {
            Optional<Flight> opt=flightRepository.findById(flight.getId());
            if(opt.isEmpty())
            {
                throw new FlightNotFoundException("Flight not found gor given ID: "+flight.getId());
            }
            Flight f=opt.get();

            f.setFlightNumber(flight.getFlightNumber());
            f.setSource(flight.getSource());
            f.setDestination(flight.getDestination());
            f.setDepartureTime(flight.getDepartureTime());
            f.setArrivalTime(flight.getArrivalTime());
            f.setAirline(flight.getAirline());
            f.setBaseFare(flight.getBaseFare());
            flightRepository.save(f);
        }

    }

    @Override
    public List<Flight> findAllFlight() {
        List<Flight> list = flightRepository.findAll();
        return list;
    }

    @Override
    public Flight getFlightById(Integer id) throws FlightNotFoundException {
        Optional<Flight> opt = flightRepository.findById(id);
        if (opt.isPresent()) {
            Flight f = opt.get();
            return f;
        } else {
            throw new FlightNotFoundException("No flight Data Found");
        }
    }

    @Override
    public void deleteFlightById(Integer id) throws FlightNotFoundException {
        if (id == null) {
            throw new FlightNotFoundException("Flight id cant be null");
        }

        Optional<Flight> opt = flightRepository.findById(id);

        if (opt.isEmpty()) {
            throw new FlightNotFoundException("No flight Available with this ID: " + id);
        }
        flightRepository.deleteById(id);
    }

    @Override
    public List<Flight> searchFlight(String source, String destination) {
        return flightRepository.findBySourceAndDestination(source, destination);
    }
}
