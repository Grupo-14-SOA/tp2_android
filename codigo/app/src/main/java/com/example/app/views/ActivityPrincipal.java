package com.example.app.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.app.R;
import com.example.app.presenters.Principal;

public class ActivityPrincipal extends AppCompatActivity {

    private Intent intentPrevio;
    private Principal presenter;
    private IntentFilter filtro;
    private Button buttonScanQR, buttonVerMenus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        intentPrevio = getIntent();
        String refreshToken = intentPrevio.getStringExtra("refresh_token");

        presenter = new Principal(this, refreshToken);
        configurarBroadcastReciever();

        buttonScanQR = findViewById(R.id.buttonScanQR);
        buttonVerMenus = findViewById(R.id.buttonVerMenus);

        buttonScanQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityPrincipal.this, EscanearQRActivity.class));
            }
        });
        buttonVerMenus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityPrincipal.this, MenusActivity.class));
            }
        });
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