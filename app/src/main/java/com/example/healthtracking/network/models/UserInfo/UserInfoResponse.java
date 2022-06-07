package com.example.healthtracking.network.models.UserInfo;

import com.google.gson.annotations.SerializedName;

public class UserInfoResponse {

    @SerializedName("aim_day_steps")
    private String aim_day_steps;
    @SerializedName("day_steps")
    private String day_steps;
    @SerializedName("count_sleep_hours")
    private String count_sleep_hours;
    @SerializedName("count_cal")
    private String count_cal;
    @SerializedName("count_cups_water")
    private String count_cups_water;
    @SerializedName("weight")
    private String weight;
    @SerializedName("height")
    private String height;

    public UserInfoResponse(String aim_day_steps, String day_steps, String count_sleep_hours, String count_cal, String count_cups_water, String weight, String height) {
        this.aim_day_steps = aim_day_steps;
        this.day_steps = day_steps;
        this.count_sleep_hours = count_sleep_hours;
        this.count_cal = count_cal;
        this.count_cups_water = count_cups_water;
        this.weight = weight;
        this.height = height;
    }

    public String getAim_day_steps() {
        return aim_day_steps;
    }

    public void setAim_day_steps(String aim_day_steps) {
        this.aim_day_steps = aim_day_steps;
    }

    public String getDay_steps() {
        return day_steps;
    }

    public void setDay_steps(String day_steps) {
        this.day_steps = day_steps;
    }

    public String getCount_sleep_hours() {
        return count_sleep_hours;
    }

    public void setCount_sleep_hours(String count_sleep_hours) {
        this.count_sleep_hours = count_sleep_hours;
    }

    public String getCount_cal() {
        return count_cal;
    }

    public void setCount_cal(String count_cal) {
        this.count_cal = count_cal;
    }

    public String getCount_cups_water() {
        return count_cups_water;
    }

    public void setCount_cups_water(String count_cups_water) {
        this.count_cups_water = count_cups_water;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
