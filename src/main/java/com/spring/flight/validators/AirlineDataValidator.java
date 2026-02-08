package com.spring.flight.validators;

import com.spring.flight.models.Airline;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AirlineDataValidator implements DataValidator{
    @Override
    public List<String> validate(Object data) {
        Airline airline=(Airline) data;
        List<String> errors=new ArrayList<>();
        if(airline.getCode().length()>2)
        {
            errors.add("Airline code must be 2 letter only");
        }
        if(airline.getNoOfSeats()<10 && airline.getNoOfSeats()>250)
        {
            errors.add("Seat will be between 10 to 250 only");
        }

        return errors;
    }
}
