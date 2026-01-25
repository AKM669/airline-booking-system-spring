package com.spring.flight.services;

import com.spring.flight.exceptions.BookingNotFoundException;
import com.spring.flight.exceptions.NoBookingFoundException;
import com.spring.flight.models.Booking;

import java.util.List;

public interface BookingService {

    Booking getBookingById(Integer id) throws BookingNotFoundException;
    void saveABooking(Booking booking) throws NoBookingFoundException;
    List<Booking> findAllBooking() ;
    void deleteBookingById(Integer id) throws BookingNotFoundException;

}
