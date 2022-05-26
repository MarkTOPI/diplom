package com.example.healthtracking.bottomBtnNavigation.profilePage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthtracking.R;
import com.example.healthtracking.firstPage.first_page;
import com.example.healthtracking.network.handler.ProfileHandler;
import com.example.healthtracking.network.models.UserInfo.UserInfoResponse;
import com.example.healthtracking.network.services.ApiProfileService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;
    private String TAG = "Привет!";
    private String token;
    ApiProfileService service = ProfileHandler.getInstance().getProfileService();
    TextView textUserName;

    public ProfileFragment() {
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getContext().getSharedPreferences("token", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        textUserName = view.findViewById(R.id.textUserName);
        AsyncTask.execute(() -> {
            service.getData(token).enqueue(new Callback<List<UserInfoResponse>>() {
                @Override
                public void onResponse(Call<List<UserInfoResponse>> call, Response<List<UserInfoResponse>> response) {
                    textUserName.setText(response.body().get(0).getFirstName() + " " + response.body().get(0).getLastName());
                }

                @Override
                public void onFailure(Call<List<UserInfoResponse>> call, Throwable t) {
                    Log.d(TAG, "onFailure: Что-то пошло не так");
                }
            });
        });


        view.findViewById(R.id.btnExit).setOnClickListener(v -> {
            Toast.makeText(getContext(), "Вы успешно вышли из аккаунта", Toast.LENGTH_SHORT).show();
            sharedPreferences = getContext().getSharedPreferences("token",Context.MODE_PRIVATE);
            sharedPreferences.edit().remove("token").commit();
            startActivity(new Intent(getContext(), first_page.class));
        });
        return view;
    }
}