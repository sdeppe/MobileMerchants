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

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

public class SignupActivity extends AppCompatActivity {

    public static final String TAG = "SignupActivity";

    TextView etRole;
    TextView etFirstName;
    TextView etLastName;
    TextView etUsername;
    TextView etPassword;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        if(ParseUser.getCurrentUser() != null){
            returnToMain();
         }

        etRole = findViewById(R.id.etRole);
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                //startActivity(i);

                if( TextUtils.isEmpty(etFirstName.getText()))
                    etFirstName.setError("First Name is Check!!!");
                else if(TextUtils.isEmpty(etLastName.getText()))
                    etLastName.setError("Last name is required");
                else if(TextUtils.isEmpty(etPassword.getText())){
                    etPassword.setError("Password is required");
                }else if(TextUtils.isEmpty(etUsername.getText())){
                    etUsername.setError("Username is required");
                }else if(TextUtils.isEmpty(etRole.getText())){
                    etRole.setError("Role is required");
                }else {

                    Log.i(TAG, "onClick login button");
                    String username = etUsername.getText().toString();
                    String password = etPassword.getText().toString();
                    loginUser(username, password);

                }
            }
        });
    }

    private void loginUser(String username, String password) {
        Log.i(TAG, "Attempting to login user " + username);
        // TODO: navigate to the main activity if the user has signed in properly
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) {

                    Toast.makeText(SignupActivity.this, "Issue with login!", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Issue with login", e);
                    return;
                }
                // TODO: navigate to the main activity if the user has signed in properly
                returnToMain();
                Toast.makeText(SignupActivity.this, "Success!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Return to the main screen
        private void returnToMain(){
            Intent i = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(i);
        }
}