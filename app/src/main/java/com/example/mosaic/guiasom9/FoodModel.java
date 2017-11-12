package com.example.mosaic.guiasom9;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mosaic.guiasom9.FoodDbHelper;

public class FoodModel {
    Context context;

    FoodDbHelper foodDbHelper;
    SQLiteDatabase db;


    public FoodModel(Context context){
        this.context = context;
        foodDbHelper = new FoodDbHelper(context);
        db = foodDbHelper.getWritableDatabase();
    }


    public long insert(String food, Double calories, String fooddate){

        ContentValues values = new ContentValues();
        //Valores de los campos de la tabla. Columna->Valor
        values.put(FoodContract.COLUMN_NAME_FOOD,food);
        values.put(FoodContract.COLUMN_NAME_CALORIES,calories);
        values.put(FoodContract.COLUMN_NAME_FOODDATE,fooddate);

        //Realizamos el insert
        long newRowid = db.insert(FoodContract.TABLE_NAME,null,values);
        return newRowid;

    }

    //Listar sin filtro
    public Cursor listAll(){

        db = foodDbHelper.getReadableDatabase();

        //Especificamos los campos (columnas) que deseamos
        String [] projection = { FoodContract._ID,
                FoodContract.COLUMN_NAME_FOOD,
                FoodContract.COLUMN_NAME_CALORIES,
                FoodContract.COLUMN_NAME_FOODDATE
        };

        //Ejecutamos la consulta.
        //No tiene parámetros
        Cursor c = db.query(
                FoodContract.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        return c;
    }

    //Listar con filtro
    public Cursor listFilter(String filtro){

        db = foodDbHelper.getReadableDatabase();

        //Especificamos los campos (columnas) que deseamos
        String [] projection = { FoodContract._ID,
                FoodContract.COLUMN_NAME_FOOD,
                FoodContract.COLUMN_NAME_CALORIES,
                FoodContract.COLUMN_NAME_FOODDATE
        };


        //Ahora definimos una condición de filtro
        String selection = FoodContract.COLUMN_NAME_FOOD + " like ? ";
        String[] selectionArgs = { "%"+filtro+"%" };

        //Desamos ver ordenamiento descendiente del resultado
        String sortOrder =
                FoodContract.COLUMN_NAME_FOOD + " DESC";

        //Realizamos la consulta
        Cursor c = db.query(
                FoodContract.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        return c;
    }

    public void delete(long id){
        String selection = FoodContract._ID + " = ?";
        String[] selectionArgs = { String.valueOf(id) };

        db.delete(FoodContract.TABLE_NAME,selection,selectionArgs);

    }
}


