package com.example.healthtracking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthtracking.network.Adapter.FoodAdapter;
import com.example.healthtracking.network.handler.FoodHandler;
import com.example.healthtracking.network.models.Food.FoodResponse;
import com.example.healthtracking.network.services.ApiFoodService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FoodPage extends AppCompatActivity {

    ApiFoodService service = FoodHandler.getInstance().getService();

    private ArrayList<FoodResponse> foodResponses;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FoodAdapter foodAdapter;

    private EditText editTextFoodSearch;

    private TextView txtFoodName, txtFoodCal;
    private ImageView imgFoodImage;

    private String
            application_id = "e8542dc0",
            application_keys = "fa537155c3d9e39b36fbca96d491bf75",
            ingr = "coffee",
            nutrition_type = "cooking",
            TAG = "Response";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_page);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView = findViewById(R.id.foodLvItem);

        editTextFoodSearch = findViewById(R.id.editSearchFood);

        txtFoodName = findViewById(R.id.txtFoodName);
        txtFoodCal = findViewById(R.id.txtFoodCal);
        imgFoodImage = findViewById(R.id.imgFoodImage);

        editTextFoodSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().equals("")) {
                    apiFood();
                } else {
                    searchItem(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        apiFood();
    }

    public void searchItem(String textToSearch) {
//        for (FoodResponse foodResponse:foodResponses) {
//            if (foodResponse.contains(textToSearch)) {
//                foodResponses.remove(foodResponse);
//            }
//        }
    }

    private void apiFood() {
        AsyncTask.execute(() -> {
            service.getFoodInfo(application_id, application_keys, ingr, nutrition_type).enqueue(new Callback<FoodResponse>() {
                @Override
                public void onResponse(Call<FoodResponse> call, Response<FoodResponse> response) {
                    if (response.isSuccessful()) {
                        Log.d(TAG, "onResponse: " + response.body().getParsed());
//                        foodResponses = new ArrayList<>(response.body());
                        foodAdapter = new FoodAdapter(foodResponses, getApplicationContext());
                        recyclerView.setAdapter(foodAdapter);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        foodAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<FoodResponse> call, Throwable t) {
                    Log.d(TAG, "onResponse: " + t.getLocalizedMessage());
                    Toast.makeText(this, "Ответ" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            });
        });
    }


}