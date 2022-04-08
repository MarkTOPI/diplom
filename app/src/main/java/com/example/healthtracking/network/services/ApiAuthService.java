package com.example.healthtracking.network.services;

import com.example.healthtracking.network.models.Login.LoginBody;
import com.example.healthtracking.network.models.Login.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiAuthService {
    @POST("login")
    Call<LoginResponse> goFeed(@Body LoginBody LoginBody);
}
