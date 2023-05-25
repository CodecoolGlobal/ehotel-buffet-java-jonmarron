package com.codecool.ehotel.service.breakfast;

import com.codecool.ehotel.model.Guest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BreakfastGroups {
    public Set<List<Guest>> prepareBreakfastGroups(Set<Guest> guests) {
        int setSize = guests.size();
        int guestPerSet = setSize / 7;
        Set<List<Guest>> separatedSets = new HashSet<>();
        for (int i = 0; i < 8 - 1; i++) {
            List<Guest> subSet = new ArrayList<>();
            for (int j = 0; j < guestPerSet; j++) {
                Guest guest = guests.iterator().next();
                subSet.add(guest);
                guests.remove(guest);
            }
            separatedSets.add(subSet);
        }
        separatedSets.add(new ArrayList<>(guests));
        guests.clear();
        return separatedSets;
    }
}
