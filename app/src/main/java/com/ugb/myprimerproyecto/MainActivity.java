package com.ugb.myprimerproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TabHost tbh;
    Button btn;
    TextView temp;
    Spinner spnde, spna;
    conversores miconvertor = new conversores();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tbh = findViewById(R.id.tbh);
        tbh.setup();

        tbh.addTab(tbh.newTabSpec("Moneda").setContent(R.id.tabmonedas).setIndicator("M"));
        tbh.addTab(tbh.newTabSpec("Longitud").setContent(R.id.tablongitud).setIndicator("L"));
        tbh.addTab(tbh.newTabSpec("Masa").setContent(R.id.tabmasa).setIndicator("P"));

        btn = findViewById(R.id.btncalcular);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp = (TextView)findViewById(R.id.txtcantidad);
                double cantidad = Double.parseDouble(temp.getText().toString());

                spnde = findViewById(R.id.cbode);
                spna = findViewById(R.id.cboa);

                temp = findViewById(R.id.lblRespuesta);
                temp.setText( "Respuesta: "+ miconvertor.calcular(0, spnde.getSelectedItemPosition(),spna.getSelectedItemPosition(), cantidad) );
            }
        });

    }
}

class conversores{
    double[][] conversor = {
            {1.0,8.75,7.77, 24.03,34.8,611.10},/*Monedas*/
            {1.0},/*Longitud*/
            {1.0}/*Masa*/
    };
    public double calcular(int opcion, int de, int a, double cantidad){
        return conversor[opcion][a] / conversor[opcion][de] * cantidad;
    }
}