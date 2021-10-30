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

public class VerificacionUserLoginActivity extends AppCompatActivity {

    private VerificacionLoginUsuario presenter;
    private Button buttonLogin;
    private Button buttonSignup;
    private EditText editTextMail;
    private EditText editTextPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificacion_user_login);

        buttonLogin = findViewById(R.id.buttonLogin);
        buttonSignup = findViewById(R.id.buttonSignup);

        editTextMail = findViewById(R.id.editTextMail);  
        editTextPass = findViewById(R.id.editTextPass);


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                this.presenter.setMail(editTextMail.getText().toString());
                this.presenter.setPass(editTextPass.getText().toString());
                // Verifico el codigo que ingreso el usuario
                if(!this.presenter.login()){
                    Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show();
                }
                else{
                    //llamo a la vista del programa en si 
                }
            }
        });


        buttonSignup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Llamo a la vista para registrarme
                this.lanzarVerificarUserSignup();
            }
        });
    }

    public void lanzarVerificarUserSignup(){
        Intent intent = new Intent(this, VerificacionUserSignupActivity.class);
        this.startActivity(intent);
    }
}