package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Meal;
import com.codecool.ehotel.model.MealDurability;
import com.codecool.ehotel.model.MealType;

import java.time.LocalTime;
import java.util.Map;

public class BuffetServiceImpl implements BuffetService{
    private Buffet buffet;

    public BuffetServiceImpl(Buffet buffet) {
        this.buffet = buffet;
    }

    @Override
    public void refill(Map<MealType, Integer> meals) {

        for(Map.Entry<MealType, Integer> meal : meals.entrySet()){

            MealType mealType = meal.getKey();
            Integer amount = meal.getValue();

            for(int i = 0; i < amount; i++){
                buffet.meals().add(new Meal(mealType, LocalTime.now()));
            }
        }
    }

    @Override
    public boolean consumeFreshest(MealType mealType) {

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
    public int collectWaste(MealDurability mealDurability, LocalTime time) {

        int cost = 0;

        for (Meal meal : buffet.meals()){
            if(meal.timeStamp().isBefore(time) && meal.mealType().getDurability() == mealDurability ) {
                buffet.meals().remove(meal);
                cost += meal.mealType().getCost();
            }
        }

        return cost;
    }
}
