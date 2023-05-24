package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.MealDurability;
import com.codecool.ehotel.model.MealType;

import java.time.LocalTime;
import java.util.Map;

public interface BuffetService {

    void refill(LocalTime time, Map<MealType, Integer> meals, Buffet buffet);

    boolean consumeFreshest(MealType mealType, Buffet buffet);

    int collectWaste(MealDurability mealDurability, LocalTime time, Buffet buffet);
}
