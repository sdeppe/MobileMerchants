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
    private OnFoodListener mOnFoodListener;

    public FoodItemAdapter(Context context, List<Food> foods, OnFoodListener onFoodListener) {
        this.context = context;
        this.foods = foods;
        this.mOnFoodListener = onFoodListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_food, parent, false);
        return new ViewHolder(view, mOnFoodListener);
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


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvFoodName;
        private TextView tvFoodDescription;
        private TextView tvFoodPrice;

        OnFoodListener onFoodListener;
        public ViewHolder (@NonNull View itemView, OnFoodListener onFoodListener ) {
            super(itemView);
            tvFoodName = itemView.findViewById(R.id.tvUser);
            tvFoodDescription = itemView.findViewById(R.id.tvItem);
            tvFoodPrice = itemView.findViewById(R.id.tvCost);

            this.onFoodListener =  onFoodListener;
            itemView.setOnClickListener(this);

        }



        public void bind(Food food){
            tvFoodName.setText(food.getFoodName());
            tvFoodDescription.setText(food.getFoodDescription());
            tvFoodPrice.setText(String.valueOf(food.getFoodPrice()));

        }

        @Override
        public void onClick(View v) {
                onFoodListener.onFoodClick(getAdapterPosition());
        }
    }
    // detects and interprets click
    public interface OnFoodListener{
        void onFoodClick(int position);
    }
}