package com.spring.flight.exceptions;

public class NoAircraftFoundException extends Exception {
    public NoAircraftFoundException(String msg)
    {
        super(msg);
    }
}
