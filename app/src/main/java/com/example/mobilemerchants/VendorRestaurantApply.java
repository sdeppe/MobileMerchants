package com.example.mobilemerchants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseUser;

public class VendorRestaurantApply extends AppCompatActivity {

    private TextView tvApplyUsername;
    private TextView etApplyName;
    private TextView etApplyDescription;
    private Button btnApplyBack;
    private Button btnApply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_restaurant_apply);

        tvApplyUsername = findViewById(R.id.tvApplyUsername);
        etApplyName = findViewById(R.id.etApplyName);
        etApplyDescription = findViewById(R.id.etApplyDescription);
        btnApplyBack = findViewById(R.id.btnApplyBack);
        btnApply = findViewById(R.id.btnApply);

        tvApplyUsername.setText(ParseUser.getCurrentUser().getUsername());

        btnApplyBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(VendorRestaurantApply.this, VendorHomeScreen.class);
                startActivity(i);
            }
        });

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // todo check if empty
                String restName = etApplyName.getText().toString();
                String restDesc = etApplyDescription.getText().toString();
                // todo check if restaurant name exists under different user
                // todo save data
                // todo redirect
            }
        });
    }
}