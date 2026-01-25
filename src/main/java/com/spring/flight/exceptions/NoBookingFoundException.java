package com.spring.flight.exceptions;

public class NoBookingFoundException extends Exception {
    public NoBookingFoundException(String msg) {
        super(msg);
    }
}
