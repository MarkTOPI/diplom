package com.example.healthtracking.bottomBtnNavigation.mainPage;

import static android.content.ContentValues.TAG;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.nfc.Tag;
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
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.healthtracking.FoodPage;
import com.example.healthtracking.R;
import com.example.healthtracking.network.handler.AuthHandler;
import com.example.healthtracking.network.handler.ProfileHandler;
import com.example.healthtracking.network.models.Login.LoginResponse;
import com.example.healthtracking.network.models.UserInfo.UserInfoResponse;
import com.example.healthtracking.network.services.ApiAuthService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment {
    ApiAuthService service = AuthHandler.getInstance().getService();
    private Dialog dialog;
    private Button buttonSteps, buttonSleep, buttonFood, buttonMinus, buttonPlus, buttonBody;
    public SharedPreferences sharedPreferencesToken, sharedPreferencesUserName;
    private TextView textUserName, textStepsAim, textSleepHrs, count, textBodyKgNow, textBodyCmNow;
    private String token, username, TAG = "Привет!";
    public TextView textStepsNow;

    private UserInfoResponse userInfo;

    public MainFragment() {
    }
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        sharedPreferencesToken = getContext().getSharedPreferences("token", Context.MODE_PRIVATE);
        token = sharedPreferencesToken.getString("token", "");
        sharedPreferencesUserName = getContext().getSharedPreferences("username", Context.MODE_PRIVATE);
        username = sharedPreferencesUserName.getString("username", "");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        textUserName = view.findViewById(R.id.textUserName);
        textUserName.setText(username);
        textStepsNow = view.findViewById(R.id.textStepsNow);
        textStepsAim = view.findViewById(R.id.textStepsTarget);
        textSleepHrs = view.findViewById(R.id.textSleepHrs);
        count = view.findViewById(R.id.textWaterNow);
        textBodyKgNow = view.findViewById(R.id.textBodyKgNow);
        textBodyCmNow = view.findViewById(R.id.textBodyCmNow);
        buttonSteps = view.findViewById(R.id.btnSteps);
        buttonSleep = view.findViewById(R.id.btnSleep);
        buttonFood = view.findViewById(R.id.btnFood);
        dialog = new Dialog(buttonSteps.getContext());
        dialog = new Dialog(buttonSleep.getContext());
        buttonMinus = view.findViewById(R.id.btnMinus);
        buttonPlus = view.findViewById(R.id.btnPlus);
        buttonBody = view.findViewById(R.id.btnBody);


        AsyncTask.execute(() -> {
            service.getUserInfo(Integer.valueOf(token)).enqueue(new Callback<UserInfoResponse>() {
                @Override
                public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                    if (response.isSuccessful()) {
                        userInfo = response.body();
                        textStepsAim.setText(response.body().getAim_day_steps());
                        textSleepHrs.setText(response.body().getCount_sleep_hours());
                        count.setText(response.body().getCount_cups_water());
                        textBodyCmNow.setText(response.body().getHeight());
                        textBodyKgNow.setText(response.body().getWeight());
                    }
                }

                @Override
                public void onFailure(Call<UserInfoResponse> call, Throwable t) {
                    Toast.makeText(view.getContext(), "Произошла ошибка при получении данных о пользователе!", Toast.LENGTH_SHORT).show();
                }
            });
        });


        buttonSteps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setContentView(R.layout.custom_steps_input);
                final EditText editTextStepsTracking = (EditText) dialog.findViewById(R.id.editTextStepsTracking);
                dialog.setCancelable(true);
                editTextStepsTracking.setText(textStepsAim.getText().toString());
                Button ok = dialog.findViewById(R.id.customOk);
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final String steps = (String) editTextStepsTracking.getText().toString();
                        userInfo.setAim_day_steps(steps);
                        AsyncTask.execute(() -> {
                            service.putUserInfo(userInfo, Integer.valueOf(token)).enqueue(new Callback<UserInfoResponse>() {
                                @Override
                                public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                                    if (response.isSuccessful()) {
                                        Log.d(TAG, "steps: " + steps);
                                        textStepsAim.setText(steps);
                                        Toast.makeText(view.getContext(), "Вы установили цель:" + steps, Toast.LENGTH_LONG).show();
                                    }
                                    if (response.code() == 422) {
                                        Toast.makeText(view.getContext(), "Синтаксическая ошибка", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<UserInfoResponse> call, Throwable t) {
                                    Toast.makeText(view.getContext(), "Произошла ошибка при отправке данных о пользователе!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        });
                    }
                });
                dialog.show();
            }
        });
        buttonSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setContentView(R.layout.custom_time_picker);
                final EditText editTextSleeps = (EditText) dialog.findViewById(R.id.editTextSleeps);
                dialog.setCancelable(true);
                editTextSleeps.setText(textSleepHrs.getText().toString());
                Button ok = dialog.findViewById(R.id.customOk);
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final String sleeps = (String) editTextSleeps.getText().toString();
                        userInfo.setCount_sleep_hours(sleeps);
                        AsyncTask.execute(() -> {
                            service.putUserInfo(userInfo, Integer.valueOf(token)).enqueue(new Callback<UserInfoResponse>() {
                                @Override
                                public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                                    if (response.isSuccessful()) {
                                        Log.d(TAG, "sleeps: " + sleeps);
                                        textSleepHrs.setText(sleeps);
                                        Toast.makeText(view.getContext(), "Вы спали: " + sleeps + " часов", Toast.LENGTH_LONG).show();
                                    }
                                    if (response.code() == 422) {
                                        Toast.makeText(view.getContext(), "Синтаксическая ошибка", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<UserInfoResponse> call, Throwable t) {
                                    Toast.makeText(view.getContext(), "Произошла ошибка при отправке данных о пользователе!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        });
                    }
                });
                dialog.show();
            }
        });

        buttonFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), FoodPage.class);
                startActivity(intent);
            }
        });

        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String count_cups_water = (String) count.getText().toString();
                userInfo.setCount_cups_water(String.valueOf(count_cups_water));
                AsyncTask.execute(() -> {
                    service.putUserInfo(userInfo, Integer.valueOf(token)).enqueue(new Callback<UserInfoResponse>() {
                        @Override
                        public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                            if (response.isSuccessful()) {
                                CharSequence text = count.getText();
                                int pz = Integer.valueOf(text.toString());
                                if (pz >= 0) {
                                    pz++;
                                    count.setText(Integer.toString(pz));
                                    Log.d(TAG, ""+ count);
                                }
                            }
                            if (response.code() == 422) {
                                Toast.makeText(view.getContext(), "Синтаксическая ошибка", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<UserInfoResponse> call, Throwable t) {
                            Toast.makeText(view.getContext(), "Произошла ошибка при отправке данных о пользователе!", Toast.LENGTH_SHORT).show();
                        }
                    });
                });
            }
        });
        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String count_cups_water = (String) count.getText().toString();
                userInfo.setCount_cups_water(String.valueOf(count_cups_water));
                AsyncTask.execute(() -> {
                    service.putUserInfo(userInfo, Integer.valueOf(token)).enqueue(new Callback<UserInfoResponse>() {
                        @Override
                        public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                            if (response.isSuccessful()) {
                                CharSequence text = count.getText();
                                int pz = Integer.valueOf(text.toString());
                                if (pz < 1) {
                                    pz = 0;
                                    Toast.makeText(getContext(), "Вы не можете выпить меньше 0 стаканов воды!", Toast.LENGTH_LONG).show();
                                    count.setText(Integer.toString(pz));
                                } else {
                                    pz--;
                                    count.setText(Integer.toString(pz));
                                }
                            }
                            if (response.code() == 422) {
                                Toast.makeText(view.getContext(), "Синтаксическая ошибка", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<UserInfoResponse> call, Throwable t) {
                            Toast.makeText(view.getContext(), "Произошла ошибка при отправке данных о пользователе!", Toast.LENGTH_SHORT).show();
                        }
                    });
                });
            }
        });

        buttonBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setContentView(R.layout.custom_body_input);
                final EditText editTextHeightTracking = (EditText) dialog.findViewById(R.id.editTextHeightTracking);
                final EditText editTextWeightTracking = (EditText) dialog.findViewById(R.id.editTextWeightTracking);
                dialog.setCancelable(true);
                editTextHeightTracking.setText(textBodyCmNow.getText().toString());
                editTextWeightTracking.setText(textBodyKgNow.getText().toString());
                Button ok = dialog.findViewById(R.id.customOk);
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final String height = (String) editTextHeightTracking.getText().toString();
                        final String weight = (String) editTextWeightTracking.getText().toString();
                        userInfo.setHeight(height);
                        userInfo.setWeight(weight);
                        AsyncTask.execute(() -> {
                            service.putUserInfo(userInfo, Integer.valueOf(token)).enqueue(new Callback<UserInfoResponse>() {
                                @Override
                                public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                                    if (response.isSuccessful()) {
                                        textBodyKgNow.setText(weight);
                                        textBodyCmNow.setText(height);
                                        Toast.makeText(view.getContext(), "Вы установили свой рост: " + height + " вес: " + weight, Toast.LENGTH_LONG).show();
                                    }
                                    if (response.code() == 422) {
                                        Toast.makeText(view.getContext(), "Синтаксическая ошибка", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<UserInfoResponse> call, Throwable t) {
                                    Toast.makeText(view.getContext(), "Произошла ошибка при отправке данных о пользователе!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        });
                    }
                });
                dialog.show();
            }
        });
        return view;
    }
}