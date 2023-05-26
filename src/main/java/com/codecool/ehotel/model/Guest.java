package com.codecool.ehotel.model;

import java.time.LocalDate;

public record Guest(GuestNames guestName, GuestType guestType, LocalDate checkIn, LocalDate checkOut) {
}
