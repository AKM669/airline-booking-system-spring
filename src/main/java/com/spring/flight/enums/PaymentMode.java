package com.spring.flight.enums;

public enum PaymentMode {
    CREDIT_CARD("CC"),
    DEBIT_CARD("DC"),
    UPI("UPI"),
    NET_BANKING("NB"),
    CASH("CASH");

    private String name;

    PaymentMode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

