package com.spring.flight.exceptions;

public class AircraftNotFoundException extends Exception{

    public AircraftNotFoundException(String msg)
    {
        super(msg);
    }
}
