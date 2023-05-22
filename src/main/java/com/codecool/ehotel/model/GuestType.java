package com.codecool.ehotel.model;

import java.util.List;
import static com.codecool.ehotel.model.MealType.*;

public enum GuestType {

    HUMAN(List.of(SCRAMBLED_EGGS, FRIED_BACON, CROISSANT)),
    DWARF(List.of(SUNNY_SIDE_UP, FRIED_SAUSAGE, MASHED_POTATO, BUN, MUFFIN)),
    ELF(List.of(PANCAKE, MUFFIN, CEREAL, MILK));

    private List<MealType> mealPreferences;

    GuestType(List<MealType> mealPreferences) {
        this.mealPreferences = mealPreferences;
    }

    public List<MealType> getMealPreferences() {
        return mealPreferences;
    }
}
