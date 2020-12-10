package com.example.mobilemerchants.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilemerchants.R;

import java.util.List;

public class VendorOrderDisplayAdapter extends RecyclerView.Adapter<VendorOrderDisplayAdapter.ViewHolder> {

    private Context context;
    private List<VendorOrders> orders;

    public VendorOrderDisplayAdapter(Context context, List<VendorOrders> restaurants) {
        this.context = context;
        this.orders = orders;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VendorOrderDisplayAdapter.ViewHolder holder, int position) {
        VendorOrders order = orders.get(position);
        holder.bind(order);
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvUserName;
        private TextView tvFoodName;
        private TextView tvQuant;

        public ViewHolder (@NonNull View itemView) {
            super(itemView);


            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvFoodName = itemView.findViewById(R.id.tvFoodOrderName);
            tvQuant = itemView.findViewById(R.id.tvQuant);
        }

        public void bind(VendorOrders restaurant){
            tvUserName.setText(VendorOrders.getName());
            tvFoodName.setText(VendorOrders.getFoodName());
            tvQuant.setText(VendorOrders.getFoodQuant());

        }
    }
}
