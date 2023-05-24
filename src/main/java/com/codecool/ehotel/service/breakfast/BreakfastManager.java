package com.codecool.ehotel.service.breakfast;

import com.codecool.ehotel.model.*;
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

    public void serve(Set<Guest> guests){
        DisplayMetrics displayMetrics = new DisplayMetrics();
//        LocalTime cycleStart = LocalTime.of(6,0, 0 );
  //      LocalTime cycleEnd = LocalTime.of(6,30, 0 );
        LocalTime now = LocalTime.of(6,0,0);
        Map<MealType, Integer> meals = new HashMap<>();

        meals.put(MealType.BUN, 1);
        meals.put(MealType.CROISSANT, 1);
        meals.put(MealType.FRIED_BACON, 1);
        meals.put(MealType.MASHED_POTATO, 1);

        int unhappyGuests = 0;
        int wasteCost= 0;

        while (now.isBefore(LocalTime.of(10,0,0))){

            buffetService.refill(meals, buffet);

            for ( Guest guest : guests){

                for (Map.Entry<MealType, Integer> entry : meals.entrySet()){
                    MealType mealType = entry.getKey();
                    if(guest.guestType().getMealPreferences().contains(mealType)){
                        if(!buffetService.consumeFreshest(mealType, buffet)){
                            unhappyGuests++;
                        }
                    }
                }
            }
            wasteCost += buffetService.collectWaste(MealDurability.SHORT, now.minusHours(1).minusMinutes(30), buffet);
            System.out.println(now);
            System.out.println(wasteCost);

            now = now.plusMinutes(30);
        }

        wasteCost += buffetService.collectWaste(MealDurability.SHORT, now, buffet);
        wasteCost += buffetService.collectWaste(MealDurability.MEDIUM, now, buffet);

        displayMetrics.displayMetrics(unhappyGuests, wasteCost);
    }
}
