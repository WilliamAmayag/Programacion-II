package com.ugb.myprimerproyecto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONObject;

public class agregarpelis extends AppCompatActivity {

    FloatingActionButton btnregresar;
    ImageView imgfotodepeli;
    VideoView vdidepeli;
    Intent tomarfotointent;
    String urldefoto, urldevideo;
    String idpeli,idlocal, accion = "nuevo", rev;
    Button btnagregar, btncargarvideo;
    DB miconexion;
    TextView temp;
    utilidades miUrl;
    String urifoto, urivideo;
    detectarInternet di;
    private static final int RPQ= 100;
    private static final int RIG= 101;
    private static final int RVD= 102;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregarpelis);

        //conexion
        miconexion = new DB(getApplicationContext(),"",null,1);

        //relacion entre java y xml
        btnregresar = findViewById(R.id.btnregresar);
        btncargarvideo = findViewById(R.id.btncargarvideo);
        imgfotodepeli = findViewById(R.id.imgfotopelicula);
        vdidepeli = findViewById(R.id.vdipelicula);
        btnagregar = findViewById(R.id.btnguardarpelicula);

        //btn atras
        btnregresar.setOnClickListener(v -> {
            regresarmainactivity();
        });

        //btn tomar foto
        imgfotodepeli.setOnClickListener(v -> {
            abrirgaleriaimagen();
        });

        btncargarvideo.setOnClickListener(v -> {
            abrirgaleriavideo();
        });


        //btn agregar producto
        btnagregar.setOnClickListener(v -> {
           agregarproducto();
        });

        permisos();
       mostrardatosproducto();

        MediaController mediaController = new MediaController(this);
        vdidepeli.setMediaController(mediaController);
        mediaController.setAnchorView(vdidepeli);

    }



    private void permisos() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
        if (ActivityCompat.checkSelfPermission(agregarpelis.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
         }else {
            ActivityCompat.requestPermissions(agregarpelis.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},RPQ);
        }
    }else {
      }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent dataimagen) {

            if (resultCode == Activity.RESULT_OK && dataimagen != null) {

                if (requestCode == RIG) {
                Uri photo = dataimagen.getData();
                imgfotodepeli.setImageURI(photo);
                urldefoto = photo.toString();
            }else if (requestCode == RVD){
                    Uri video = dataimagen.getData();
                    vdidepeli.setVideoURI(video);
                    urldevideo = video.toString();
                }

        }

        super.onActivityResult(requestCode, resultCode, dataimagen);
    }





    private void abrirgaleriaimagen(){
        Intent i = new Intent(Intent.ACTION_GET_CONTENT );
        i.setType("image/*");
        startActivityForResult(i, RIG);
    }

    private void abrirgaleriavideo(){
        Intent i = new Intent(Intent.ACTION_GET_CONTENT );
        i.setType("video/*");
        startActivityForResult(i, RVD);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode== RPQ){
            if(permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            }else{
                mensajes("Por favor dame los permisos");
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    //metodo para regresar a pantalla anterior
    private void regresarmainactivity() {
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }






   
    //Metodo para agregar producto
    private void agregarproducto() {
        try {
            temp = findViewById(R.id.txtTitulo);
            String titulo = temp.getText().toString();

            temp = findViewById(R.id.txtsinopsis);
            String sinopsis = temp.getText().toString();

            temp = findViewById(R.id.txtduracion);
            String duracion = temp.getText().toString();

            temp = findViewById(R.id.txtprecio);
            String precio = temp.getText().toString();


            JSONObject datospelis = new JSONObject();
            if(accion.equals("modificar") && idpeli.length()>0 && rev.length()>0 ){
                datospelis.put("_id",idpeli);
                datospelis.put("_rev",rev);
            }

            datospelis.put("titulo",titulo);
            datospelis.put("sinopsis",sinopsis);
            datospelis.put("duracion",duracion);
            datospelis.put("precio",precio);
            datospelis.put("urlfoto",urldefoto);
            datospelis.put("urltriler",urldevideo);

                String[] datos = {idlocal, titulo, sinopsis, duracion, precio, urifoto, urldevideo };




            di = new detectarInternet(getApplicationContext());
            if (di.hayConexionInternet()) {
                enviarDatos guardarpelis = new enviarDatos(getApplicationContext());
                String resp = guardarpelis.execute(datospelis.toString()).get();
            }

            miconexion.administracion_de_pelis(accion, datos);
            mensajes("Registro guardado con exito.");

            regresarmainactivity();

        }catch (Exception w){
mensajes(w.getMessage());

        }

    }

    private void mostrardatosproducto() {

           try {
               Bundle recibirparametros = getIntent().getExtras();
               accion = recibirparametros.getString("accion");
               idlocal = recibirparametros.getString("idlocal");
              // mensajes(idlocal);

               if(accion.equals("modificar")){
                   JSONObject datos = new JSONObject(recibirparametros.getString("datos")).getJSONObject("value");

                   idpeli = datos.getString("_id");

                   rev = datos.getString("_rev");

                   temp = findViewById(R.id.txtTitulo);
                   temp.setText(datos.getString("titulo"));

                   temp = findViewById(R.id.txtsinopsis);
                   temp.setText(datos.getString("sinopsis"));

                   temp = findViewById(R.id.txtduracion);
                   temp.setText(datos.getString("duracion"));

                   temp = findViewById(R.id.txtprecio);
                   temp.setText(datos.getString("precio"));


                   urldefoto =  datos.getString("urlfoto");
                   urldevideo =  datos.getString("urltriler");


                   imgfotodepeli.setImageURI(Uri.parse(urifoto));
                   vdidepeli.setVideoURI(Uri.parse(urivideo));

                  // Bitmap bitmap = BitmapFactory.decodeFile(urldefoto);
              //     imgfotodeproducto.setImageBitmap(bitmap);
               }
           }catch (Exception ex){
               mensajes(ex.getMessage());
           }


    }


    //metodo para mostrar mensajes
    private void mensajes(String msg){
        Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG).show();
    }

}