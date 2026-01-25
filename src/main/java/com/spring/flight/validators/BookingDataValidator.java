package com.spring.flight.validators;

import com.spring.flight.models.Booking;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookingDataValidator implements DataValidator{
    @Override
    public List<String> validate(Object data) {
        Booking booking=(Booking) data;
        List<String> errors=new ArrayList<>();
        return errors;
    }
}
