package com.example.healthtracking.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.healthtracking.R;
import com.example.healthtracking.firstPage.first_page;
import com.example.healthtracking.login.LoginPage;
import com.example.healthtracking.network.errorUtils.ErrorUtils;
import com.example.healthtracking.network.handler.AuthHandler;
import com.example.healthtracking.network.models.Register.RegistrationBody;
import com.example.healthtracking.network.models.Register.RegistrationResponse;
import com.example.healthtracking.network.services.ApiAuthService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationPage extends AppCompatActivity {

    private static final String TAG = "Registration";

    EditText editUserName, editLogin, editPassword, editRepeatPassword;

    ApiAuthService service = AuthHandler.getInstance().getService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_AppCompat_DayNight_NoActionBar);
        setContentView(R.layout.activity_registration_page);

        initializeViews();
    }

    private void initializeViews() {
        editUserName = findViewById(R.id.editUserName);
        editLogin = findViewById(R.id.editLogin);
        editPassword = findViewById(R.id.editPassword);
        editRepeatPassword = findViewById(R.id.editRepeatPassword);

        findViewById(R.id.btnRegistration).setOnClickListener(view -> {
            btnRegistration();
        });
    }

    public void btnRegistration() {
        AsyncTask.execute(() -> {
            service.goRegistration(getRegistrationData()).enqueue(new Callback<RegistrationResponse>() {
                @Override
                public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                    if (response.isSuccessful()) {
                        Intent intent = new Intent(RegistrationPage.this, LoginPage.class);
                        startActivity(intent);
                        finish();
                        Log.d(TAG, "onResponse: " + getApplicationContext());
                        Toast.makeText(getApplicationContext(), "Регистрация успешна!", Toast.LENGTH_LONG).show();
                    } else if (response.code() == 400) {
                        String serviceErrorMessage = ErrorUtils.parseError(response).message();
                        Toast.makeText(getApplicationContext(), serviceErrorMessage, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Произошла ошибка!", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            });
        });
    }

    private RegistrationBody getRegistrationData() {
        return new RegistrationBody(editUserName.getText().toString(), editLogin.getText().toString(), editPassword.getText().toString(), editRepeatPassword.getText().toString());
    }


    public void btnBack(View view) {
        Intent intent = new Intent(this, first_page.class);
        startActivity(intent);
    }
}