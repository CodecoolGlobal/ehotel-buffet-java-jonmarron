package com.codecool.ehotel.service.breakfast;

import com.codecool.ehotel.model.Guest;

import java.util.HashSet;
import java.util.Set;

public class breakfastGroups {
    public Set<Set<Guest>> prepareBreakfastGroups(Set<Guest> guests){
        int setSize = guests.size();
        int setSizePerSet = setSize / 8;
        int remainingElements = setSize % 8;

        Set<Set<Guest>> separatedSets = new HashSet<>();

        for (int i = 0; i < 8 - remainingElements; i++){
            Set<Guest> subSet = new HashSet<>();
            for (int j = 0; j < setSizePerSet; j++){
                Guest guest = guests.iterator().next();
                subSet.add(guest);
                guests.remove(guest);
            }
            separatedSets.add(subSet);
        }

        for(int i = 0; i < remainingElements; i++){
            Set<Guest> remainingGuests = new HashSet<>();
            Guest guest = guests.iterator().next();
            remainingGuests.add(guest);
            guests.remove(guest);
            separatedSets.add(remainingGuests);
        }
        return separatedSets;
    }
}
