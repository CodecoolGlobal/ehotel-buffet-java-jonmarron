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
    public BreakfastManager(Buffet buffet, BuffetService buffetService) {
        this.buffet = buffet;
        this.buffetService = buffetService;
    }
    public void serve(Set<List<Guest>> guestsGroup) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        LocalTime now = LocalTime.of(6, 0, 0);
        Map<MealType, Integer> meals = new HashMap<>();

        for (MealType mealType : MealType.values()) {
            meals.put(mealType, 2);
        }
        System.out.println("Meals " + meals);

        int unhappyGuests = 0;
        int wasteCost = 0;

        while (now.isBefore(LocalTime.of(10, 0, 0))) {

            for (List<Guest> group : guestsGroup) {

                buffetService.refill(now, meals, buffet);
                System.out.println(group);
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
                wasteCost += buffetService.collectWaste(MealDurability.SHORT, now.minusHours(1).minusMinutes(30), buffet);
                now = now.plusMinutes(30);
            }
        }
        wasteCost += buffetService.collectWaste(MealDurability.SHORT, now, buffet);
        wasteCost += buffetService.collectWaste(MealDurability.MEDIUM, now, buffet);

        displayMetrics.displayMetrics(unhappyGuests, wasteCost);
    }
}
