package com.example.healthtracking.network.models.Food;

import com.google.gson.annotations.SerializedName;

public class FoodNutrients {
    public FoodNutrients(String energy_cal) {
        this.energy_cal = energy_cal;
    }

    public String getEnergy_cal() {
        return energy_cal;
    }

    public void setEnergy_cal(String energy_cal) {
        this.energy_cal = energy_cal;
    }

    @SerializedName("ENERC_KCAL")
    private String energy_cal;
}
