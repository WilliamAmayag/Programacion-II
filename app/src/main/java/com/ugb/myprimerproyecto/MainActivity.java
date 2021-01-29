package com.ugb.myprimerproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calcular(View view){
double num1 = 0;
double num2 = 0;
double respuesta = 0;

        TextView temnun = (TextView)findViewById(R.id.txtnum1);
        String valor1 = temnun.getText().toString();
        if (valor1.matches("")) {
            num1 = 0;
        }   else {
            num1 = Double.parseDouble(temnun.getText().toString());
        }

        temnun = (TextView)findViewById(R.id.txtnum2);
        String valor2 = temnun.getText().toString();
        if (valor2.matches("")){
             num2 = 0;
        } else {
             num2 = Double.parseDouble(temnun.getText().toString());
        }




        Spinner operaciones = findViewById(R.id.coboperaciones);
        switch (operaciones.getSelectedItemPosition()){
           case 0: //suma
                respuesta = num1 + num2;
                    break;
           case 1: //resta
                respuesta = num1 - num2;
                    break;
           case 2: //multiplicacion
                respuesta = num1 * num2;
                    break;
           case 3: //divicion
                respuesta = num1 / num2;
               break;
            case 4: //factorial

                respuesta = 1;
                for(int i=1; i<=num1; i++){
                    respuesta = i * respuesta;}
                break;
            case 5: //porcentaje
                respuesta = (num2/100)*num1;
                break;
            case 6: //exponenciacion
                respuesta = Math.pow(num1,num2);
                break;
            case 7: //raiz
                respuesta = Math.pow(num1, 1/num2); //la raiz x de un numero x es igual a ese numero elevado a 1/numeroderaiz
                break;
            case 8://modulo
                respuesta = num1 % num2; // & es el resto en java
                break;
            case 9://mayor de
                if (num1 >= num2){
                    respuesta = num1;
                } else {respuesta= num2;}
                break;
        }
        temnun = findViewById(R.id.resultado);
        temnun.setText("Respuesta " + respuesta);
    }
}