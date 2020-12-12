package com.example.mobilemerchants;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.app.role.RoleManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.example.mobilemerchants.AdminConfirmActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.mobilemerchants.Adapters.AccountDisplayAdapter;
import com.example.mobilemerchants.Adapters.UserAccount;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";

    EditText etUsername;
    EditText etPassword;
    Button btnLogin;
    Button btnSignup;
    Button btnAdmin;
    ParseUser user = ParseUser.getCurrentUser();

    UserAccount check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_activity);

        if (ParseUser.getCurrentUser() != null) {
            goMainActivity();
        }


        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);
        btnAdmin = findViewById(R.id.btnAdmin);

        // todo verify account in database
        // send to right screen depending on account type

//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(LoginActivity.this, UserAccountDisplay.class);
//                startActivity(i);
//            }
//        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(i);
            }
        });

        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(LoginActivity.this, AdminConfirmActivity.class); // should start at Vendor home screen for testing its now set to vendor order display .

             //   Intent i = new Intent(LoginActivity.this, AdminConfirmActivity.class);

                startActivity(i);
          }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                if (TextUtils.isEmpty(etUsername.getText())) {
                    etUsername.setError("User name is required!");
                } else if (TextUtils.isEmpty(etPassword.getText())) {
                    etPassword.setError("Password is required");
            } else {
                   ParseUser.logInInBackground(etUsername.getText().toString(),
                            etPassword.getText().toString(),
                            new LogInCallback() {

                                @Override
                                public void done(ParseUser user, ParseException e) {
                                    if(user != null){
                                        if (user.get("role").equals("user")) {
                                            // TODO: better error handling
                                            goMainActivity();
                                            Toast.makeText(LoginActivity.this, "Welcome Back!", Toast.LENGTH_SHORT).show();
                                            Log.i(TAG, "User logged in "+ user.get("role"));
                                            Intent i = new Intent(LoginActivity.this, RestaurantDisplay.class);
                                            startActivity(i);
                                        }else if(user.get("role").equals("admin")){
                                            Toast.makeText(LoginActivity.this, "Welcome Back!", Toast.LENGTH_SHORT).show();
                                            Log.i(TAG, "Admin logged in "+ user.get("role"));
                                            Intent i = new Intent(LoginActivity.this, AdminConfirmActivity.class);
                                            startActivity(i);
                                        }else{
                                            Toast.makeText(LoginActivity.this, "Welcome Back!", Toast.LENGTH_SHORT).show();
                                            Log.i(TAG, "Vendor logged in " + user.get("role"));
                                            Intent i = new Intent(LoginActivity.this, VendorHomeScreen.class);
                                            startActivity(i);
                                        }

                                    } else {
                                        ParseUser.logOut();
                                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }


                            });


                }
            }
        });
    }
    private void goMainActivity(){


        Intent i = new Intent(this, FoodDisplay.class);
        startActivity(i);
        finish();
    }
    private void goVendorActivity(){

        Intent i = new Intent(this, VendorOrderDisplay.class);

        startActivity(i);
        finish();
    }
    private void goAdminActivity(){
        Intent i = new Intent(this, AdminConfirmActivity.class);
        startActivity(i);
        finish();

    }
}


