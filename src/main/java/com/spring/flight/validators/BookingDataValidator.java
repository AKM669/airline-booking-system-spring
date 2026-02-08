package com.spring.flight.validators;

import com.spring.flight.models.Booking;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookingDataValidator implements DataValidator{
    @Override
    public List<String> validate(Object data) {
        Booking booking=(Booking) data;
        List<String> errors=new ArrayList<>();
        if(booking.getTravellerCount() <= 0 ){
            errors.add("Traveller count should be more than OR equal to 1");
        }
        if(booking.getJourneyDate().isBefore(LocalDate.now())){
            errors.add("Journey date should be after today");
        }
        return errors;
    }
}
