package com.codecool.ehotel.service.guest;


import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.GuestNames;
import com.codecool.ehotel.model.GuestType;

import java.time.LocalDate;
import java.util.*;

public class GuestServiceImpl implements GuestService {
    private final Random random;

    public GuestServiceImpl(Random random) {
        this.random = random;
    }


    @Override
    public Guest generateRandomGuest(LocalDate seasonStart, LocalDate seasonEnd) {
        GuestType[] allGuestTypes = GuestType.values();
        int typeRandomIndex = random.nextInt(allGuestTypes.length);
        GuestType guestType = allGuestTypes[typeRandomIndex];
        LocalDate checkIn = seasonStart;
        LocalDate checkOut = checkIn.plusDays(7);

        GuestNames guestName = findRandomGuestName(guestType);
        return new Guest(guestName, guestType, checkIn, checkOut);
    }

    private GuestNames findRandomGuestName(GuestType guestType) {
        GuestNames[] allGuestNames = GuestNames.values();
        List<GuestNames> names = Arrays.stream(allGuestNames)
                .filter(guestNames -> guestNames.getType().equalsIgnoreCase(guestType.toString()))
                .toList();
        int randomNameIndex = random.nextInt(names.size());

        return names.get(randomNameIndex);
    }

    @Override
    public Set<Guest> getGuestsForDay(List<Guest> guests, LocalDate date) {
        int randomGuestNumber = (int) (Math.random() * (10 - 5 + 1)) + 5;
        Set<Guest> guestSet = null;
        for (int i = 0; i < randomGuestNumber; i++) {
            guestSet = new HashSet<>();
            guestSet.add(generateRandomGuest(LocalDate.now(), LocalDate.now()));
        }
        return guestSet;
    }
}


