package com.example.mobilemerchants.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobilemerchants.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.ViewHolder> {

    private Context context;
    private List<Food> foods;

    public FoodItemAdapter(Context context, List<Food> foods) {
        this.context = context;
        this.foods = foods;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_food, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Food food = foods.get(position);
        holder.bind(food);
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvFoodName;
        private TextView tvFoodDescription;
        private TextView tvFoodPrice;

        public ViewHolder (@NonNull View itemView) {
            super(itemView);
            tvFoodName = itemView.findViewById(R.id.tvFoodOrderName);
            tvFoodDescription = itemView.findViewById(R.id.tvFoodDescription);
            tvFoodPrice = itemView.findViewById(R.id.tvFoodPrice);

        }

        public void bind(Food food){
            tvFoodName.setText(food.getFoodName());
            tvFoodDescription.setText(food.getFoodDescription());
            tvFoodPrice.setText(food.getFoodPrice());

        }
    }
}