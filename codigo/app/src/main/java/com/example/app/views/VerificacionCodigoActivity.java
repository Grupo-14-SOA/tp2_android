package com.example.app.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app.R;
import com.example.app.presenters.VerificacionSMS;

public class VerificacionCodigoActivity extends AppCompatActivity {

    private VerificacionSMS presenter;
    private Button buttonValidar;
    private EditText codigoIngresado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificacion_sms);

        buttonValidar = findViewById(R.id.buttonValidar);
        codigoIngresado = findViewById(R.id.editTextCodigoIngresado);

        buttonValidar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                this.presenter.setCodIngresado(codigoIngresado.getText().toString());
                // Verifico el codigo que ingreso el usuario
                if(!this.presenter.verificarCodIngresado()){
                    Toast.makeText(getApplicationContext(), "Codigo incorrecto, vuelva a generar generar el codigo", Toast.LENGTH_LONG).show();
                    //Cierro la actividad actual
                    finish();
                }
            }
        });
    }

    public void setPresenter(VerificacionSMS presenter){
        this.presenter = presenter;
    }




}