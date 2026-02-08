package com.spring.flight.services.impl;

import com.spring.flight.exceptions.AircraftNotFoundException;
import com.spring.flight.exceptions.NoAircraftFoundException;
import com.spring.flight.models.Airline;
import com.spring.flight.repo.AirlineRepository;
import com.spring.flight.services.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirlineServiceImpl implements AirlineService {

    @Autowired
    private AirlineRepository airlineRepository;

    @Override
    public Airline getAirlineById(Integer id) throws NoAircraftFoundException {
        Optional<Airline> opt=airlineRepository.findById(id);
        if(opt.isPresent())
        {
            return opt.get();
        }
        else {
            throw new NoAircraftFoundException("No Aircraft Found with Id: "+id);
        }
    }

    @Override
    public void saveAirline(Airline airline) throws Exception{
        if(airline.getId()==null)
        {
            airline.setCode(airline.getCode().toUpperCase());
            airlineRepository.save(airline);
        }
        else {
            Optional<Airline> opt=airlineRepository.findById(airline.getId());
            if(opt.isEmpty())
            {
                throw new AircraftNotFoundException("Airline not found gor given ID: "+airline.getId());
            }
            Airline a=opt.get();

            a.setName(airline.getName());
            a.setCode(airline.getCode());
            a.setCountry(airline.getCountry());
            a.setNoOfSeats(airline.getNoOfSeats());

            airlineRepository.save(a);
        }
    }

    @Override
    public List<Airline> findAllAirline() {
        List<Airline> list=airlineRepository.findAll();
        return list;
    }


    @Override
    public void deleteAirlineById(Integer id) throws NoAircraftFoundException {

        if (id == null)
        {
            throw new NoAircraftFoundException("Airline Id can not be null");
        }
        if(!airlineRepository.existsById(id))
        {
            throw new NoAircraftFoundException("No Airline found with ID: "+id);
        }
        airlineRepository.deleteById(id);
    }
}
