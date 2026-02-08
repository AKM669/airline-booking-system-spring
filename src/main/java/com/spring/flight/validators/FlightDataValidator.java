package com.spring.flight.validators;

import com.spring.flight.models.Flight;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class FlightDataValidator implements DataValidator{
    @Override
    public List<String> validate(Object data) {
        List<String> errors = new ArrayList<>();
        Flight flight = (Flight) data;
        if (flight.getDestination().equalsIgnoreCase(flight.getSource())) {
            errors.add("Source & Destination can't be same");
        }
        if (flight.getDepartureTime().isBefore(LocalDateTime.now())) {
            errors.add("Departure time should be future time");
        }
        if (flight.getArrivalTime().isBefore(flight.getDepartureTime())) {
            errors.add("Arrival time should be after departure time");
        }
        if(flight.getBaseFare() <= 0 ){
            errors.add("Base fare should be greater than 0");
        }
        if(flight.getSeatsAvailable()!=flight.getAirline().getNoOfSeats())
        {
            errors.add("Seats must be same while editing");
        }
        return errors;
    }
}
