package com.codecool.ehotel;

import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.service.guest.GuestServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EHotelBuffetApplication {

    public static void main(String[] args) {
        GuestServiceImpl guestService = new GuestServiceImpl(new Random());
        // Initialize services


        // Generate guests for the season
        List<Guest> guests = new ArrayList<>();
        int randomGuestNumber = (int) (Math.random() * (20 - 5 + 1)) + 5;
        for (int i = 0; i < randomGuestNumber; i++) {
            guests.add(guestService.generateRandomGuest(LocalDate.now(), LocalDate.now().plusDays(30)));
        }
        System.out.println(guests);
        System.out.println(guestService.getGuestsForDay(guests, LocalDate.now().plusDays(2)));
        // Run breakfast buffet


    }
}
