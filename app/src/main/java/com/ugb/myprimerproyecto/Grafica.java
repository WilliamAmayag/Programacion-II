package com.ugb.myprimerproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class Grafica extends AppCompatActivity {
Long cantidad1;
Long cantidad2;
Long cantidad3;
    PieChartView pieChartView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafica);

        try {
            Bundle recibirDatos = getIntent().getExtras();
           cantidad1 = recibirDatos.getLong("dato1");
           cantidad2 = recibirDatos.getLong("dato2");
           cantidad3 = recibirDatos.getLong("dato3");


            pieChartView = findViewById(R.id.grpEstadistica);
            List listData = new ArrayList<>();
            listData.add(new SliceValue(cantidad1, Color.rgb(0, 73, 253  )).setLabel("P1:" +cantidad1+ "$"));
            listData.add(new SliceValue(cantidad2, Color.parseColor("#00822B")).setLabel("P2:" +cantidad2+ "$"));
            listData.add(new SliceValue(cantidad3, Color.RED).setLabel("P3:" +cantidad3+ "$"));

            PieChartData pieChartData = new PieChartData(listData);
            pieChartData.setHasLabels(true).setValueLabelTextSize(14);

            pieChartView.setPieChartData(pieChartData);


        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Error: "+ e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}