package com.ugb.myprimerproyecto;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class agregarproductos extends AppCompatActivity {

    FloatingActionButton btnregresar;
    ImageView imgfotodeproducto;
    Intent tomarfotointent;
    String urldefoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregarproductos);

        //relacion entre java y xml
        btnregresar = findViewById(R.id.btnregresar);
        imgfotodeproducto = findViewById(R.id.imgfotoproducto);


        btnregresar.setOnClickListener(v -> {
            regresarmainactivity();
        });

        imgfotodeproducto.setOnClickListener(v -> {
            tomarfoto();
        });
    }



//metodo para regresar a pantalla anterior
    private void regresarmainactivity() {
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }

    //metodo de tomar foto
    private void tomarfoto() {
        //lanzar activity de camara
        tomarfotointent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //si la camara fue lanzada exitosamente
        if (tomarfotointent.resolveActivity(getPackageManager()) != null ){

            File mifoto = null;

            try {
                mifoto = crearfoto();
            }catch (Exception e){
                mensajes(e.getMessage());
            }

            //si la foto fue tomada
            if (mifoto != null){
                try{
                    //buscar foto para mostrarla
                    Uri urifotoproducto = FileProvider.getUriForFile(agregarproductos.this, "com.ugb.myprimerproyecto.fileprovider",mifoto);
                    tomarfotointent.putExtra(MediaStore.EXTRA_OUTPUT, urifotoproducto);
                    startActivityForResult(tomarfotointent,1);
                }catch (Exception e){
                    mensajes(e.getMessage());
                }
            } else {
                mensajes("No fue posible tomar la foto");
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //si la foto a sido correcta mapear la foto para que sea visible
        try{
            if( requestCode==1 && resultCode==RESULT_OK ){
                Bitmap imagenBitmap = BitmapFactory.decodeFile(urldefoto);
                imgfotodeproducto.setImageBitmap(imagenBitmap);
            }
        }catch (Exception e){
            mensajes(e.getMessage());
        }
    }

    //metodo para rutas y creacion de nombres
    private File crearfoto() throws IOException {
        //nombre de la foto
        String tiempo = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String nombredeimagen = "img_"+ tiempo +"_";

        //Ruta de almacenamiento
        File rutadealmacenamiento = getExternalFilesDir(Environment.DIRECTORY_DCIM);
        //si la ruta de almacenamiento no existe
        if(!rutadealmacenamiento.exists()){
            //crearla
            rutadealmacenamiento.mkdirs();
        }

        //Crear temporal de foto tomada
        File image = File.createTempFile(nombredeimagen,".jpg",rutadealmacenamiento);
        // y guardar ruta
        urldefoto = image.getAbsolutePath();
        return image;
    }

        //metodo para mostrar mensajes
    private void mensajes(String msg){
        Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG).show();
    }
}