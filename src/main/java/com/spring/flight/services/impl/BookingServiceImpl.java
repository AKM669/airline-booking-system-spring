package com.spring.flight.services.impl;

import com.spring.flight.enums.BookingStatus;
import com.spring.flight.exceptions.BookingNotFoundException;
import com.spring.flight.exceptions.FlightNotFoundException;
import com.spring.flight.exceptions.NoBookingFoundException;
import com.spring.flight.exceptions.NoPassengerFoundException;
import com.spring.flight.models.Flight;
import com.spring.flight.models.Payment;
import com.spring.flight.services.BookingService;
import com.spring.flight.models.Booking;
import com.spring.flight.repo.BookingRepository;
import com.spring.flight.services.FlightService;
import com.spring.flight.services.PassengerService;
import com.spring.flight.validators.BookingDataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingDataValidator bookingDataValidator;

    @Autowired
    private PassengerService passengerService;

    @Autowired
    private FlightService flightService;

    @Override
    public Booking getBookingById(Integer id) throws BookingNotFoundException {
        Optional<Booking> opt=bookingRepository.findById(id);
        if(opt.isPresent())
        {
            return opt.get();
        }
        else {
            throw new BookingNotFoundException("No Booking with Id: "+id);
        }
    }

    @Override
    public void saveABooking(Booking booking) throws NoBookingFoundException, NoPassengerFoundException, FlightNotFoundException {
        if(booking.getId()==null )
        {
            booking.setStatus(BookingStatus.CONFIRMED);
            Payment p = new Payment();
            p.setAmount(booking.getFlight().getBaseFare()* booking.getTravellerCount());
            p.setPaymentMode(booking.getPayment().getPaymentMode());
            p.setBooking(booking);
            booking.setPayment(p);
            booking.setPassenger(passengerService.getPassengerById(booking.getPassenger().getId()));
            booking.setFlight(flightService.getFlightById(booking.getFlight().getId()));
            bookingRepository.save(booking);
        }
        else {
            Optional<Booking> opt=bookingRepository.findById(booking.getId());
            if(opt.isEmpty())
            {
                throw new NoBookingFoundException("Booking not found");
            }
            Booking b=opt.get();

            b.setJourneyDate(booking.getJourneyDate());
            b.setStatus(booking.getStatus());
            b.setPassenger(booking.getPassenger());
            b.setFlight(booking.getFlight());
            b.setTravellerCount(booking.getTravellerCount());
            Payment p1 = b.getPayment();
            p1.setAmount(booking.getFlight().getBaseFare()* booking.getTravellerCount());
            p1.setPaymentMode(booking.getPayment().getPaymentMode());
            b.setPayment(p1);
            bookingRepository.save(b);
        }
    }

    @Override
    public List<Booking> findAllBooking() {
        return bookingRepository.findAll();
    }


    @Override
    public void deleteBookingById(Integer id) throws BookingNotFoundException {
        if(!bookingRepository.existsById(id))
        {
            throw new BookingNotFoundException("No Booking found");
        }
        bookingRepository.deleteById(id);
    }
}
