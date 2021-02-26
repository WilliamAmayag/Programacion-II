package com.ugb.myprimerproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    SensorManager administradorsensor;
    Sensor sensor;
    SensorEventListener sensorEventListener;
    TextView tempVal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tempVal = (findViewById(R.id.lblSensor));
        activarsensor();
    }

    private void activarsensor() {
administradorsensor = (SensorManager)getSystemService(SENSOR_SERVICE);
sensor = administradorsensor.getDefaultSensor(Sensor.TYPE_PROXIMITY);

if (sensor == null){
    Toast.makeText(getApplicationContext(), "No dipones de un sensor de proximindad", Toast.LENGTH_LONG).show();
    finish();
}

sensorEventListener = new SensorEventListener() {
    @Override
    public void onSensorChanged(SensorEvent event) {
 tempVal.setText("Valor: "+event.values[0] );
 if ( event.values[0]<4) {
     getWindow().getDecorView().setBackgroundColor(Color.BLACK);
    } else if (event.values[0]>=4 && event.values[0]<8){
     getWindow().getDecorView().setBackgroundColor(Color.RED);
 } else {
     getWindow().getDecorView().setBackgroundColor(Color.BLUE);
 }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
};


    }

    @Override
    protected void onResume() {
        iniciar();
        super.onResume();
    }

    @Override
    protected void onPause() {
        detener();
        super.onPause();
    }

    private void iniciar(){
        administradorsensor.registerListener(sensorEventListener,sensor,2000*1000);
    }
    private void detener(){
        administradorsensor.unregisterListener(sensorEventListener);
    }



}