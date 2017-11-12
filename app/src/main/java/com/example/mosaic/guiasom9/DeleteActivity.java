package com.example.mosaic.guiasom9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DeleteActivity extends AppCompatActivity {

    FoodModel foodModel;
    EditText id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        foodModel = new FoodModel(this);
        id=(EditText)findViewById(R.id.idFood);
    }

    public void deleteFood(View view) {

        long idToDelete = Long.parseLong(id.getText().toString());

        foodModel.delete(idToDelete);
        foodModel.db.close();

        finish();
    }
}