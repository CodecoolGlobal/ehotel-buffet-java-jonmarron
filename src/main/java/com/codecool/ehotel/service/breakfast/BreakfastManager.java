package com.codecool.ehotel.service.breakfast;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.MealType;
import com.codecool.ehotel.service.buffet.BuffetService;

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
        Map<MealType, Integer> meals = new HashMap<>();

            }
}
