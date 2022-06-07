package com.example.healthtracking.network.handler;

import com.example.healthtracking.network.errorUtils.ErrorUtils;
import com.example.healthtracking.network.services.ApiAuthService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthHandler {

    private static AuthHandler mInstance;

    private Retrofit retrofit;

//    private static final String BASE_URL = "http://cinema.areas.su/auth/";
    private static final String BASE_URL = "https://mighty-island-79577.herokuapp.com";

    public AuthHandler () {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(interceptor);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ErrorUtils.retrofit = retrofit;
    }

    public static AuthHandler getInstance() {
        if (mInstance == null) {
            mInstance = new AuthHandler();
        }
        return mInstance;
    }

    public ApiAuthService getService() {
        return retrofit.create(ApiAuthService.class);
    }

}
