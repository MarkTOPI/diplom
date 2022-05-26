package com.example.healthtracking.network.services;

import com.example.healthtracking.network.models.UserInfo.UserInfoResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ApiProfileService {
    @GET("user")
    Call<List<UserInfoResponse>> getData(@Header("Authorization") String token);
}
