package com.codecool.ehotel.service.breakfast;

import com.codecool.ehotel.model.Guest;

import java.util.*;

public class BreakfastGroupsOrganizer {
    private final Random random;
    private final int numberOfGroups;

    public BreakfastGroupsOrganizer(Random random, int numberOfGroups) {
        this.random = random;
        this.numberOfGroups = numberOfGroups;
    }

    public Set<List<Guest>> prepareBreakfastGroups(Set<Guest> guests) {
        // SLAP: Single Level of Abstraction Principle
        List<List<Guest>> groups = getEmptyGroups();
        for (Guest guest : guests) {
            assignOneRandomly(guest, groups);
        }
        return new HashSet<>(groups);
    }

    private List<List<Guest>> getEmptyGroups() {
        List<List<Guest>> groups = new ArrayList<>();
        for (int index = 0; index < numberOfGroups; index++) {
            groups.add(new ArrayList<>());
        }
        return groups;
    }

    private void assignOneRandomly(Guest guest, List<List<Guest>> groups) {
        int randomGroupIndex = random.nextInt(numberOfGroups);
        List<Guest> group = groups.get(randomGroupIndex);
        group.add(guest);
    }
}
