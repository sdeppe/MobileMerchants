package com.example.mobilemerchants.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobilemerchants.R;
import com.parse.ParseFile;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AccountDisplayAdapter extends RecyclerView.Adapter<AccountDisplayAdapter.ViewHolder> {

    private Context context;
    private List<UserAccount> users;

    public AccountDisplayAdapter(Context context, List<UserAccount> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_userdisplay, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserAccount user = users.get(position);
        holder.bind(user);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView etFirstNameUpdate;
        private TextView etUpdateLastName;
        private TextView etUpdateUserName;
        private TextView etUpdatePassword;



        public ViewHolder (@NonNull View itemView) {
            super(itemView);
            etFirstNameUpdate = itemView.findViewById(R.id.etFirstNameUpdate);
           etUpdateLastName = itemView.findViewById(R.id.etUpdateLastName);
           etUpdateUserName = itemView.findViewById(R.id.etUpdateUsername);
           etUpdatePassword = itemView.findViewById(R.id.etUpdatePassword);
        }

        public void bind(UserAccount user){
            etFirstNameUpdate.setText(user.getUserFirstName());
            etUpdateLastName.setText(user.getUserLastName());
            etUpdateUserName.setText(user.getUsername());
            etUpdatePassword.setText(user.getPassword());
        }
    }
}