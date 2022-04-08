package com.example.healthtracking.bottomBtnNavigation.profilePage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.healthtracking.R;
import com.example.healthtracking.firstPage.first_page;

public class ProfileFragment extends Fragment {
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;
    private String token;

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
        view.findViewById(R.id.btnExit).setOnClickListener(v -> {
            Toast.makeText(getContext(), "Вы успешно вышли из аккаунта", Toast.LENGTH_SHORT).show();
            sharedPreferences = getContext().getSharedPreferences("token",Context.MODE_PRIVATE);
            sharedPreferences.edit().remove("token").commit();
            startActivity(new Intent(getContext(), first_page.class));
        });
        return view;
    }
}