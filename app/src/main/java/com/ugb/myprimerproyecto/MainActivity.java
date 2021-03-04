package com.ugb.myprimerproyecto;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TabHost;


public class MainActivity extends AppCompatActivity {
    RelativeLayout Contenido;
    TabHost tabhost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Contenido = findViewById(R.id.contenidoView);
        tabhost = findViewById(R.id.tbhgeneral);
        tabhost.setup();

        tabhost.addTab(tabhost.newTabSpec("valoragua").setContent(R.id.valoragua).setIndicator("agua"));
        tabhost.addTab(tabhost.newTabSpec("conversorarea").setContent(R.id.tabconversorarea).setIndicator("area"));

    }
}
