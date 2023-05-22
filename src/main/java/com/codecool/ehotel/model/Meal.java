    package com.codecool.ehotel.model;

    import java.time.LocalTime;

    public record Meal(MealType mealType, LocalTime timeStamp) {
    }
