package com.example.healthtracking.network.services;

import com.example.healthtracking.network.models.Food.FoodResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiFoodService {
    @GET("/api/food-database/v2/parser")
    Call<FoodResponse> getFoodInfo(@Query(value = "app_id") String application_id,
                                   @Query(value = "app_key") String application_keys,
                                   @Query(value = "ingr") String ingr,
                                   @Query(value = "nutrition-type") String nutrition_type);
}
