package com.spring.flight.services.impl;

import com.spring.flight.exceptions.NoPassengerFoundException;
import com.spring.flight.exceptions.PassengerNotFoundException;
import com.spring.flight.models.Passenger;
import com.spring.flight.repo.PassengerRepository;
import com.spring.flight.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Override
    public Passenger getPassengerById(Integer id) throws NoPassengerFoundException {
        Optional<Passenger> opt=passengerRepository.findById(id);
        if(opt.isPresent())
        {
            return opt.get();
        }
        else {
            throw new NoPassengerFoundException("No Passenger Found with this ID: "+id);
        }
    }

    @Override
    public void savePassenger(Passenger passenger) throws PassengerNotFoundException {
        if(passenger.getId()==null)
        {
            passengerRepository.save(passenger);
        }
        else {
            Optional<Passenger> opt=passengerRepository.findById(passenger.getId());
            if(opt.isEmpty())
            {
                throw new PassengerNotFoundException("Passenger not found gor given ID: "+passenger.getId());
            }
            Passenger p=opt.get();

            p.setId(passenger.getId());
            p.setName(passenger.getName());
            p.setAge(passenger.getAge());
            p.setPassportNumber(passenger.getPassportNumber());

            passengerRepository.save(p);
        }

    }

    @Override
    public List<Passenger> findAllPassenger() {
        List<Passenger> li=passengerRepository.findAll();
        return li;
    }

    @Override
    public void deletePassengerById(Integer id) throws NoPassengerFoundException {
        if(id==null)
        {
            throw new NoPassengerFoundException("Passenger Cant be Null or Empty");
        }
        if(!passengerRepository.existsById(id))
        {
            throw new NoPassengerFoundException("No passenger found with ID: "+id);
        }
        passengerRepository.deleteById(id);
    }
}
