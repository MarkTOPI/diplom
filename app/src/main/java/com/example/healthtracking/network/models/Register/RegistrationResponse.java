package com.example.healthtracking.network.models.Register;

import com.google.gson.annotations.SerializedName;

public class RegistrationResponse {
    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
