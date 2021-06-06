package com.ugb.myprimerproyecto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;


public class ChatAdapter extends ArrayAdapter<Mensaje> {
    public ChatAdapter(Context context, ArrayList<Mensaje> mensajes) {
        super(context, 0, mensajes);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Mensaje mensaje = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.chat, parent, false);
        }

        TextView usuario = (TextView) convertView.findViewById(R.id.lblUsuario);
        usuario.setText(mensaje.usuario);

        TextView lblMensaje = (TextView) convertView.findViewById(R.id.lblMensajes);
        lblMensaje.setText(mensaje.mensaje);

        return convertView;
    }
}
