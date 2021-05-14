package com.ugb.myprimerproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button login, registro;
    TextView temp;
    DB miconexion;
    Cursor datosusuariocursor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.btniniciar);
        registro = findViewById(R.id.btnregistrar);

        login.setOnClickListener(v->{
            logi();
        });

        registro.setOnClickListener(v->{
           Intent i = new Intent(getApplicationContext(), registrorvotante.class);
           startActivity(i);
        });
    }

    private void logi() {
     try {
         temp = findViewById(R.id.txtuss);
         String dui = temp.getText().toString();

         temp = findViewById(R.id.txtpass);
         String pass = temp.getText().toString();

         miconexion = new DB(getApplicationContext(), "", null, 1);
         datosusuariocursor = miconexion.consultar_usuario("consultar", dui,pass);
         if( datosusuariocursor.moveToFirst() ) {

             String nombre = datosusuariocursor.getString(1);
             String duii = datosusuariocursor.getString(2);
             String telefono = datosusuariocursor.getString(3);
             String mail = datosusuariocursor.getString(4);
             String padss = datosusuariocursor.getString(5);

mensajes("Bienvenido " + nombre);
             Intent i = new Intent(MainActivity.this, mostrarpostulados.class);
             i.putExtra(mostrarpostulados.nombre, nombre);
             i.putExtra(mostrarpostulados.duii,duii);
             i.putExtra(mostrarpostulados.telefono, telefono);
             i.putExtra(mostrarpostulados.mail, mail);
             i.putExtra(mostrarpostulados.padss,padss);
             startActivity(i);
         }
     }catch (Exception e){
         mensajes("no se encontro el usuario");
     }
    }


    private void mensajes(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }
}