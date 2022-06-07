package com.example.healthtracking.network.models.Food;

import com.google.gson.annotations.SerializedName;

public class FoodResponse {
    public FoodResponse(String text, FoodParsed parsed) {
        this.text = text;
        this.parsed = parsed;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public FoodParsed getParsed() {
        return parsed;
    }

    public void setParsed(FoodParsed parsed) {
        this.parsed = parsed;
    }

    @SerializedName("text")
    private String text;
    @SerializedName("parsed")
    private FoodParsed parsed;

}
