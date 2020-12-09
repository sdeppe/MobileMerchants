package com.example.mobilemerchants;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.PointerEncoder;
import com.parse.SaveCallback;

import java.text.ParseException;

public class VendorAddFood extends AppCompatActivity {

    EditText etFoodName;
    EditText etPrice;
    EditText etDescription;

    Button btnAddItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_add_food);

        etFoodName = findViewById(R.id.etFoodName);
        etPrice = findViewById(R.id.etPrice);
        etDescription = findViewById(R.id.etDescription);
        btnAddItem = findViewById(R.id.btnAddItem);


        // todo verify and add to database



        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String FoodName = etFoodName.getText().toString();
                String FoodDescription = etDescription.getText().toString();
                String FoodPrice = etPrice.getText().toString();
                Double tempPrice = Double.valueOf(FoodPrice);
                Number price = (Number)tempPrice;

//                ParseObject food = new ParseObject("Food");
                ParseQuery<ParseObject> Food = ParseQuery.getQuery("RestaurantShop");
                //"RestaurantShop": { "__type": "Pointer", "className": "Restaurants", "objectId": "<THE_REFERENCED_OBJECT_ID>"

                createObject(FoodName, FoodDescription, price);

            }
        });
    }

    public void createObject(String FoodName, String FoodDecription, Number FoodPrice) {
        ParseObject entity = new ParseObject("Food");

        entity.put("FoodName", FoodName);
        entity.put("FoodDescription", FoodDecription);
        entity.put("FoodPrice" , FoodPrice);
        entity.put("RestaurantShop", new ParseObject("Restaurants"));

        // Saves the new object.
        // Notice that the SaveCallback is totally optional!
        entity.saveInBackground(new SaveCallback() {

            @Override
            public void done(com.parse.ParseException e) {


                // Here you can handle errors, if thrown. Otherwise, "e" should be null
                if (e == null) {
                    System.out.println("Success");
//                    readObject();
                } else {
                    // something went wrong
                    System.out.println("Error");

                }
            }
        });
    }

//    public void readObject() {
//        ParseQuery<ParseObject> query = ParseQuery.getQuery("Food");

        // The query will search for a ParseObject, given its objectId.
        // When the query finishes running, it will invoke the GetCallback
        // with either the object, or the exception thrown
//        query.getInBackground("");
//        query.getInBackground("<PARSE_OBJECT_ID>", new GetCallback<ParseObject>() {
//
//
//            public void done(ParseObject result, ParseException e) {
//                if (e == null) {
//                    System.out.println("Success");
//                    System.out.println(result);
//                    updateObject();
//                } else {
//                    // something went wrong
//                    System.out.println("Error");
//
//                }
//            }
//        });
//    }
//
//    public void updateObject() {
//        ParseQuery<ParseObject> query = ParseQuery.getQuery("Food");
//
//        // Retrieve the object by id
//        query.getInBackground("<PARSE_OBJECT_ID>", new GetCallback<ParseObject>() {
//
//
//
//            public void done(ParseObject entity, ParseException e) {
//                if (e == null) {
//                    // Update the fields we want to
//                    entity.put("FoodName", "A string");
//                    entity.put("FoodDescription", "A string");
//                    entity.put("FoodPrice", 1);
//                    entity.put("RestaurantShop", new ParseObject("Restaurants"));
//
//                    // All other fields will remain the same
//                    entity.saveInBackground();
//                }
//            }
//        });
//    }
//
//    public void deleteObject() {
//        // TODO: modify me!
//        final boolean deleteAttributesOnly = true;
//
//        ParseQuery<ParseObject> query = ParseQuery.getQuery("Food");
//
//        // Retrieve the object by id
//        query.getInBackground("<PARSE_OBJECT_ID>", new GetCallback<ParseObject>() {
//
//
//            public void done(ParseObject entity, ParseException e) {
//                if (e == null) {
//                    if (deleteAttributesOnly) {
//                        // If you want to undefine a specific field, do this:
//                        entity.remove("FoodName");
//                        entity.remove("FoodDescription");
//                        entity.remove("FoodPrice");
//                        entity.remove("RestaurantShop");
//
//                        // Then save the changes
//                        entity.saveInBackground();
//                    } else {
//                        // Otherwise, you can delete the entire ParseObject from the database
//                        entity.deleteInBackground();
//                    }
//                }
//            }
//        });
//    }

}