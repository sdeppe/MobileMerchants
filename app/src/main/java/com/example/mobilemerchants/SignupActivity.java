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

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import com.parse.SignUpCallback;

public class SignupActivity extends AppCompatActivity {

    public static final String TAG = "SignupActivity";
    EditText etRole;
    EditText etContactInfo;
    EditText etFirstName;
    EditText etLastName;
    EditText etUsername;
    EditText etPassword;
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
        etContactInfo = findViewById(R.id.etContactInfo);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


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

                //Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                //startActivity(i);
                Log.i(TAG, "onClick login button");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String email = etContactInfo.getText().toString();
                String firstName = etFirstName.getText().toString();
                String lastName = etLastName.getText().toString();
                String role = etRole.getText().toString();
                createUser(username, password, email, firstName, lastName, role);
            }
        });
    }
    //            public void createUser(final String username, final String password, String email, String firstName, String lastName, String role) {

    public void createUser(String username, String password, String email, String firstName, String lastName, String role) {
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.put("firstName", firstName);
        user.put("lastName", lastName);
        user.put("role", role);

        // Other fields can be set just like any other ParseObject,
        // using the "put" method, like this: user.put("attribute", "its value");
        // If this field does not exists, it will be automatically created
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                    Toast.makeText(SignupActivity.this,"Success!",Toast.LENGTH_LONG).show();
                    returnToMain();
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    Log.e(TAG,"Error", e);
                    Toast.makeText(SignupActivity.this,"Error!",Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    //Return to the main screen
    private void returnToMain(){
        Intent i = new Intent(SignupActivity.this, LoginActivity.class);
        startActivity(i);

    }
}