package com.spring.flight.validators;

import com.spring.flight.models.Passenger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PassengerDataValidator implements DataValidator{
    @Override
    public List<String> validate(Object data) {
        Passenger passenger=(Passenger) data;
        List<String> errors=new ArrayList<>();

        return errors;
    }
}
