package com.spring.flight.repo;

import com.spring.flight.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {
    List<Seat> findByFlightIdAndIsAvailableTrue(Integer id);

}
