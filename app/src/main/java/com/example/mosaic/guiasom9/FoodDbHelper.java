package com.example.mosaic.guiasom9;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mosaic.guiasom9.FoodContract;


public class FoodDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;
    //Pruebe cambiar la versión
    //En otra ejecución
    public static final String DATABASE_NAME = "FoodDb.db";

    public FoodDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(FoodContract.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(FoodContract.SQL_DELETE_ENTRIES);

        onCreate(sqLiteDatabase);
    }

    //On DownGrade no implementado
}