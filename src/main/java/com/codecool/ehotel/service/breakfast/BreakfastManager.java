package com.codecool.ehotel.service.breakfast;

import com.codecool.ehotel.model.*;
import com.codecool.ehotel.service.buffet.BuffetService;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BreakfastManager {
    private Buffet buffet;
    private BuffetService buffetService;
    public BreakfastManager(Buffet buffet, BuffetService buffetService) {
        this.buffet = buffet;
        this.buffetService = buffetService;
    }

    public void serve(List<Guest> guests){
        LocalTime cycleStart = LocalTime.of(6,0, 0 );
        LocalTime cycleEnd = LocalTime.of(6,30, 0 );

        Map<MealType, Integer> meals = new HashMap<>();

        meals.put(MealType.BUN, 5);
        meals.put(MealType.CROISSANT, 15);
        meals.put(MealType.FRIED_BACON, 10);
        meals.put(MealType.MASHED_POTATO, 12);

        int unhappyGuests = 0;

        while (cycleEnd.isBefore(LocalTime.of(10,0,0))){

            buffetService.refill(meals);

            for ( Guest guest : guests){
                for(Meal meal : buffet.meals()){
                    if(guest.guestType().getMealPreferences().contains(meal.mealType())){
                        if(buffetService.consumeFreshest(meal.mealType())) {
                            unhappyGuests++;
                        };
                    };
                }
            }
            if(cycleEnd.isAfter(LocalTime.of(6, 0, 0).plusHours(1).plusMinutes(30))) {
                buffetService.collectWaste(MealDurability.SHORT, cycleEnd.minusHours(1).minusMinutes(30));
            }

            cycleStart.plusMinutes(30);
            cycleEnd.plusMinutes(30);
        }
        
        buffetService.collectWaste(MealDurability.SHORT, cycleEnd);
        buffetService.collectWaste(MealDurability.MEDIUM, cycleEnd);
    }
}
