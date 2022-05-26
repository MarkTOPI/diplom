package com.example.healthtracking.network.handler;

import com.example.healthtracking.network.errorUtils.ErrorUtils;
import com.example.healthtracking.network.services.ApiProfileService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileHandler {
    private static ProfileHandler mInstance;
    private static final String BASEURL = "http://cinema.areas.su/";
    private Retrofit retrofit;

    public ProfileHandler() {
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

    public static ProfileHandler getInstance() {
        if (mInstance == null) {
            mInstance = new ProfileHandler();
        }

        return mInstance;
    }

    public ApiProfileService getProfileService() {
        return retrofit.create(ApiProfileService.class);
    }
}
