package com.spring.flight.services.impl;

import com.spring.flight.exceptions.NoSeatFoundException;
import com.spring.flight.models.Seat;
import com.spring.flight.repo.SeatRepository;
import com.spring.flight.services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatRepository seatRepository;



    @Override
    public Seat getSeatById(Integer id) throws NoSeatFoundException {
        Optional<Seat> opt=seatRepository.findById(id);
        if(opt.isEmpty())
        {
            throw new NoSeatFoundException("There is no seat with Id: "+id);
        }
        else {
            return opt.get();
        }
    }

    @Override
    public void saveSeat(Seat seat) {
//        if(seat.getId()==null)
//        {
//            seatRepository.save(seat);
//        }
//        else {
//
//        }
    }

    @Override
    public List<Seat> findAllSeat() {
        List<Seat> li=seatRepository.findAll();
        return li;
    }

    @Override
    public void deleteSeatById(Integer id) throws NoSeatFoundException {
        Optional<Seat> opt=seatRepository.findById(id);
        if(opt.isEmpty())
        {
            throw new NoSeatFoundException("No seat found to delete with Id: "+id);
        }
        else {
            seatRepository.deleteById(id);
        }
    }
}
