package com.codecool.ehotel;

import com.codecool.ehotel.service.guest.GuestServiceImpl;

import java.time.LocalDate;
import java.util.Random;

public class EHotelBuffetApplication {

    public static void main(String[] args) {
GuestServiceImpl guestService = new GuestServiceImpl(new Random());
        // Initialize services

System.out.println(guestService.generateRandomGuest(LocalDate.now(),LocalDate.now()));
        // Generate guests for the season


        // Run breakfast buffet


    }
}
