package com.example.mobilemerchants;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class VendorHomeScreen extends AppCompatActivity {

    TextView tvVendorName;
    RecyclerView rvVendorFoodDisplay;
    Button btnAddFood;
    Button btnOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_home_screen);

        tvVendorName = findViewById(R.id.tvVendorName);
        rvVendorFoodDisplay = findViewById(R.id.rvVendorFoodDisplay);
        btnAddFood = findViewById(R.id.btnAddFood);
        btnOrders = findViewById(R.id.btnOrders);

        // todo set tvVendorName
        // todo set RecycleViewer

        btnAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(VendorHomeScreen.this, VendorAddFood.class);
                startActivity(i);
                // todo change to start for result
            }
        });

        btnOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(VendorHomeScreen.this, VendorOrderDisplay.class);
                startActivity(i);
            }
        });
    }
}