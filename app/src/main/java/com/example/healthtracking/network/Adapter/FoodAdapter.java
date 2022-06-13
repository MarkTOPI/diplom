package com.example.healthtracking.network.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthtracking.R;
import com.example.healthtracking.network.models.Food.FoodParsed;
import com.example.healthtracking.network.models.Food.FoodResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {


    private List<FoodParsed> foodParseds;
    private LayoutInflater inflater;
    private Context context;
    private String TAG = "Image";

    public FoodAdapter(List<FoodParsed> foodParseds, LayoutInflater inflater, Context context) {
        this.foodParseds = foodParseds;
        this.inflater = inflater;
        this.context = context;
    }

    @NonNull
    @Override
    public FoodAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = inflater.inflate(R.layout.custom_item_menu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.ViewHolder holder, int position) {
        FoodParsed foodParsed = foodParseds.get(position);
        holder.setLabelItem(foodParsed.getFoodItem().getFood_label());
        holder.setTxtFoodCal(foodParsed.getFoodItem().getFoodNutrients().getEnergy_cal());
        Picasso.with(context).load(foodParsed.getFoodItem().getFoodImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return foodParseds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        final private TextView labelItem , txtFoodCal;
        final public ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            this.labelItem = (TextView) view.findViewById(R.id.txtFoodName);
            this.txtFoodCal = (TextView) view.findViewById(R.id.txtFoodCal);
            this.imageView = (ImageView) view.findViewById(R.id.imgFoodImage);
        }

        public void setLabelItem(String labelItem) {
            this.labelItem.setText(labelItem);
        }

        public void setTxtFoodCal(String txtFoodCal) {
            this.txtFoodCal.setText(txtFoodCal);
        }
    }
}
