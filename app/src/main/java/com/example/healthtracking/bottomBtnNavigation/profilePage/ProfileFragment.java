package com.example.healthtracking.bottomBtnNavigation.profilePage;

import android.app.Dialog;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthtracking.R;
import com.example.healthtracking.firstPage.first_page;
import com.example.healthtracking.network.handler.ProfileHandler;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {
    private SharedPreferences.Editor editor;
    private Dialog dialog;
    private SharedPreferences sharedPreferencesToken, sharedPreferencesUserName;
    private String TAG = "Привет!";
    private String token, username;
    private Button btnChangeUserName;
    private EditText editTextUserName;
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
        sharedPreferencesToken = getContext().getSharedPreferences("token", Context.MODE_PRIVATE);
        token = sharedPreferencesToken.getString("token", "");
        sharedPreferencesUserName = getContext().getSharedPreferences("username", Context.MODE_PRIVATE);
        username = sharedPreferencesToken.getString("username", "");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        btnChangeUserName = view.findViewById(R.id.btnChangeUserName);
        textUserName = view.findViewById(R.id.textUserName);
        textUserName.setText(username);

        btnChangeUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setContentView(R.layout.custom_user_name_input);
                final EditText editTextUserName = (EditText) dialog.findViewById(R.id.editTextUserName);
                dialog.setCancelable(true);
                Button ok = dialog.findViewById(R.id.customOk);
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final String userName = (String) editTextUserName.getText().toString();
                        textUserName.setText(userName);
                        Toast.makeText(view.getContext(), "Вы установили новое имя пользователя: " + userName, Toast.LENGTH_LONG).show();
                    }
                });
                dialog.show();
            }
        });


        view.findViewById(R.id.btnExit).setOnClickListener(v -> {
            Toast.makeText(getContext(), "Вы успешно вышли из аккаунта", Toast.LENGTH_SHORT).show();
            sharedPreferencesToken = this.getActivity().getSharedPreferences("token",Context.MODE_PRIVATE);
            sharedPreferencesToken.edit().remove("token").commit();
            startActivity(new Intent(getContext(), first_page.class));
        });
        return view;
    }
}