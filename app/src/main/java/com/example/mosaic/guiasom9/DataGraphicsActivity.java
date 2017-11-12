package com.example.mosaic.guiasom9;


import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;


import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
//import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;


import java.util.ArrayList;
import java.util.List;

public class DataGraphicsActivity extends AppCompatActivity {

    BarChart chart;
    FoodModel foodModel;
    int contador=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_graphics);

        chart = (BarChart) findViewById(R.id.chart);

        //Llamando a FoodModel que contiene la información a mostrar
        foodModel = new FoodModel(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        contador=0;
        Cursor c = foodModel.listAll();

        c.moveToFirst();

        //Especificamos una lista donde almacenar los items a mostrar en la
        //Grafica de barra

        List<BarEntry> entries = new ArrayList<>();

        //Recorremos los datos del cursor y se los agregamos a la lista entries
        while(c.isAfterLast() == false){
            contador++;
            entries.add(
                    new BarEntry(
                            contador,//Contador
                            c.getFloat(c.getColumnIndex(FoodContract.COLUMN_NAME_CALORIES)),//Calorías
                            c.getString(c.getColumnIndex(FoodContract.COLUMN_NAME_FOOD))//Alimento
                    )

            );

            c.moveToNext();


        }

        foodModel.db.close();

        //Creamos el set de datos a partir de las entradas
        BarDataSet set = new BarDataSet(entries,"BarDataSet");

        //Definiendo lista de colores para las barras.
        List<Integer> colors = new ArrayList<>();
        colors.add(Color.BLUE);
        colors.add(0xffff6c00);
        colors.add(0xff457dd7);
        colors.add(Color.MAGENTA);
        colors.add(0xfde04938);

        //Le asignamos los colores.
        set.setColors(colors);

        //Instanciamos BarData
        BarData data = new BarData(set);

        //Asignamos el formato a mostrar.
        //Deseamos mostrar en éste caso
        //El nombre del alimento sobre la barra
        /*data.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return  entry.getData().toString();
            }
        });*/



        data.setBarWidth(0.9f); // set custom bar width


        //Dibujando el gráfico

        /*PUEDE COMENTAR LAS SIGUIENTES LÍNEA DE CÓDIGO Y VER UN RESULTADO DIFERENTE*/
        chart.getXAxis().setGranularity(1);
        chart.getXAxis().setGranularityEnabled(true);
        chart.getXAxis().setAxisMinValue(0);
        chart.getXAxis().setAxisMaxValue(entries.size()+1);
        /*FIN DE LAS LINEAS A COMENTAR*/


        chart.setDescription("CALORIAS CONSUMIDAS");
        chart.setData(data);
        chart.getXAxis().setDrawGridLines(false);
        chart.invalidate(); // refresh

    }
}


