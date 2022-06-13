package com.example.healthtracking.network.models.Food;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FoodItem {
    public FoodItem(String foodImage, String food_label, FoodNutrients foodNutrients) {
        this.foodImage = foodImage;
        this.food_label = food_label;
        this.foodNutrients = foodNutrients;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
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

    @SerializedName("image")
    private String foodImage;
    @SerializedName("label")
    private String food_label;
    @SerializedName("nutrients")
    private FoodNutrients foodNutrients;
}
