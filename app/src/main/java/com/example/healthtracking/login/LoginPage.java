package com.example.healthtracking.login;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.healthtracking.MainActivity;
import com.example.healthtracking.R;
import com.example.healthtracking.firstPage.first_page;
import com.example.healthtracking.network.errorUtils.ErrorUtils;
import com.example.healthtracking.network.handler.AuthHandler;
import com.example.healthtracking.network.models.Login.LoginBody;
import com.example.healthtracking.network.models.Login.LoginResponse;
import com.example.healthtracking.network.services.ApiAuthService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPage extends AppCompatActivity {

    private SharedPreferences.Editor editorToken, editorUserName;
    private SharedPreferences sharedPreferencesToken, sharedPreferencesUserName;
    private String token, username;
    EditText editLogin, editPassword;

    ApiAuthService service = AuthHandler.getInstance().getService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_AppCompat_DayNight_NoActionBar);
        setContentView(R.layout.activity_login_page);

        editorToken = getSharedPreferences("token", MODE_PRIVATE).edit();
        sharedPreferencesToken = getSharedPreferences("token", MODE_PRIVATE);
        token = sharedPreferencesToken.getString("token", "");
        editorUserName = getSharedPreferences("username", MODE_PRIVATE).edit();
        sharedPreferencesUserName = getSharedPreferences("username", MODE_PRIVATE);
        username = sharedPreferencesUserName.getString("username", "");
        Log.d(TAG, "" + token);
        Log.d(TAG, "" + username);
        if (token != "") {
            goMainPage();
        }
        initializeView();
    }

    public void initializeView() {
        editLogin = findViewById(R.id.editLogin);
        editPassword = findViewById(R.id.editPassword);

        findViewById(R.id.btnLogin).setOnClickListener(view -> {
            goFeed();
        });
    }

    private void goFeed() {
        AsyncTask.execute(() -> {
            service.goFeed(getLoginData()).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.isSuccessful()) {
                        editorToken.putString("token", response.body().getToken()).apply();
                        editorUserName.putString("username", response.body().getUsername()).apply();
                        Log.d(TAG, "Мой юзер: " + response.body().getUsername());
                        Toast.makeText(getApplicationContext(), "Вы успешно вошли!", Toast.LENGTH_SHORT).show();
                        goMainPage();
                    } else if (response.code() == 400) {
                        Toast.makeText(getApplicationContext(), "Синтаксическая ошибка", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Произошла ошибка ;(", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    getSystemService(t.getLocalizedMessage());
                    Toast.makeText(getApplicationContext(), "Произошла плохая ошибка ;(", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    public LoginBody getLoginData() {
        return new LoginBody(editLogin.getText().toString(), editPassword.getText().toString());
    }

    public void goMainPage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void btnBack(View view) {
        Intent intent = new Intent(this, first_page.class);
        startActivity(intent);
    }
}