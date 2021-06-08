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

import com.an.biometric.BiometricCallback;
import com.an.biometric.BiometricManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BiometricCallback {

    Button login, registro;
    TextView temp;
    DB miconexion;
    utilidades u;
    JSONArray jsonArrayDatosvotantes;
    JSONObject jsonObjectDatosvotantes;
    Cursor datosusuariocursor = null;
    detectarInternet di;
    String dui,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        di = new detectarInternet(getApplicationContext());
        login = findViewById(R.id.btniniciar);
        registro = findViewById(R.id.btnregistrar);

        login.setOnClickListener(v->{
            logilocal();
        });

        registro.setOnClickListener(v->{
           Intent i = new Intent(getApplicationContext(), registrorvotante.class);
           startActivity(i);
        });

        new BiometricManager.BiometricBuilder(MainActivity.this)
                .setTitle("VotoFacil")
                .setSubtitle("Identificate")
                .setDescription("Por favor coloca tu huella en el sensor")
                .setNegativeButtonText("Cancelar")
                .build()
                .authenticate(MainActivity.this);
    }

    private void logilocal() {
        temp = findViewById(R.id.txtuss);
        dui = temp.getText().toString();

        temp = findViewById(R.id.txtpass);
        pass = temp.getText().toString();
        logi();


    }

    private void logi() {
     try {

         ConexionconServer conexionconServer = new ConexionconServer();
         String resp = conexionconServer.execute(u.urlobtenerdatosvotantes, "GET").get();
         jsonObjectDatosvotantes=new JSONObject(resp);
         jsonArrayDatosvotantes = jsonObjectDatosvotantes.getJSONArray("rows");


         JSONObject jsonObject;
         if(di.hayConexionInternet()) {
             if(jsonArrayDatosvotantes.length()>0) {
                 mensajes("Buscando");
                 for (int i = 0; i < jsonArrayDatosvotantes.length(); i++) {

                     jsonObject = jsonArrayDatosvotantes.getJSONObject(i).getJSONObject("value");

                    if (dui.equalsIgnoreCase(jsonObject.getString("dui"))){

                        if (pass.equalsIgnoreCase(jsonObject.getString("pass"))){
                            i = jsonArrayDatosvotantes.length()+1;
                            String nombre = jsonObject.getString("nombre");
                            String duii = jsonObject.getString("dui");
                            String telefono = jsonObject.getString("telefono");
                            String mail = jsonObject.getString("correo");
                            String padss = jsonObject.getString("pass");
                            String permiso = jsonObject.getString("permiso");

                            mensajes("Bienvenido " + nombre);



                            Bundle parametros = new Bundle();
                            parametros.putString("nombre", nombre);
                            parametros.putString("duii", duii);
                            parametros.putString("telefono", telefono);
                            parametros.putString("mail", mail);
                            parametros.putString("padss", padss);
                            parametros.putString("permiso", permiso);
                            Intent lanzar = new Intent(getApplicationContext(), mostrarpostulados.class);
                            lanzar.putExtras(parametros);
                            startActivity(lanzar);

                            try { String[] datos = {duii,nombre, duii, telefono, mail, padss};
                                miconexion.agregar_usuario("nuevo", datos);

                            }catch (Exception e){}

                        }
                     }
                 }}
         }

     }catch (Exception e){
      mensajes(e.getMessage());
     }
    }


    private void mensajes(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSdkVersionNotSupported() {

    }

    @Override
    public void onBiometricAuthenticationNotSupported() {

    }

    @Override
    public void onBiometricAuthenticationNotAvailable() {

    }

    @Override
    public void onBiometricAuthenticationPermissionNotGranted() {

    }

    @Override
    public void onBiometricAuthenticationInternalError(String error) {

    }

    @Override
    public void onAuthenticationFailed() {

    }

    @Override
    public void onAuthenticationCancelled() {

    }

    @Override
    public void onAuthenticationSuccessful() {


        try {
            miconexion = new DB(getApplicationContext(), "", null, 1);
            datosusuariocursor = miconexion.consultar_usuario("consultar");

            if( datosusuariocursor.moveToFirst() ){
              dui=   datosusuariocursor.getString(2);
              pass=   datosusuariocursor.getString(5);
                logi();
            }
        }catch (Exception e){
            mensajes(e.getMessage());
        }

    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {

    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {

    }
}