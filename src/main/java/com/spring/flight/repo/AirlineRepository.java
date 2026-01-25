package com.spring.flight.repo;

import com.spring.flight.models.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirlineRepository extends JpaRepository <Airline, Integer>{
}
