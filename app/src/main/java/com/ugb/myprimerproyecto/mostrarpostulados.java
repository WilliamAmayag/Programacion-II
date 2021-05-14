package com.ugb.myprimerproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class mostrarpostulados extends AppCompatActivity {
    public static  final String nombre="nombre";
    public static  final String duii="duii";
    public static  final String telefono="telefono";
    public static  final String mail="mail";
    public static  final String padss="padss";

    TextView nombrel, duil, telefotol,maill,padssl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrarpostulados);

        nombrel = findViewById(R.id.nombre);
        duil = findViewById(R.id.duii);
        telefotol = findViewById(R.id.telefono);
        maill = findViewById(R.id.mail);
        padssl = findViewById(R.id.padss);

        nombrel.setText(getIntent().getStringExtra("nombre"));
        duil.setText(getIntent().getStringExtra("duii"));
        telefotol.setText(getIntent().getStringExtra("telefono"));
        maill.setText(getIntent().getStringExtra("mail"));
        padssl.setText(getIntent().getStringExtra("padss"));
    }
}