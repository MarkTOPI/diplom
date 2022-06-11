package com.example.healthtracking.network.models.Food;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FoodResponse {
    @SerializedName("text")
    private String text;
    @SerializedName("parsed")
    private List<FoodParsed> parsed;

    public FoodResponse(String text, List<FoodParsed> parsed) {
        this.text = text;
        this.parsed = parsed;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<FoodParsed> getParsed() {
        return parsed;
    }

    public void setParsed(List<FoodParsed> parsed) {
        this.parsed = parsed;
    }
}
