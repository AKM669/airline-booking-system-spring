package com.spring.flight.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Roles {

    USER("USER"),
    SUPER_ADMIN("SUPER-ADMIN"),
    AIRCRAFT_ADMIN("AIRCRAFT-ADMIN"),
    FLIGHT_ADMIN("FLIGHT-ADMIN"),
    PASSENGER_ADMIN("PASSENGER-ADMIN");

    private final String roleName;

    Roles(String roleName)
    {
        this.roleName=roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "roleName='" + roleName + '\'' +
                '}';
    }

    public static List<String> getAllRoleNames() {
        return Arrays.stream(values())
                .map(Roles::getRoleName)
                .collect(Collectors.toList());
    }

}
