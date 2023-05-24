package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Meal;
import com.codecool.ehotel.model.MealDurability;
import com.codecool.ehotel.model.MealType;

import java.time.LocalTime;
import java.util.Map;

public class BuffetServiceImpl implements BuffetService{

    @Override
    public void refill(LocalTime time, Map<MealType, Integer> meals, Buffet buffet) {

        for(Map.Entry<MealType, Integer> meal : meals.entrySet()){

            MealType mealType = meal.getKey();
            Integer amount = meal.getValue();
            int amountSameMealType = buffet.meals().stream()
                    .filter(food -> food.mealType() == mealType)
                    .toList().size();
            if(amountSameMealType <= 1){
                for(int i = 0; i < amount; i++){
                    buffet.meals().add(new Meal(mealType, time));
                }
            }
        }
    }

    @Override
    public boolean consumeFreshest(MealType mealType, Buffet buffet) {

        Meal freshestMeal = null;
        LocalTime freshestTimeStamp = null;

        for(Meal meal : buffet.meals()){
            if (meal.mealType() == mealType){
                if (freshestTimeStamp == null || freshestTimeStamp.isBefore(meal.timeStamp())){
                    freshestMeal = meal;
                    freshestTimeStamp = freshestMeal.timeStamp();
                }
            }
        }

        if(freshestMeal != null){
            buffet.meals().remove(freshestMeal);
            return true;
        }

        return false;
    }

    @Override
    public int collectWaste(MealDurability mealDurability, LocalTime time, Buffet buffet) {

        int cost = 0;
        int buffetSize = buffet.meals().size();
        for(int i = buffetSize-1; i>=0;i--){
            Meal meal = buffet.meals().get(i);

            if(meal.timeStamp().isBefore(time) && meal.mealType().getDurability() == mealDurability ) {
                buffet.meals().remove(meal);
                cost += meal.mealType().getCost();
            }

        }

        return cost;
    }
}
