package com.codecool.ehotel;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.service.breakfast.BreakfastGroups;
import com.codecool.ehotel.service.breakfast.BreakfastManager;
import com.codecool.ehotel.service.buffet.BuffetService;
import com.codecool.ehotel.service.buffet.BuffetServiceImpl;
import com.codecool.ehotel.service.guest.GuestServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class EHotelBuffetApplication {

    public static void main(String[] args) {
        // Initialize services
        Random random = new Random();
        GuestServiceImpl guestService = new GuestServiceImpl(new Random());
        Buffet buffet = new Buffet(new ArrayList<>());
        BuffetService buffetService = new BuffetServiceImpl();
        BreakfastManager breakfastManager = new BreakfastManager(buffet, buffetService);
        BreakfastGroups breakfastGroups = new BreakfastGroups();

        // Generate guests for the season
        List<Guest> guests = new ArrayList<>();

        int randomGuestNumber = random.nextInt(60-30+1) + 30;

        for (int i = 0; i < randomGuestNumber; i++) {
            guests.add(guestService.generateRandomGuest(LocalDate.now(), LocalDate.now().plusDays(10)));
        }

        System.out.println(guests.size() + " Guests");
        System.out.println(guests);

        Set<Guest> todaysGuests = guestService.getGuestsForDay(guests, LocalDate.now().plusDays(2));
        Set<Set<Guest>> groups = breakfastGroups.prepareBreakfastGroups(todaysGuests);

        // Run breakfast buffet
        breakfastManager.serve(groups);

    }
}
