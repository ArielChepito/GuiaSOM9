package com.example.mosaic.guiasom9;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView foodList;

    FoodModel foodModel;

    ArrayList<String> itemList = new ArrayList<>();


    EditText cajaNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        foodList =(ListView) findViewById(R.id.foodList);
        foodModel = new FoodModel(this);

        cajaNombre = (EditText) findViewById(R.id.txtNombre);
        cajaNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                itemList.clear();
                if(i2> 1)
                {
                    listar(foodModel.listFilter(charSequence+""));
                }
                else
                {

                    listar(foodModel.listAll());
                }

                //
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        itemList.clear();
        listar(foodModel.listAll());

    }

    public void listar(Cursor c)
    {
        c.moveToFirst();

        while(c.isAfterLast() == false){
            String item = c.getString(c.getColumnIndex(FoodContract._ID))+" ";
            item += c.getString(c.getColumnIndex(FoodContract.COLUMN_NAME_FOOD))+" ";
            item += c.getString(c.getColumnIndex(FoodContract.COLUMN_NAME_CALORIES))+" ";
            item += c.getString(c.getColumnIndex(FoodContract.COLUMN_NAME_FOODDATE))+" ";

            itemList.add(item);

            c.moveToNext();
        }


        foodModel.db.close();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,itemList);

        foodList.setAdapter(arrayAdapter);
    }

    public void addFood(View view) {
        Intent i = new Intent(this,AddFoodActivity.class);

        startActivity(i);
    }
    public void modifyFood(View view) {
        /* Intent i = new Intent(this,DataGraphicsActivity.class);
        startActivity(i);*/
    }
    public void viewChart(View view) {
        Intent i = new Intent(this,DataGraphicsActivity.class);
        startActivity(i);
    }

    public void deleteFood(View view) {
        Intent i = new Intent(this,DeleteActivity.class);
        startActivity(i);
    }
}