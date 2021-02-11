package com.ugb.myprimerproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity {
    TabHost tbhConversores;
    Button btnConvertir;
    TextView tempVal;
    Spinner spnOpcionDe, spnOpcionA;
    conversores miConversor = new conversores();
    RelativeLayout contenidoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contenidoView = findViewById(R.id.contenidoView);
        tbhConversores = findViewById(R.id.tbhConversores);
        tbhConversores.setup();

        tbhConversores.addTab(tbhConversores.newTabSpec("Monedas").setContent(R.id.tabMonedas).setIndicator("Divisa"));
        tbhConversores.addTab(tbhConversores.newTabSpec("Longitud").setContent(R.id.tabLongitud).setIndicator("Longitud"));
        tbhConversores.addTab(tbhConversores.newTabSpec("Masa").setContent(R.id.tabMasa).setIndicator("Masa"));
        tbhConversores.addTab(tbhConversores.newTabSpec("Almacenamiento").setContent(R.id.tabAlmacenamiento).setIndicator("Almacenamiento"));
        tbhConversores.addTab(tbhConversores.newTabSpec("Tiempo").setContent(R.id.tabTiempo).setIndicator("Tiempo"));
        tbhConversores.addTab(tbhConversores.newTabSpec("Temperatura").setContent(R.id.tabTemperatura).setIndicator("Temperatura"));

        btnConvertir = findViewById(R.id.btnCalcular);
        btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    tempVal = (TextView) findViewById(R.id.txtcantidad);
                    double cantidad = Double.parseDouble(tempVal.getText().toString());

                    spnOpcionDe = findViewById(R.id.cboDe);
                    spnOpcionA = findViewById(R.id.cboA);
                    tempVal = findViewById(R.id.lblRespuesta);
                    tempVal.setText("Respuesta: " + miConversor.convertir(0, spnOpcionDe.getSelectedItemPosition(), spnOpcionA.getSelectedItemPosition(), cantidad));
                }catch (Exception e){
                    tempVal = findViewById(R.id.lblRespuesta);
                    tempVal.setText("Por favor ingrese los valores correspondiente");
                    Toast.makeText(getApplicationContext(), "Por ingrese los valores correspondiente "+ e.getMessage(),Toast.LENGTH_SHORT).show();
                    /*Snackbar snackbar = Snackbar.make(contenidoView, "Por favor ingrese los valores correspondiente", Snackbar.LENGTH_LONG);
                    snackbar.show();*/
                }
            }
        });

        btnConvertir = findViewById(R.id.btnCalcularL);
        btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    tempVal = findViewById(R.id.txtCantidadL);
                    double cantidad = Double.parseDouble(tempVal.getText().toString());

                    spnOpcionDe = findViewById(R.id.cboDeL);
                    spnOpcionA = findViewById(R.id.cboAL);
                    tempVal = findViewById(R.id.lblRespuestaL);

                    tempVal.setText("Respuesta: " + miConversor.convertir(1, spnOpcionDe.getSelectedItemPosition(), spnOpcionA.getSelectedItemPosition(), cantidad));
                }catch (Exception e){
                    tempVal = findViewById(R.id.lblRespuestaL);
                    tempVal.setText("Por favor ingrese los valores correspondiente");
                    Toast.makeText(getApplicationContext(), "Por ingrese los valores correspondiente "+ e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });



        btnConvertir = findViewById(R.id.btnCalcularM);
        btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    tempVal = findViewById(R.id.txtcantidadM);
                    double cantidad = Double.parseDouble(tempVal.getText().toString());

                    spnOpcionDe = findViewById(R.id.cboDeM);
                    spnOpcionA = findViewById(R.id.cboAM);
                    tempVal = findViewById(R.id.lblRespuestaM);

                    tempVal.setText("Respuesta: " + miConversor.convertir(2, spnOpcionDe.getSelectedItemPosition(), spnOpcionA.getSelectedItemPosition(), cantidad));
                }catch (Exception e){
                    tempVal = findViewById(R.id.lblRespuestaM);
                    tempVal.setText("Por favor ingrese los valores correspondiente");
                    Toast.makeText(getApplicationContext(), "Por ingrese los valores correspondiente "+ e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnConvertir = findViewById(R.id.btnCalcularalmacenamiento);
        btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    tempVal = findViewById(R.id.txtcantidadalmacenamiento);
                    double cantidad = Double.parseDouble(tempVal.getText().toString());

                    spnOpcionDe = findViewById(R.id.cboDealmacenamiento);
                    spnOpcionA = findViewById(R.id.cboAalmacenamiento);
                    tempVal = findViewById(R.id.lblRespuestaalmacenamiento);

                    tempVal.setText("Respuesta: " + miConversor.convertir(3, spnOpcionDe.getSelectedItemPosition(), spnOpcionA.getSelectedItemPosition(), cantidad));
                }catch (Exception e){
                    tempVal = findViewById(R.id.lblRespuestaalmacenamiento);
                    tempVal.setText("Por favor ingrese los valores correspondiente");
                    Toast.makeText(getApplicationContext(), "Por ingrese los valores correspondiente "+ e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });






        btnConvertir = findViewById(R.id.btnCalcularTiempo);
        btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    tempVal = findViewById(R.id.txtcantidadTiempo);
                    double cantidad = Double.parseDouble(tempVal.getText().toString());

                    spnOpcionDe = findViewById(R.id.cboDeTiempo);
                    spnOpcionA = findViewById(R.id.cboATiempo);
                    tempVal = findViewById(R.id.lblRespuestaTiempo);

                    tempVal.setText("Respuesta: " + miConversor.convertir(4, spnOpcionDe.getSelectedItemPosition(), spnOpcionA.getSelectedItemPosition(), cantidad));
                }catch (Exception e){
                    tempVal = findViewById(R.id.lblRespuestaTiempo);
                    tempVal.setText("Por favor ingrese los valores correspondiente");
                    Toast.makeText(getApplicationContext(), "Por ingrese los valores correspondiente "+ e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });





        btnConvertir = findViewById(R.id.btnCalcularTemperatura);
        btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    tempVal = findViewById(R.id.txtcantidadTemperatura);
                    double cantidad = Double.parseDouble(tempVal.getText().toString());

                    spnOpcionDe = findViewById(R.id.cboDeTemperatura);
                    spnOpcionA = findViewById(R.id.cboATemperatura);
                    tempVal = findViewById(R.id.lblRespuestaTemperatura);

                    tempVal.setText("Respuesta: " + miConversor.convertir(5, spnOpcionDe.getSelectedItemPosition(), spnOpcionA.getSelectedItemPosition(), cantidad));
                }catch (Exception e){
                    tempVal = findViewById(R.id.lblRespuestaTemperatura);
                    tempVal.setText("Por favor ingrese los valores correspondiente");
                    Toast.makeText(getApplicationContext(), "Por ingrese los valores correspondiente "+ e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}


class conversores{
    double[][] conversor = {
            {1.0, 8.75, 7.77, 24.03, 34.8, 611.10, 0.83, 20.10, 0.73, 1.28},//Divisa
            {1.00, 1000000000.00, 1000000.00, 1000.00, 100.00, 0.001, 39.37008, 3.28084, 1.093613, 0.000621371},//Longitud
            {1.00,453592.4,   45359.24,   4535.924, 453.5924,  45.35924, 4.535924, 0.453592, 16.00, 0.0005},//Masa
            {1.00,0.125,   0.001, 0.000125, 0.000001, 0.000000125, 0.000000001, 0.000000000125,0.000000000001,0.000000000000125 },//Almacenamiento
            {1.00,1000000.00, 1000.00, 0.0166667, 0.000277778,  0.0000115741, 0.00000165344, 0.000000031688088},//Tiempo
            {1.00, 33.8, 274.15},//Temperatura
            {1,1,   1,   1, 1,  1, 1, 1, 1, 1},//Volumen
            {1,1,   1,   1, 1,  1, 1, 1, 1, 1},//Áre
    };
    public double convertir(int opcion, int de, int a, double cantidad){
        return conversor[opcion][a] / conversor[opcion][de] * cantidad;
    }
}
