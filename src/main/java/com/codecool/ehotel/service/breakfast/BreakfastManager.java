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
        LocalTime cycleStart = LocalTime.of(6,0, 0 );
        LocalTime cycleEnd = LocalTime.of(6,30, 0 );

        Map<MealType, Integer> meals = new HashMap<>();

        for (MealType mealType : MealType.values()){
            meals.put(mealType, 10);
        }
        System.out.println("Meals " + meals);

        int unhappyGuests = 0;
        int wasteCost= 0;

        while (cycleEnd.isBefore(LocalTime.of(10,0,0))){

            buffetService.refill(meals, buffet);

            for ( Guest guest : guests){
                for(int i = buffet.meals().size() -1; i >= 0; i--){
                    if(guest.guestType().getMealPreferences().contains(buffet.meals().get(i).mealType())){
                        if(!buffetService.consumeFreshest(buffet.meals().get(i).mealType(), buffet)) {
                            unhappyGuests++;
                        };
                    };
                }
            }
            if(cycleEnd.isAfter(LocalTime.of(6, 0, 0).plusHours(1).plusMinutes(30))) {
                wasteCost += buffetService.collectWaste(MealDurability.SHORT, cycleEnd.minusHours(1).minusMinutes(30), buffet);
            }

            cycleStart = cycleStart.plusMinutes(30);
            cycleEnd = cycleEnd.plusMinutes(30);
        }

        wasteCost += buffetService.collectWaste(MealDurability.SHORT, cycleEnd, buffet);
        wasteCost += buffetService.collectWaste(MealDurability.MEDIUM, cycleEnd, buffet);

        displayMetrics.displayMetrics(unhappyGuests, wasteCost);
    }
}
