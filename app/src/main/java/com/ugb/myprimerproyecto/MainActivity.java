package com.ugb.myprimerproyecto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

import java.math.BigDecimal;
import java.math.RoundingMode;


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

        tbhConversores.addTab(tbhConversores.newTabSpec("Monedas").setContent(R.id.tabMonedas).setIndicator("", getResources().getDrawable(R.drawable.ic_moneda)));
        tbhConversores.addTab(tbhConversores.newTabSpec("Longitud").setContent(R.id.tabLongitud).setIndicator("", getResources().getDrawable(R.drawable.ic_longitud)));
        tbhConversores.addTab(tbhConversores.newTabSpec("Masa").setContent(R.id.tabMasa).setIndicator("", getResources().getDrawable(R.drawable.ic_masa)));
        tbhConversores.addTab(tbhConversores.newTabSpec("Almacenamiento").setContent(R.id.tabAlmacenamiento).setIndicator("", getResources().getDrawable(R.drawable.ic_almacenamiento)));
        tbhConversores.addTab(tbhConversores.newTabSpec("Tiempo").setContent(R.id.tabTiempo).setIndicator("", getResources().getDrawable(R.drawable.ic_tiempo)));
        tbhConversores.addTab(tbhConversores.newTabSpec("Temperatura").setContent(R.id.tabTemperatura).setIndicator("", getResources().getDrawable(R.drawable.ic_temperatura)));
        tbhConversores.addTab(tbhConversores.newTabSpec("Volumen").setContent(R.id.tabVolumen).setIndicator("", getResources().getDrawable(R.drawable.ic_volumen)));
        tbhConversores.addTab(tbhConversores.newTabSpec("Area").setContent(R.id.tabArea).setIndicator("", getResources().getDrawable(R.drawable.ic_area)));

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

                    tempVal.setText("Respuesta: " + miConversor.convertirTemperatura(spnOpcionDe.getSelectedItemPosition(), spnOpcionA.getSelectedItemPosition(), cantidad));
                }catch (Exception e){
                    tempVal = findViewById(R.id.lblRespuestaTemperatura);
                    tempVal.setText("Por favor ingrese los valores correspondiente");
                    Toast.makeText(getApplicationContext(), "Por ingrese los valores correspondiente "+ e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });





        btnConvertir = findViewById(R.id.btnCalcularVolumen);
        btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    tempVal = findViewById(R.id.txtVolumen);
                    double cantidad = Double.parseDouble(tempVal.getText().toString());

                    spnOpcionDe = findViewById(R.id.cboDeLVolumen);
                    spnOpcionA = findViewById(R.id.cboALVolumen);
                    tempVal = findViewById(R.id.lblRespuestaVolumen);

                    tempVal.setText("Respuesta: " + miConversor.convertir(6, spnOpcionDe.getSelectedItemPosition(), spnOpcionA.getSelectedItemPosition(), cantidad));
                }catch (Exception e){
                    tempVal = findViewById(R.id.lblRespuestaVolumen);
                    tempVal.setText("Por favor ingrese los valores correspondiente");
                    Toast.makeText(getApplicationContext(), "Por ingrese los valores correspondiente "+ e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });



        btnConvertir = findViewById(R.id.btnCalcularArea);
        btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    tempVal = findViewById(R.id.txtcantidadArea);
                    double cantidad = Double.parseDouble(tempVal.getText().toString());

                    spnOpcionDe = findViewById(R.id.cboDeArea);
                    spnOpcionA = findViewById(R.id.cboAArea);
                    tempVal = findViewById(R.id.lblRespuestaArea);

                    tempVal.setText("Respuesta: " + miConversor.convertir(7, spnOpcionDe.getSelectedItemPosition(), spnOpcionA.getSelectedItemPosition(), cantidad));
                }catch (Exception e){
                    tempVal = findViewById(R.id.lblRespuestaArea);
                    tempVal.setText("Por favor ingrese los valores correspondiente");
                    Toast.makeText(getApplicationContext(), "Por ingrese los valores correspondiente "+ e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(getApplicationContext(),"Index: "+ item, Toast.LENGTH_LONG).show();
        int idmenu = item.getItemId();

        switch (idmenu){
            case R.id.mnxmoneda:
                tbhConversores.setCurrentTab(0);
                break;

            case R.id.mnxlongitud:
                tbhConversores.setCurrentTab(1);
                break;

            case R.id.mnxmasa:
                tbhConversores.setCurrentTab(2);
                break;

            case R.id.mnxalmacenamiento:
                tbhConversores.setCurrentTab(3);
                break;
            case R.id.mnxtiempo:
                tbhConversores.setCurrentTab(4);
                break;
            case R.id.mnxtemperatura:
                tbhConversores.setCurrentTab(5);
                break;
            case R.id.mnxVolumen:
                tbhConversores.setCurrentTab(6);
                break;
            case R.id.mnxarea:
                tbhConversores.setCurrentTab(7);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mnxconversor,menu);
        return true;
    }
}

class conversores{

    public static double round(double value) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(5, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    double[][] conversor = {
            {1.0, 8.75, 7.77, 24.03, 34.8, 611.10, 0.83, 20.10, 0.73, 1.28},//Divisa
            {1.00, 1000000000.00, 1000000.00, 1000.00, 100.00, 0.001, 39.37008, 3.28084, 1.093613, 0.000621371},//Longitud
            {1.00,453592.4,   45359.24,   4535.924, 453.5924,  45.35924, 4.535924, 0.453592, 16.00, 0.0005},//Masa
            {1.00,0.125,   0.001, 0.000125, 0.000001, 0.000000125, 0.000000001, 0.000000000125,0.000000000001,0.000000000000125 },//Almacenamiento
            {1.00,1000000.00, 1000.00, 0.0166667, 0.000277778,  0.0000115741, 0.00000165344, 0.000000031688088,3.171e-9,3.171e-10},//Tiempo
            {1,1,1,1,1,1,1,1,1,1},//Temperatura
            {1, 1.00, 0.001, 0.000001, 0.033814, 0.004227, 0.002113, 0.000264, 0.061024, 0.000035},//Volumen
            {1.00,100.00, 0.0001,  0.00000001, 0.0000000001,  0.155, 0.00107639, 0.000119599, 0.000000024710538, 0.00000000003861}//Área
    };

    public double convertir(int opcion, int de, int a, double cantidad){
        return round(conversor[opcion][a] / conversor[opcion][de] * cantidad);
    }
    public double convertirTemperatura(int de, int a, double cantidad){
        double respuesta = 0;
        //Celsius
        if (de == 0) {
            if (a == 0) {
                respuesta = cantidad;
            } else if (a == 1) {
                respuesta = (cantidad * 9 / 5) + 32;
            } else if (a == 2) {
                respuesta =cantidad + 273.15;
            }
        }
        //Fahrenheit
        if (de == 1) {
            if (a == 0) {
                respuesta = (cantidad - 32) * 5 / 9;
            } else if (a == 1) {
                respuesta = cantidad;
            } else if (a == 2) {
                respuesta = (cantidad - 32) * 5 / 9 + 273.15;
            }
        }
        // Kelvin
        if (de == 2) {
            if (a == 0) {
                respuesta = cantidad -273.15;
            } else if (a == 1) {
                respuesta = (cantidad - 273.15) * 9 / 5 + 32;
            } else if (a == 2) {
                respuesta = cantidad;
            }
        }

        return round(respuesta);
    }
}
