package com.spring.flight.repo;

import com.spring.flight.models.Booking;
import com.spring.flight.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
