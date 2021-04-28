package com.ugb.myprimerproyecto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton btnadd;
    DB miconexion;
    ListView ltspelis;
    Cursor datospeliscursor = null;
    ArrayList<pelis> pelisArrayList=new ArrayList<pelis>();
    ArrayList<pelis> pelisArrayListCopy=new ArrayList<pelis>();
    pelis misPelis;
    JSONArray jsonArrayDatosPelis;
    JSONObject jsonObjectDatosPelis;
    utilidades u;
    String idlocal;
    detectarInternet di;
    int position = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        di = new detectarInternet(getApplicationContext());
        btnadd = findViewById(R.id.btnagregar);
        btnadd.setOnClickListener(v->{
            Agregar("nuevo");
        });
        obtenerDatos();
      //  Buscar();
       }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_productos, menu);
        try {
            if(di.hayConexionInternet()) {
                AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
                datospeliscursor.moveToPosition(adapterContextMenuInfo.position);
                position = adapterContextMenuInfo.position;
               menu.setHeaderTitle(jsonArrayDatosPelis.getJSONObject(position).getJSONObject("value").getString("titulo"));
            } else {
                AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo)menuInfo;
                datospeliscursor.moveToPosition(adapterContextMenuInfo.position);
               menu.setHeaderTitle(datospeliscursor.getString(1));
              }
            idlocal = datospeliscursor.getString(0);
              }catch (Exception e){
            mensajes(e.getMessage());
        }
    }
    @Override

    public boolean onContextItemSelected(@NonNull MenuItem item) {
        try {
            switch (item.getItemId()) {
                case R.id.mxnAgregar:
                    Agregar("nuevo");
                    break;
                case R.id.mxnModificar:
                    Modificar ("modificar");
                    break;
                case R.id.mxnEliminar:
                    Eliminar();
                    break;
            }
        }catch (Exception ex){
            mensajes(ex.getMessage());
        }
        return super.onContextItemSelected(item);
    }

    private void Eliminar(){
       //agregar eliminar
    }



    private void Modificar(String accion){
       //Agregar modificar
    }

    private void Agregar(String accion){
        Bundle parametros = new Bundle();
        parametros.putString("accion", accion);
        Intent i = new Intent(getApplicationContext(), agregarpelis.class);
        i.putExtras(parametros);
        startActivity(i);
    }

    private void obtenerDatosOffLine(){
        try {
            miconexion = new DB(getApplicationContext(), "", null, 1);
            datospeliscursor = miconexion.administracion_de_pelis("consultar", null);
            if( datospeliscursor.moveToFirst() ){
             mostrarDatos();
            } else {
                mensajes("No hay datos");
            }
        }catch (Exception e){
            mensajes(e.getMessage());
        }
    }

    private void obtenerDatosOnLine(){
        try {
            ConexionconServer conexionconServer = new ConexionconServer();
            String resp = conexionconServer.execute(u.urlServer, "GET").get();
            jsonObjectDatosPelis=new JSONObject(resp);
            jsonArrayDatosPelis = jsonObjectDatosPelis.getJSONArray("rows");
            mostrarDatos();
        }catch (Exception ex){
            mensajes(ex.getMessage());
        }
    }

    private void obtenerDatos(){
        if(di.hayConexionInternet()) {
            mensajes("Mostrando datos desde la nube");
            obtenerDatosOnLine();
            obtenerDatosOffLine();
        } else {
           mensajes("Mostrando datos locales");
           obtenerDatosOffLine();
        }
    }

    private void mostrarDatos(){
        try{
           ltspelis = findViewById(R.id.listpelis);
            pelisArrayList.clear();
            pelisArrayListCopy.clear();
            JSONObject jsonObject;
            if(di.hayConexionInternet()) {
                if(jsonArrayDatosPelis.length()>0) {
                    for (int i = 0; i < jsonArrayDatosPelis.length(); i++) {
                        jsonObject = jsonArrayDatosPelis.getJSONObject(i).getJSONObject("value");
                        misPelis = new pelis(
                                jsonObject.getString("_id"),
                                jsonObject.getString("_rev"),
                                jsonObject.getString("titulo"),
                                jsonObject.getString("sinopsis"),
                                jsonObject.getString("duracion"),
                                jsonObject.getString("precio"),
                                jsonObject.getString("urlfoto"),
                                jsonObject.getString("urltriler")
                        );
                        pelisArrayList.add(misPelis);
                    }}
                 } else {
                do{
                    misPelis = new pelis(
                            datospeliscursor.getString(0),//
                            datospeliscursor.getString(1),//
                            datospeliscursor.getString(1),//
                            datospeliscursor.getString(2),//
                            datospeliscursor.getString(3),//
                            datospeliscursor.getString(4),//
                            datospeliscursor.getString(5), //
                            datospeliscursor.getString(6) //
                    );
                    pelisArrayList.add(misPelis);
                }while(datospeliscursor.moveToNext());
              }
              adaptadorImagenes adaptadorImagenes = new adaptadorImagenes(getApplicationContext(), pelisArrayList);
            ltspelis.setAdapter(adaptadorImagenes);
            registerForContextMenu(ltspelis);
            pelisArrayListCopy.addAll(pelisArrayList);

        }catch (Exception e){
            mensajes(e.getMessage());
        }
    }

    private void mensajes(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }
}
