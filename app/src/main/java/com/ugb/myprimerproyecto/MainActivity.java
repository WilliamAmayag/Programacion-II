package com.ugb.myprimerproyecto;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    RelativeLayout Contenido;
    TabHost tabhost;
    Button button;
    TextView temp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Contenido = findViewById(R.id.contenidoView);
        tabhost = findViewById(R.id.tbhgeneral);
        tabhost.setup();

        tabhost.addTab(tabhost.newTabSpec("valoragua").setContent(R.id.valoragua).setIndicator("agua"));
        tabhost.addTab(tabhost.newTabSpec("conversorarea").setContent(R.id.tabconversorarea).setIndicator("area"));

        button = (Button) findViewById(R.id.btnCalcular);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularagua();
            }

            //Funcion del boton
            private void calcularagua() {
              try {
                  temp = (TextView) findViewById(R.id.txtCantidadL);
                  double cantidad = Double.parseDouble(temp.getText().toString());
                  temp = (TextView) findViewById(R.id.lblRespuestaL);

                  if (cantidad >=0 && cantidad <=18 ){


                      temp.setText("El total a pagar es $6");

                  } else if (cantidad >= 19 && cantidad <=28){
                      double calculo = 0;
                      double i = 0;
                      double resultado = 0;
                      calculo = cantidad - 18;

                      i = calculo * 0.45;

                      resultado = 6 + i;

                      temp.setText("El total a pagar es $" + resultado);
                  } else {

                      double calculo = 0;

                      double c = cantidad;
                      double resultado1 = 0;
                      double resultado2 = 0;
                      double resultado = 0;
                      calculo = c - 28;

                      resultado1 = calculo * 0.65;

                      resultado2 = 10 * 0.45;





                      resultado = 6 + resultado1 + resultado2;

                      temp.setText("El total a pagar es $" + resultado);

                  }


              }catch (Exception e){
temp = findViewById(R.id.lblRespuestaL);
temp.setText("Por favor ingrese los valores correctos");
                  Toast.makeText(getApplicationContext(), "Por favor ingrese los valores correctos", Toast.LENGTH_LONG).show();
              }

            }
        });



    }

}
