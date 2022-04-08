package com.example.healthtracking.network.models.Login;

import com.google.gson.annotations.SerializedName;

public class LoginBody {
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;

    public LoginBody(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail() {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword() {
        this.password = password;
    }
}
