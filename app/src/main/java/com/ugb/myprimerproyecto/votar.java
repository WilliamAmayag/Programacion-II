package com.ugb.myprimerproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONObject;

public class votar extends AppCompatActivity {
    FloatingActionButton btnregresar;
    ImageView imgfoto;
    Button btnagregar;
    VideoView vdide;
    String urldefoto, urldevideo,id, accion = "votar", rev, duipostulado;
    TextView temp;
    String lognombre,logdui,logtelefono,logmail,logpadss;
    detectarInternet di;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votar);
        vdide = findViewById(R.id.vdipe);
        imgfoto = findViewById(R.id.imgfoto);
        btnregresar = findViewById(R.id.btnregresar);
        btnagregar = findViewById(R.id.btnvotar);

        btnregresar.setOnClickListener(v -> {
            regresarmainactivity();
        });

        btnagregar.setOnClickListener(v -> {
            votar();
        });


        try {
            mostrardatos();
            controles();
        }catch (Exception e){

        }
    }

    private void votar(){
        try {
            temp = findViewById(R.id.txtNombre);
            String nombre = temp.getText().toString();


            temp = findViewById(R.id.txtPropuesta);
            String propuesta = temp.getText().toString();

            temp = findViewById(R.id.txtOtros);
            String otros = temp.getText().toString();

            JSONObject datoss = new JSONObject();

            datoss.put("_id",logdui);
            datoss.put("nombrepostulado",nombre);
            datoss.put("duipostulado",duipostulado);
            datoss.put("nombrevotante",lognombre);
            datoss.put("duivotante",logdui);

            di = new detectarInternet(getApplicationContext());
            if (di.hayConexionInternet()) {
                enviarVotos guardarpelis = new enviarVotos(getApplicationContext());
                String resp = guardarpelis.execute(datoss.toString()).get();
            }

            mensajes("Registro guardado con exito.");
            regresarmainactivity();
        }catch (Exception w){
            mensajes(w.getMessage());
        }

    }
    private void controles() {
        MediaController mediaController = new MediaController(this);
        vdide.setMediaController(mediaController);
        mediaController.setAnchorView(vdide);
    }

    private void mostrardatos() {
        try {
            Bundle recibirparametros = getIntent().getExtras();
            accion = recibirparametros.getString("accion");
            lognombre = recibirparametros.getString("nombre");
            logdui = recibirparametros.getString("duii");
            logtelefono = recibirparametros.getString("telefono");
            logmail = recibirparametros.getString("mail");
            logpadss = recibirparametros.getString("padss");
            if(accion.equals("votar")){
                JSONObject datos = new JSONObject(recibirparametros.getString("datos")).getJSONObject("value");
                id = datos.getString("_id");

                rev = datos.getString("_rev");
                duipostulado = datos.getString("dui");

                temp = findViewById(R.id.txtNombre);
                temp.setText(datos.getString("nombre"));

                temp = findViewById(R.id.txtPropuesta);
                temp.setText(datos.getString("propuesta"));

                temp = findViewById(R.id.txtOtros);
                temp.setText(datos.getString("otro"));


                urldefoto =  datos.getString("urlfoto");
                urldevideo =  datos.getString("urltriler");

                imgfoto.setImageURI(Uri.parse(urldefoto));
                vdide.setVideoURI(Uri.parse(urldevideo));

            }
        }catch (Exception ex){

        }
    }

    private void regresarmainactivity() {
        Bundle parametros = new Bundle();
        parametros.putString("nombre", lognombre);
        parametros.putString("duii", logdui);
        parametros.putString("telefono", logtelefono);
        parametros.putString("mail", logmail);
        parametros.putString("padss", logpadss);
        Intent lanzar = new Intent(getApplicationContext(), mostrarpostulados.class);
        lanzar.putExtras(parametros);
        startActivity(lanzar);
    }

    private void mensajes(String msg){
        Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG).show();
    }
}