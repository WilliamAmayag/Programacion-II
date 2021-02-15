package com.ugb.myprimerproyecto;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import lecho.lib.hellocharts.view.PieChartView;

public class MainActivity extends AppCompatActivity {
EditText editText;
Button btnaccion;
TextView temporal;
Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


btnaccion = (Button) findViewById(R.id.calcular);

btnaccion.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

   try {
       temporal = (EditText) findViewById(R.id.txtcantidad1);
       int valor1 = Integer.parseInt(temporal.getText().toString());

       temporal = findViewById(R.id.txtcantidad2);
       int valor2 = Integer.parseInt(temporal.getText().toString());

       temporal = findViewById(R.id.txtcantidad3);
       int valor3 = Integer.parseInt(temporal.getText().toString());


       intent = new Intent(MainActivity.this, Grafica.class);
Bundle enviarnumero = new Bundle();
enviarnumero.putLong("dato1", valor1);
enviarnumero.putLong("dato2", valor2);
enviarnumero.putLong("dato3", valor3);
intent.putExtras(enviarnumero);
startActivity(intent);

   }catch (Exception e){

       Toast.makeText(getApplicationContext(),"Ingrese dato",Toast.LENGTH_SHORT).show();

   }
    }
});


    }


}