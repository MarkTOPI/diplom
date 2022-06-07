package com.example.healthtracking.network.models.UserInfo;

import com.google.gson.annotations.SerializedName;

public class UserInfoBody {
    @SerializedName("id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserInfoBody(Integer id) {
        this.id = id;
    }
}
