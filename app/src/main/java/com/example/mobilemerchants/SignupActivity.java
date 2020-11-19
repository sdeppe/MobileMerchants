package com.example.mobilemerchants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends AppCompatActivity {

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

        etRole = findViewById(R.id.etRole);
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);

        // todo check that the account doesn't exist in database
        // add to database

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
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
                }else{
                    ParseUser user = new ParseUser();
                    user.setUsername(etFirstName.getText().toString().trim());
                    user.setPassword(etPassword.getText().toString().trim());
                    user.put("name",etFirstName.getText().toString().trim());

                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e != null ) {
                                Toast.makeText(SignupActivity.this, "Welcome Back Please login" , Toast.LENGTH_LONG).show();
                                goMainActivity();
                            }else{
                               // ParseUser.logOut();
                                 Toast.makeText(SignupActivity.this,"error",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }

            }
        });
    }
    private void goMainActivity(){
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}