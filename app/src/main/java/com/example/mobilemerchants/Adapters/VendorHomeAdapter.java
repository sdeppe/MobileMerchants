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

public class VendorHomeAdapter extends RecyclerView.Adapter<VendorHomeAdapter.ViewHolder> {

    private Context context;
    private List<Restaurant> restaurants;

    public VendorHomeAdapter(Context context, List<Restaurant> restaurants) {
        this.context = context;
        this.restaurants = restaurants;
    }

    @NonNull
    @Override
    public VendorHomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_restaurant, parent, false);
        return new VendorHomeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VendorHomeAdapter.ViewHolder holder, int position) {
        Restaurant restaurant = restaurants.get(position);
        holder.bind(restaurant);
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvRestaurantName;
        private TextView tvRestaurantDescription;

        public ViewHolder (@NonNull View itemView) {
            super(itemView);
            tvRestaurantName = itemView.findViewById(R.id.tvRestaurantName);
            tvRestaurantDescription = itemView.findViewById(R.id.tvRestaurantDescription);
        }

        public void bind(Restaurant restaurant){
            tvRestaurantName.setText(restaurant.getName());
            tvRestaurantDescription.setText(restaurant.getDescription());
        }
    }
}
