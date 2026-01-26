package com.spring.flight.repo;

import com.spring.flight.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    List<Flight> findBySourceAndDestination(String source, String destination);
}
