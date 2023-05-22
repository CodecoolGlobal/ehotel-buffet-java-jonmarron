package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Meal;
import com.codecool.ehotel.model.MealDurability;
import com.codecool.ehotel.model.MealType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface BuffetService {

    void refill(Map<MealType , Integer> meals);

    boolean consumeFreshest(MealType mealType);
    int collectWaste(MealDurability mealDurability, LocalDateTime time);
}
