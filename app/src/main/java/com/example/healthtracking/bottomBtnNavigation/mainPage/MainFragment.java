package com.example.healthtracking.bottomBtnNavigation.mainPage;

import static android.content.ContentValues.TAG;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthtracking.R;
import com.example.healthtracking.network.handler.ProfileHandler;
import com.example.healthtracking.network.models.UserInfo.UserInfoResponse;
import com.example.healthtracking.network.services.ApiProfileService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment {
    private Dialog dialog;
    private Button buttonSleep, buttonMinus, buttonPlus;
    ApiProfileService service = ProfileHandler.getInstance().getProfileService();
    private SharedPreferences sharedPreferences;
    private TextView count, textUserName;
    private String TAG = "Привет!";
    private String token;

    public MainFragment() {
    }
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        sharedPreferences = getContext().getSharedPreferences("token", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
//        dialog = new Dialog(buttonSleep.getContext());
        count = view.findViewById(R.id.textWaterNow);
        buttonSleep = view.findViewById(R.id.btnSleep);
        buttonMinus = view.findViewById(R.id.btnMinus);
        buttonPlus = view.findViewById(R.id.btnPlus);
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

//        buttonSleep.setOnClickListener(view3 -> {
//            dialog.setContentView(R.layout.custom_time_picker);
//            dialog.setCancelable(true);
//            Button ok = dialog.findViewById(R.id.customTimeOk);
////            ok.setOnClickListener(view4 -> Toast.makeText(view.getContext(), "Вы спали!", Toast.LENGTH_LONG).show());
//            dialog.show();
//        });

        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence text = count.getText();
                int pz = Integer.valueOf(text.toString());
                if (pz >= 0) {
                    pz++;
                    count.setText(Integer.toString(pz));
                }
            }
        });
        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        });
        Log.i(TAG, "count: " + count);
        return view;
    }
}