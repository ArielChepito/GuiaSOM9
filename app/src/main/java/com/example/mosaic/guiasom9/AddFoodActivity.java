package com.example.mosaic.guiasom9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

public class AddFoodActivity extends AppCompatActivity {

    EditText food;
    EditText calories;
    DatePicker fooddate;

    FoodModel foodModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        foodModel = new FoodModel(this);

        food =(EditText) findViewById(R.id.food);
        calories =(EditText) findViewById(R.id.calories);
        fooddate = (DatePicker) findViewById(R.id.fooddate);

    }

    public void addFood(View view) {

        String foodString = food.getText().toString();
        Double caloriesString = Double.parseDouble(calories.getText().toString());

        int day = fooddate.getDayOfMonth();
        int month = fooddate.getMonth() + 1;
        int year = fooddate.getYear();



        String foodDateString = year+"-"+month+"-"+day;

        foodModel.insert(foodString, caloriesString,foodDateString);

        foodModel.db.close();

        finish();
    }
}