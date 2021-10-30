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

public class VerificacionUserSignupActivity extends AppCompatActivity {

    private VerificacionSignupUsuario presenter;
    private Button buttonSignup;
    private EditText editTextMail;
    private EditText editTextPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificacion_user_signup);

        buttonSignup = findViewById(R.id.buttonSignup);

        editTextMail = findViewById(R.id.editTextMail);  
        editTextPass = findViewById(R.id.editTextPass);


        buttonSignup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                this.presenter.setMail(editTextMail.getText().toString());
                this.presenter.setPass(editTextPass.getText().toString());
                // Trato de registrarme, en caso de poder registrarme correctamente vuelvo a la actividad del login
                if(this.presenter.sigup()){
                    // Este mensaje tengo duda si tendria que estar aca o si directamente habria que tirar el finish
                    Toast.makeText(getApplicationContext(), "Usuario registrado correctamente", Toast.LENGTH_LONG).show();
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Hubo unerror al quere registrar el usuario, intente nuevamente", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


}