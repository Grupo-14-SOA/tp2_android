package com.example.app.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.app.R;
import com.example.app.presenters.RefrescarToken;
import com.example.app.presenters.ServiceCheckTokenExpiration;

public class RefrescarTokenActivity extends AppCompatActivity {

    private Intent intentPrevio, intentSalir, intentServiceCheckTokenExpiration, intentActivityPrincipal;
    private Button botonSalir, botonRefrescar;
    private RefrescarToken presenter;
    private String refresh_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refrescar_token);

        botonSalir = findViewById(R.id.buttonSalir);
        botonRefrescar = findViewById(R.id.buttonRefrescar);
        intentSalir = new Intent(this, VerificacionUserLoginActivity.class);
        intentServiceCheckTokenExpiration = new Intent(this, ServiceCheckTokenExpiration.class);
        intentActivityPrincipal = new Intent(this, ActivityPrincipal.class);

        intentPrevio = getIntent();
        refresh_token = intentPrevio.getStringExtra("refresh_token");

        presenter = new RefrescarToken(this, refresh_token);

        botonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salirRefresh();
            }
        });

        botonRefrescar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.ejecutarTask();
            }
        });
    }

    public void iniciarActivityPrincipal(Intent intent) {
        intentActivityPrincipal.putExtra("token", intent.getStringExtra("token"));
        intentActivityPrincipal.putExtra("refresh_token", intent.getStringExtra("refresh_token"));
        startActivity(intentActivityPrincipal);
    }

    public void salirRefresh() {
        stopService(intentServiceCheckTokenExpiration);
        startActivity(intentSalir);
    }

    public void mostrarToastMake(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}