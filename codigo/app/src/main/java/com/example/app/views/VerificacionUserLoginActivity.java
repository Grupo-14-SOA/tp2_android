package com.example.app.views;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app.R;
import com.example.app.presenters.VerificacionLoginUsuario;

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

        presenter = new VerificacionLoginUsuario(this);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.setEmail(editTextMail.getText().toString());
                presenter.setPass(editTextPass.getText().toString());
                // Verifico el codigo que ingreso el usuario
                if(!presenter.logIn()){
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
                lanzarVerificarUserSignup();
            }
        });
    }

    public void lanzarVerificarUserSignup(){
        Intent intent = new Intent(this, VerificacionUserSignupActivity.class);
        this.startActivity(intent);
    }
}