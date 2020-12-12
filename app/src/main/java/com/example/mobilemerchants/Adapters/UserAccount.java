package com.example.mobilemerchants.Adapters;


import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("User")
public class UserAccount extends ParseObject {

    public static final String KEY_USERNAME = "username";
    public static final String KEY_FIRST_NAME = "firstName";
    public static final String KEY_LAST_NAME = "lastName";
    public static final String KEY_PASSWORD = "password";

    public static final String KEY_ROLE = "role";



    public UserAccount() {
        super();
    }

   // added ar
    public void setRole(String role){
        put(KEY_ROLE,role);
    }
    //add ar
    public String getUserRole() {

        return getString(KEY_ROLE);
    }



    public String getUsername() {

        return getString(KEY_USERNAME);
    }


    public String getRole(){ return KEY_ROLE;}


    public void setUserName(String username) {
        put(KEY_USERNAME, username);
    }

    public static String getUserFirstName() {
        return KEY_FIRST_NAME;
    }

    public void setUserFirstName(String firstName){
    put(KEY_FIRST_NAME, firstName);
    }

    public static String getUserLastName() {
        return KEY_LAST_NAME;
    }

    public void setUserLastName(String lastName) {
        put(KEY_USERNAME, lastName);
    }

    public static String getPassword() {
        return KEY_PASSWORD;
    }

    public void setPassword(String password) {
        put(KEY_PASSWORD, password);
    }


}