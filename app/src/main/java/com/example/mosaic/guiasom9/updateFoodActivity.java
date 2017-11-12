package com.example.mosaic.guiasom9;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class updateFoodActivity extends AppCompatActivity {


    EditText food;
    EditText calories;
    DatePicker fooddate;
    EditText idUpdate;
    Button buscar;
    FoodModel foodModel;
    int ID;



        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_food);

            foodModel = new FoodModel(this);
            idUpdate = (EditText) findViewById(R.id.idUpdate);
            food =(EditText) findViewById(R.id.foodUpdate);
            calories =(EditText) findViewById(R.id.caloriesUpdate);
            fooddate = (DatePicker) findViewById(R.id.fooddateUpdate);
            buscar = (Button) findViewById(R.id.buscarUpdate);

    }


    public void updateFood(View view) {

        int day = fooddate.getDayOfMonth();
        int month = fooddate.getMonth() + 1;
        int year = fooddate.getYear();



        String foodDateString = year+"-"+month+"-"+day;



        foodModel.update(ID, food.getText().toString().trim(),Integer.parseInt(calories.getText().toString().trim()),foodDateString);
        foodModel.db.close();
    }

    public void searchFood(View view) {
        ID= Integer.parseInt(idUpdate.getText().toString());
        Cursor c = foodModel.listOne(ID +"");
        c.moveToFirst();

        while(c.isAfterLast() == false){
            ///food.setText(c.getString(c.getColumnIndex(FoodContract._ID))+" ");
            food.setText(c.getString(c.getColumnIndex(FoodContract.COLUMN_NAME_FOOD)).trim());
            calories.setText(c.getString(c.getColumnIndex(FoodContract.COLUMN_NAME_CALORIES)).trim());
            String date=  c.getString(c.getColumnIndex(FoodContract.COLUMN_NAME_FOODDATE)).trim();
            c.moveToNext();
        }
     ///   foodModel.db.close();


    }
}
