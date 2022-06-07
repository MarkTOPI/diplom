package com.example.healthtracking.network.handler;

import com.example.healthtracking.network.errorUtils.ErrorUtils;
import com.example.healthtracking.network.services.ApiAuthService;
import com.example.healthtracking.network.services.ApiFoodService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoodHandler {
    private static FoodHandler mInstance;
    private static final String BASEURL = "https://api.edamam.com";
    private Retrofit retrofit;

    public FoodHandler() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(interceptor);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ErrorUtils.retrofit = retrofit;
    }

    public static FoodHandler getInstance() {
        if (mInstance == null) {
            mInstance = new FoodHandler();
        }

        return mInstance;
    }

    public ApiFoodService getService() {
        return retrofit.create(ApiFoodService.class);
    }
}
