package com.codecool.ehotel;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.service.breakfast.BreakfastGroupsOrganizer;
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
        GuestServiceImpl guestService = new GuestServiceImpl(random);
        Buffet buffet = new Buffet(new ArrayList<>());
        int minimumAmountOfFood = 1;
        BuffetService buffetService = new BuffetServiceImpl(minimumAmountOfFood);
        int cycleLengthInMinutes = 30;
        BreakfastManager breakfastManager = new BreakfastManager(buffet, buffetService, cycleLengthInMinutes);
        int numberOfGroups = 8;
        BreakfastGroupsOrganizer breakfastGroupsOrganizer = new BreakfastGroupsOrganizer(random, numberOfGroups);

        // Generate guests for the season
        List<Guest> guests = new ArrayList<>();
        int randomGuestNumber = createBetween30and60RandomGuests(random);
        for (int i = 0; i < randomGuestNumber; i++) {
            guests.add(guestService.generateRandomGuest(LocalDate.now(), LocalDate.now().plusDays(10)));
        }
        Set<Guest> todaysGuests = guestService.getGuestsForDay(guests, LocalDate.now().plusDays(2));
        Set<List<Guest>> groups = breakfastGroupsOrganizer.prepareBreakfastGroups(todaysGuests);

        // Run breakfast buffet
        breakfastManager.serve(groups);
    }

    private static int createBetween30and60RandomGuests(Random random) {
        return random.nextInt(31) + 30;
    }
}
