package com.spring.flight.validators;

import com.spring.flight.models.Flight;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FlightDataValidator implements DataValidator{
    @Override
    public List<String> validate(Object data) {
        Flight flight=(Flight) data;
        List<String> error=new ArrayList<>();
        return error;
    }
}
