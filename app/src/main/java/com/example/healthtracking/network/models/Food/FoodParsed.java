package com.example.healthtracking.network.models.Food;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FoodParsed {
    @SerializedName("food")
    private FoodItem foodItem;

    public FoodItem getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(FoodItem foodItem) {
        this.foodItem = foodItem;
    }

    public FoodParsed(FoodItem foodItem) {
        this.foodItem = foodItem;
    }
}
