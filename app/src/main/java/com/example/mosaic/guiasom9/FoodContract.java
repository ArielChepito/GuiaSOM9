package com.example.mosaic.guiasom9;

import android.provider.BaseColumns;

public class FoodContract implements BaseColumns {

    //Las siguientes constantes nos permitirán
    //Utilizarlas desde cualquier clase externa.
    public static final String TABLE_NAME = "FOOD_HISTORY";
    public static final String _ID = "ID";
    public static final String COLUMN_NAME_FOOD = "FOOD";
    public static final String COLUMN_NAME_CALORIES = "CALORIES";
    public static final String COLUMN_NAME_FOODDATE = "FOODDATE";

    //Creamos la sentencia SQL para creación de la tabla
    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME + " ( " +
            _ID + " INTEGER PRIMARY KEY," +
            COLUMN_NAME_FOOD + " TEXT, " +
            COLUMN_NAME_CALORIES + " REAL, " +
            COLUMN_NAME_FOODDATE + " TEXT ) ";

    //Creamos la sentencia SQL para la eliminación de la tabla
    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;
}
