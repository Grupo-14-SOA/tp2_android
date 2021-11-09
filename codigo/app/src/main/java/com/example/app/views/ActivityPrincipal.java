package com.example.app.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.example.app.R;
import com.example.app.presenters.Principal;

public class ActivityPrincipal extends AppCompatActivity {

    private Intent intentPrevio;
    private Principal presenter;
    public IntentFilter filtro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        intentPrevio = getIntent();
        String refreshToken = intentPrevio.getStringExtra("refresh_token");

        presenter = new Principal(this, refreshToken);
        configurarBroadcastReciever();
    }

    private void configurarBroadcastReciever() {
        //Metodo que registra un broadcast receiver para comunicar el servicio que recibe los
        //mensajes del servidor con el presenter de esta activity
        //Se registra la  accion LOGOUT_APP, para que cuando la activity de refrescar token
        //la ejecute se invoque automaticamente el OnRecive del presenter
        filtro = new IntentFilter("com.example.intentservice.intent.action.STOP_CHECK_TOKEN");
        filtro.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(presenter, filtro);
    }
}