package com.spring.flight.exceptions;

public class NoPassengerFoundException extends Exception{

    public NoPassengerFoundException(String msg)
    {
        super(msg);
    }
}
