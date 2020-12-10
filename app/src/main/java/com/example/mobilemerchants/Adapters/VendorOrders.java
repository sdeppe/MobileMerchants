package com.example.mobilemerchants.Adapters;


import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("VendorOrders")
public class VendorOrders extends ParseObject {

    public static final String KEY_ORDER_USER_NAME = "Username";
    public static final String KEY_ORDER_FOOD_NAME = "OrderFoodName";
    public static final String KEY_ORDER_FOOD_QUANT = "OrderQuant";
    // public static final String KEY_CONFIRMED = "Confirmed";


    public static String getName() {
        return KEY_ORDER_USER_NAME;
    }

    public static String getFoodName() {
        return KEY_ORDER_FOOD_NAME;
    }

    public static String getFoodQuant() {
        return KEY_ORDER_FOOD_QUANT;
    }
    public void setKeyOrderUserName(String name){
        put(KEY_ORDER_USER_NAME, name);
    }
    public void setKeyOrderFoodName(String foodname){
        put(KEY_ORDER_FOOD_NAME, foodname);
    }
    public void setKeyOrderFoodQuant( int quant){
        put(KEY_ORDER_FOOD_QUANT, quant);
    }
}
