package com.codecool.ehotel.service.breakfast;

import com.codecool.ehotel.model.Guest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BreakfastGroups {
    public Set<List<Guest>> prepareBreakfastGroups(Set<Guest> guests){
        int setSize = guests.size();
        int setSizePerSet = setSize / 8;
        int remainingElements = setSize % 8;

        Set<List<Guest>> separatedSets = new HashSet<>();

        for (int i = 0; i < 8 - remainingElements; i++){
            List<Guest> subSet = new ArrayList<>();
            for (int j = 0; j < setSizePerSet; j++){
                Guest guest = guests.iterator().next();
                subSet.add(guest);
                guests.remove(guest);
            }
            separatedSets.add(subSet);
        }

        for(int i = 0; i < remainingElements; i++){
            List<Guest> remainingGuests = new ArrayList<>();
            Guest guest = guests.iterator().next();
            remainingGuests.add(guest);
            guests.remove(guest);
            separatedSets.add(remainingGuests);
        }
        return separatedSets;
    }
}
