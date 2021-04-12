package com.ugb.myprimerproyecto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
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
import android.widget.EditText;
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
// boton flotante
    FloatingActionButton btnadd;
    DB miconexion;
    ListView listaproductos;
    Cursor datosproductoscursor = null;
    ArrayList<productos> productosArrayList = new ArrayList<productos>();
    ArrayList<productos> productosArrayListcopy = new ArrayList<productos>();
    productos misproductos;
    JSONArray jsonArrayDatosProductos;
    JSONObject jsonObjectDatosProductos;
    utilidades u;
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //relacionamos con xml
        btnadd = findViewById(R.id.btnagregarproducto);

        //comprobar datos online o ofline
            comprobardatos();

        //evento de tocar el boton agregar producto
       btnadd.setOnClickListener(v-> {
          //metodo para lanzar activity agregar producto
           agregaproductos("nuevo", new String[]{});
       });
      buscarProductos();

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_productos,menu);

        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo)menuInfo;
        datosproductoscursor.moveToPosition(adapterContextMenuInfo.position);
        menu.setHeaderTitle(datosproductoscursor.getString(1));
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        try {

            switch (item.getItemId()) {
                case R.id.mxnAgregar:
                    agregaproductos("nuevo", new String[]{});
                    break;
                case R.id.mxnModificar:
                    String[] datos = {
                            datosproductoscursor.getString(0),
                            datosproductoscursor.getString(1),
                            datosproductoscursor.getString(2),
                            datosproductoscursor.getString(3),
                            datosproductoscursor.getString(4),
                            datosproductoscursor.getString(5),
                            datosproductoscursor.getString(6)
                    };
                    agregaproductos("modificar", datos);
                    break;
                case R.id.mxnEliminar:
                    eliminarProducto();

                    break;
            }
        } catch (Exception ex) {
            mensajes(ex.getMessage());
        }
        return super.onContextItemSelected(item);
    }


    private void comprobardatosoffline(){
        miconexion = new DB(getApplicationContext(),"",null,1);
        datosproductoscursor = miconexion.administracion_de_productos("consultar",null);

        if( datosproductoscursor.moveToFirst() ){
            //si se encuemtran datos cargarlos
            mostrarDatosProductosOfline();
        } else {
            //sino enviar a guardar datos
            mensajes("No hay datos");
            agregaproductos("nuevo", new String[]{});
        }
    }

    //metodos para mostrar los datos encontrados con imagen a la par
    private void  mostrarDatosProductosOfline() {
        listaproductos = findViewById(R.id.listproductos);
        productosArrayList.clear();
        productosArrayListcopy.clear();

        do{
            misproductos = new productos(
                    datosproductoscursor.getString(0),//idproducto
                    datosproductoscursor.getString(1),//codigo
                    datosproductoscursor.getString(2),//descripcion
                    datosproductoscursor.getString(3),//marca
                    datosproductoscursor.getString(4),//presentacion
                    datosproductoscursor.getString(5), //precio
                    datosproductoscursor.getString(6) //urldefoto
            );
            productosArrayList.add(misproductos);
        }while(datosproductoscursor.moveToNext());
        adaptadorImagenes adaptadorImagenes = new adaptadorImagenes(getApplicationContext(), productosArrayList);
        listaproductos.setAdapter(adaptadorImagenes);

        registerForContextMenu(listaproductos);
        productosArrayListcopy.addAll(productosArrayList);
    }

    private void eliminarProducto(){
        try {
            AlertDialog.Builder confirmacion = new AlertDialog.Builder(MainActivity.this);
            confirmacion.setTitle("Esta seguro de eliminar el registro?");
            confirmacion.setMessage(datosproductoscursor.getString(2));
            confirmacion.setPositiveButton("Si", (dialog, which) -> {
                miconexion = new DB(getApplicationContext(), "", null, 1);
                datosproductoscursor = miconexion.administracion_de_productos("eliminar", new String[]{datosproductoscursor.getString(0)});
                comprobardatos();
                mensajes("Registro eliminado con exito");
                dialog.dismiss();
            });
            confirmacion.setNegativeButton("No", (dialog, which) -> {
                mensajes("Eliminacion canelada por el usuario");
                dialog.dismiss();
            });
            confirmacion.create().show();
        } catch (Exception ex){
            mensajes(ex.getMessage());
        }
    }

    private void buscarProductos() {
        TextView tempVal = findViewById(R.id.txtbuscarproducto);
        tempVal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                productosArrayList.clear();
                if (tempVal.getText().toString().length()<1){
                    productosArrayList.addAll(productosArrayListcopy);
                } else{
                    for (productos PB : productosArrayListcopy){
                        String nombre = PB.getDescripcion();
                        String codigo = PB.getCodigo();
                        String marca = PB.getMarca();
                        String presentacion = PB.getPresentacion();
                        String precio = PB.getPrecio();
                        String buscando = tempVal.getText().toString().trim().toLowerCase();
                        if(nombre.toLowerCase().contains(buscando) ||
                                codigo.toLowerCase().contains(buscando) ||
                                marca.toLowerCase().contains(buscando) ||
                                presentacion.toLowerCase().contains(buscando) ||
                                precio.toLowerCase().contains(buscando)){
                            productosArrayList.add(PB);
                        }
                    }
                }
                adaptadorImagenes adaptadorImagenes = new adaptadorImagenes(getApplicationContext(), productosArrayList);
                listaproductos.setAdapter(adaptadorImagenes);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    //metodo de lanzar activity
    private void agregaproductos(String accion, String[] datos) {
        try {
            Bundle parametroProductos = new Bundle();
            parametroProductos.putString("accion", accion);


            parametroProductos.putStringArray("datos",datos );
            //lanzar activity de agregar producto

            Intent i = new Intent(getApplicationContext(), agregarproductos.class);
            i.putExtras(parametroProductos);
            startActivity(i);
        }catch (Exception ex){
            mensajes(ex.getMessage());
    }}

    private void comprobardatosOnline(){
        try {
            ConexionConElServer conexionConElServerServer = new ConexionConElServer();
            String resp = conexionConElServerServer.execute(u.urlServer, "GET").get();

            jsonObjectDatosProductos=new JSONObject(resp);
            jsonArrayDatosProductos = jsonObjectDatosProductos.getJSONArray("rows");
            mostrarDatosProductosOnLine();
        }catch (Exception ex){
           mensajes(ex.getMessage());
        }
    }

    private void mostrarDatosProductosOnLine() {
        try{
            if(jsonArrayDatosProductos.length()>0){
                listaproductos = findViewById(R.id.listproductos);
                productosArrayList.clear();
                productosArrayListcopy.clear();

                JSONObject jsonObject;
                for(int i=0; i<jsonArrayDatosProductos.length(); i++){
                    jsonObject=jsonArrayDatosProductos.getJSONObject(i).getJSONObject("value");
                    misproductos = new productos(
                            jsonObject.getString("_id"),
                            jsonObject.getString("codigo"),
                            jsonObject.getString("descripcion"),
                            jsonObject.getString("marca"),
                            jsonObject.getString("presentacion"),
                            jsonObject.getString("precio"),
                            jsonObject.getString("urlfoto")
                    );
                    productosArrayList.add(misproductos);
                }
                adaptadorImagenes adaptadorImagenes = new adaptadorImagenes(getApplicationContext(), productosArrayList);
                listaproductos.setAdapter(adaptadorImagenes);

                registerForContextMenu(listaproductos);
                productosArrayListcopy.addAll(productosArrayList);
            }else{
                mensajes("No hay datos");
                agregaproductos("nuevo", new String[]{});
            }
        }catch (Exception e){
            mensajes(e.getMessage());
        }
    }


    //metodo para comprobar si hay datos
    private void comprobardatos() {
        comprobardatosOnline();
    }


    class productos{
        String idproducto;
        String codigo;
        String descripcion;
        String marca;
        String presentacion;
        String precio;
        String urlfoto;

        public productos(String idproducto, String codigo, String descripcion, String marca, String presentacion, String precio, String urlfoto) {
            this.idproducto = idproducto;
            this.codigo = codigo;
            this.descripcion = descripcion;
            this.marca = marca;
            this.presentacion = presentacion;
            this.precio = precio;
            this.urlfoto = urlfoto;
        }

        public String getIdproducto() {
            return idproducto;
        }

        public void setIdproducto(String idproducto) {
            this.idproducto = idproducto;
        }

        public String getCodigo() {
            return codigo;
        }

        public void setCodigo(String codigo) {
            this.codigo = codigo;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getMarca() {
            return marca;
        }

        public void setMarca(String marca) {
            this.marca = marca;
        }

        public String getPresentacion() {
            return presentacion;
        }

        public void setPresentacion(String presentacion) {
            this.presentacion = presentacion;
        }

        public String getPrecio() {
            return precio;
        }

        public void setPrecio(String precio) {
            this.precio = precio;
        }

        public String getUrlfoto() {
            return urlfoto;
        }

        public void setUrlfoto(String urlfoto) {
            this.urlfoto = urlfoto;
        }
    }

    private void mensajes(String msg){
        Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG).show();
    }

    private class ConexionConElServer extends AsyncTask<String, String, String> {
        HttpURLConnection urlConnection;

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... parametros) {
            StringBuilder result = new StringBuilder();
            try {
                String uri = parametros[0];
                String metodo = parametros[1];
                URL url = new URL(uri);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod(metodo);

                InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String linea;
                while ((linea = bufferedReader.readLine()) != null) {
                    result.append(linea);
                }
            } catch (Exception e) {
                Log.i("GET", e.getMessage());
            }
            return result.toString();
        }
    }
}