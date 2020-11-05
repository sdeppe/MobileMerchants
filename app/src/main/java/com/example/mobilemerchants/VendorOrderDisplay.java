package com.example.mobilemerchants;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VendorOrderDisplay extends AppCompatActivity {

    RecyclerView rvPastOrders;
    Button btnBack;
    Button btnSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_order_display);

        rvPastOrders = findViewById(R.id.rvPastOrders);
        btnBack = findViewById(R.id.btnBack);
        btnSignOut = findViewById(R.id.btnSignOut);

        // todo setup RecycleViewer to display past orders

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(VendorOrderDisplay.this, VendorHomeScreen.class);
                startActivity(i);
            }
        });

        // todo make sure user is correctly signed out
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(VendorOrderDisplay.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}