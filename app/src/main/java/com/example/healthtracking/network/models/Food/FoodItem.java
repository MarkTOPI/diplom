package com.example.healthtracking.network.models.Food;

import com.google.gson.annotations.SerializedName;

public class FoodItem {
    public FoodItem(String food_label, FoodNutrients foodNutrients) {
        this.food_label = food_label;
        this.foodNutrients = foodNutrients;
    }

    public String getFood_label() {
        return food_label;
    }

    public void setFood_label(String food_label) {
        this.food_label = food_label;
    }

    public FoodNutrients getFoodNutrients() {
        return foodNutrients;
    }

    public void setFoodNutrients(FoodNutrients foodNutrients) {
        this.foodNutrients = foodNutrients;
    }

    @SerializedName("label")
    private String food_label;
    @SerializedName("nutrients")
    private FoodNutrients foodNutrients;
}
