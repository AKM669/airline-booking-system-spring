package com.spring.flight.services;

import com.spring.flight.exceptions.NoSeatFoundException;
import com.spring.flight.models.Seat;

import java.util.List;

public interface SeatService {

    Seat getSeatById(Integer id) throws NoSeatFoundException;
    void saveSeat(Seat seat);
    List<Seat> findAllSeat();
    void deleteSeatById(Integer id) throws NoSeatFoundException;

}
