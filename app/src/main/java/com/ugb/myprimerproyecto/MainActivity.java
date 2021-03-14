package com.ugb.myprimerproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {
// boton flotante
    FloatingActionButton btnadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //relacionamos con xml
        btnadd = findViewById(R.id.btnagregarproducto);

        //evento de tocar el boton agregar producto
       btnadd.setOnClickListener(v-> {
          //metodo para lanzar activity agregar producto
           agregaproductos();
       });
    }

    //metodo de lanzar activity
    private void agregaproductos() {
        //lanzar activity de agregar producto
        Intent i = new Intent(getApplicationContext(), agregarproductos.class);
        startActivity(i);
    }


}