package com.example.healthtracking.network.services;

import android.content.Intent;

import com.example.healthtracking.network.models.Login.LoginBody;
import com.example.healthtracking.network.models.Login.LoginResponse;
import com.example.healthtracking.network.models.Register.RegistrationBody;
import com.example.healthtracking.network.models.Register.RegistrationResponse;
import com.example.healthtracking.network.models.UserInfo.UserInfoResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiAuthService {
    @POST("/login")
    Call<LoginResponse> goFeed(@Body LoginBody LoginBody);

    @POST("/registration")
    Call<RegistrationResponse> goRegistration(@Body RegistrationBody registerBody);

    @GET("/me")
    Call<UserInfoResponse> getUserInfo(@Query(value = "id") Integer id);

    @PUT("/me")
    Call<UserInfoResponse> putUserInfo(@Body UserInfoResponse userInfoResponse, @Query(value = "user_id") Integer id);
}
