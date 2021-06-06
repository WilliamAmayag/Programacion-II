package com.ugb.myprimerproyecto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Chat extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference chat = database.getReference("chat");

    ListView lstChat;
    EditText txtMensaje;
    Button btnEnviar;
    ArrayList<Mensaje> mensajes;
    ChatAdapter adapter;
    String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Bundle recibirparametros = getIntent().getExtras();
        nombre = recibirparametros.getString("nombre");

        lstChat = findViewById(R.id.lstChat);
        txtMensaje = findViewById(R.id.txtMensaje);
        btnEnviar = findViewById(R.id.btnEnviar);
        mensajes = new ArrayList<>();
        adapter = new ChatAdapter(this, mensajes);
        lstChat.setAdapter(adapter);

        btnEnviar.setOnClickListener(v -> {
            String mensaje = txtMensaje.getText().toString();
            if (!mensaje.isEmpty()) {
                enviar(mensaje);
            }
        });

        chat.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Mensaje m = snapshot.getValue(Mensaje.class);
                mensajes.add(m);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void enviar(String mensaje) {
        chat.push().setValue(new Mensaje(nombre, mensaje));
        txtMensaje.setText("");
    }

}