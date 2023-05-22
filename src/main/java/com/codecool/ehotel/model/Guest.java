package com.codecool.ehotel.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public record Guest(GuestNames guestNames, GuestType guestType, LocalDate checkIn, LocalDate checkOut) {
}
