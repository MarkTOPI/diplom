package com.example.healthtracking.network.models.Food;

import com.google.gson.annotations.SerializedName;

public class FoodParsed {
    public FoodParsed(FoodItem foodItem, String foodImage) {
        this.foodItem = foodItem;
        this.foodImage = foodImage;
    }

    public FoodItem getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(FoodItem foodItem) {
        this.foodItem = foodItem;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

    @SerializedName("food")
    private FoodItem foodItem;
    @SerializedName("image")
    private String foodImage;
}