package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Meal;
import com.codecool.ehotel.model.MealType;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BuffetServiceImplTest {
    @Test
    void refill2meals() {
        Buffet buffet = new Buffet(new ArrayList<>());
        BuffetService buffetService = new BuffetServiceImpl(buffet);
        Map<MealType, Integer> meals = Map.of(
                MealType.MILK, 2,
                MealType.CEREAL, 1
        );

        buffetService.refill(meals);

        Buffet expectedBuffet = new Buffet(List.of(
                new Meal(MealType.MILK, LocalTime.now()),
                new Meal(MealType.MILK, LocalTime.now()),
                new Meal(MealType.CEREAL, LocalTime.now())

        ));
        assertEquals(expectedBuffet.meals().size(), buffet.meals().size());
        assertEquals(MealType.MILK, buffet.meals().get(0).mealType());
        assertEquals(MealType.MILK, buffet.meals().get(1).mealType());
        assertEquals(MealType.CEREAL, buffet.meals().get(2).mealType());
    }

    @Test
    void consumeFreshest() {
    }

    @Test
    void collectWaste() {
    }
}