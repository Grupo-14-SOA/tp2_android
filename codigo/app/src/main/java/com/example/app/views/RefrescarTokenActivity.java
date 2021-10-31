package com.example.app.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.app.R;

public class RefrescarTokenActivity extends AppCompatActivity {

    Intent intentPrevio, intentRefrescar,intentSalir, intentService;
    Button botonSalir, botonRefrescar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refrescar_token);

        botonSalir = findViewById(R.id.buttonSalir);
        botonRefrescar = findViewById(R.id.buttonRefrescar);

        intentSalir = new Intent(this,VerificacionUserLoginActivity.class);

        botonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salirRefresh();
            }
        });

        botonRefrescar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new RequestTask().execute();
            }
        });
    }

    private void salirRefresh(){
        stopService(intentService);
        startActivity(intentSalir);
    }
}