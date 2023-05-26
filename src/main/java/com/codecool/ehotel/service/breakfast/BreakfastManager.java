package com.codecool.ehotel.service.breakfast;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.MealDurability;
import com.codecool.ehotel.model.MealType;
import com.codecool.ehotel.service.buffet.BuffetService;
import com.codecool.ehotel.service.ui.DisplayMetrics;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BreakfastManager {
    private final Buffet buffet;
    private final BuffetService buffetService;
    private final int cycleLengthInMinutes;

    public BreakfastManager(Buffet buffet, BuffetService buffetService, int cycleLengthInMinutes) {
        this.buffet = buffet;
        this.buffetService = buffetService;
        this.cycleLengthInMinutes = cycleLengthInMinutes;
    }
    public void serve(Set<List<Guest>> guestsGroup) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        LocalTime now = LocalTime.of(6, 0, 0);
        Map<MealType, Integer> meals = new HashMap<>();
        for (MealType mealType : MealType.values()) {
            meals.put(mealType, 2);
        }
        int numberOfUnhappyGuests = 0;
        int wasteCost = 0;
        for (List<Guest> group : guestsGroup) {
            displayMetrics.displayGuests(group);
            buffetService.refill(now, meals, buffet);
            numberOfUnhappyGuests = consumeAndReturnNumberOfUnhappyGuests(meals, numberOfUnhappyGuests, group);
            wasteCost += buffetService.collectWaste(
                    MealDurability.SHORT, now.minusHours(1).minusMinutes(30), buffet);
            now = now.plusMinutes(cycleLengthInMinutes);
        }
        int endWaste = buffetService.collectWaste(MealDurability.SHORT, now, buffet) 
                + buffetService.collectWaste(MealDurability.MEDIUM, now, buffet);
        wasteCost += endWaste;
        displayMetrics.displayMetrics(numberOfUnhappyGuests, wasteCost);
    }

    private int consumeAndReturnNumberOfUnhappyGuests(Map<MealType, Integer> meals, int unhappyGuests, List<Guest> group) {
        for (Guest guest : group) {
            boolean unhappy = false;
            for (Map.Entry<MealType, Integer> entry : meals.entrySet()) {
                MealType mealType = entry.getKey();
                if (guest.guestType().getMealPreferences().contains(mealType)) {
                    if (!buffetService.consumeFreshest(mealType, buffet) && !unhappy) {
                        unhappyGuests++;
                        unhappy = true;
                    }
                }
            }
        }
        return unhappyGuests;
    }


}
