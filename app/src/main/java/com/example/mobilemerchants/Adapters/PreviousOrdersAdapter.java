package com.example.mobilemerchants.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mobilemerchants.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PreviousOrdersAdapter extends RecyclerView.Adapter<PreviousOrdersAdapter.ViewHolder> {

    public interface OnClickListener{
        void onItemClicked(int position);
    }

    private Context context;
    private List<PreviousOrders> previousOrders;
    OnClickListener clickListener;

    public PreviousOrdersAdapter(Context context, List<PreviousOrders> previousOrders, OnClickListener clickListener) {
        this.context = context;
        this.previousOrders = previousOrders;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.previous_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PreviousOrders previousOrder = previousOrders.get(position);
        holder.bind(previousOrder);
    }

    @Override
    public int getItemCount() {
        return previousOrders.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvUser;
        private TextView tvItem;
        private TextView tvCost;
        LinearLayout container;

        public ViewHolder (@NonNull View itemView) {
            super(itemView);
            tvUser = itemView.findViewById(R.id.tvUser);
            tvItem = itemView.findViewById(R.id.tvItem);
            tvCost = itemView.findViewById(R.id.tvCost);
            container = itemView.findViewById(R.id.container);
        }

        public void bind(PreviousOrders previousOrders){
            tvUser.setText(previousOrders.getUser().getObjectId());
            tvItem.setText(previousOrders.getOrder().getObjectId());
            tvCost.setText(String.valueOf(previousOrders.getTotal()));
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onItemClicked(getAdapterPosition());
                }
            });
        }
    }
}
