package com.example.healthtracking.network.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthtracking.R;
import com.example.healthtracking.network.models.Food.FoodResponse;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    private ArrayList<FoodResponse> foodResponses;
    private LayoutInflater inflater;
    private Context context;

    public FoodAdapter(ArrayList<FoodResponse> foodResponse, Context context) {
        this.foodResponses = foodResponse;
        this.inflater = LayoutInflater.from(context);
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
        FoodResponse foodResponse = foodResponses.get(position);
//        holder.setLabelItem(foodResponse.getParsed().getFoodItem().getFood_label());
//        holder.setTxtFoodCal(foodResponse.getParsed().getFoodItem().getFoodNutrients().getEnergy_cal());
//        Picasso.with(context).load("http://cinema.areas.su/up/images/" + movieResponse.getPoster()).into(holder.coverCinema);
    }

    @Override
    public int getItemCount() {
        return foodResponses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        final private TextView labelItem , txtFoodCal;

        public ViewHolder(View view) {
            super(view);
            this.labelItem = (TextView) view.findViewById(R.id.txtFoodName);
            this.txtFoodCal = (TextView) view.findViewById(R.id.txtFoodCal);
        }

        public void setLabelItem(String labelItem) {
            this.labelItem.setText(labelItem);
        }

        public void setTxtFoodCal(String txtFoodCal) {
            this.txtFoodCal.setText(txtFoodCal);
        }
    }
}
