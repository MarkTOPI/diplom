package com.example.healthtracking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
    private FoodAdapter foodAdapter;

    private EditText editTextFoodSearch;

    private TextView txtFoodName, txtFoodCal;
    private ImageView imgFoodImage;

    private String
            application_id = "e8542dc0",
            application_keys = " fa537155c3d9e39b36fbca96d491bf75",
            ingr = "coffee",
            nutrition_type = "cooking",
            TAG = "Response";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_page);

        recyclerView = findViewById(R.id.foodLvItem);

        editTextFoodSearch = findViewById(R.id.editSearchFood);

        txtFoodName = findViewById(R.id.txtFoodName);
        txtFoodCal = findViewById(R.id.txtFoodCal);
        imgFoodImage = findViewById(R.id.imgFoodImage);

        apiFood();
    }

    private void apiFood() {
        AsyncTask.execute(() -> {
            service.getFoodInfo(application_id, application_keys, ingr, nutrition_type).enqueue(new Callback<FoodResponse>() {
                @Override
                public void onResponse(Call<FoodResponse> call, Response<FoodResponse> response) {
                    if (response.isSuccessful()) {
                        Log.d(TAG, "onResponse: " + response.body());
//                        foodResponses = new ArrayList<>(response.body());
                        foodAdapter = new FoodAdapter(foodResponses, getApplicationContext());
                        recyclerView.setAdapter(foodAdapter);
                        foodAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<FoodResponse> call, Throwable t) {

                }
            });
        });
    }


}