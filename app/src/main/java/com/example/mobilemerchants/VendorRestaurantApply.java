package com.example.mobilemerchants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobilemerchants.Adapters.Restaurant;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class VendorRestaurantApply extends AppCompatActivity {
    public static final String TAG = "VendorRestaurantApply";
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
                if (TextUtils.isEmpty(etApplyName.getText())) {
                    etApplyName.setError("Restaurant Name is required");
                } else if (TextUtils.isEmpty(etApplyDescription.getText())) {
                    etApplyDescription.setError("Restaurant description is required");
                } else {
                    Restaurant restaurant = new Restaurant();
                    restaurant.setRestaurantName(etApplyName.getText().toString().trim());
                    restaurant.setRestaurantDescription(etApplyDescription.getText().toString().trim());
                    restaurant.put("RestaurantOwner", ParseUser.getCurrentUser().getObjectId());
                    restaurant.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if(e != null){
                                Log.e(TAG, "Error while saving", e);
                                Toast.makeText(VendorRestaurantApply.this, "Error while saving!", Toast.LENGTH_SHORT).show();
                            }
                            Log.i(TAG, "Post save was successful");
                            Toast.makeText(VendorRestaurantApply.this, "Successfully Applied!", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(VendorRestaurantApply.this, VendorHomeScreen.class);
                            startActivity(i);
                        }
                    });
                }
            }
        });
    }
}