package com.example.app.views;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.IntentFilter;
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
    public IntentFilter filtro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificacion_user_login);

        buttonLogin = findViewById(R.id.buttonLogin);
        buttonSignup = findViewById(R.id.buttonSignup);

        editTextMail = findViewById(R.id.editTextMail);  
        editTextPass = findViewById(R.id.editTextPass);

        presenter = new VerificacionLoginUsuario(this);
        configurarBroadcastReciever();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.setEmail(editTextMail.getText().toString());
                presenter.setPass(editTextPass.getText().toString());
                presenter.logIn();
            }
        });

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Llamo a la vista para registrarme
                lanzarVerificarUserSignup();
            }
        });
    }

    //Metodo que crea y configurar un broadcast receiver para comunicar el servicio que recibe los mensaje del servidor
    //con la activity principal
    private void configurarBroadcastReciever()
    {
        //se asocia(registra) la  accion RESPUESTA_OPERACION, para que cuando el Servicio de recepcion la ejecute
        //se invoque automaticamente el OnRecive del objeto receiver
        filtro = new IntentFilter("com.example.intentservice.intent.action.LOGIN_RESPONSE");
        filtro.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(presenter, filtro);
    }


    public void lanzarVerificarUserSignup(){
        Intent intent = new Intent(this, VerificacionUserSignupActivity.class);
        this.startActivity(intent);
    }

    public void lanzarActivity(){
        // Método a ser llamado desde el presenter para ejecutar la siguiente actividad
        //Intent intent = new Intent(this, .class);
        //this.startActivity(intent);
    }

    public void mostrarToastMake(String msg)
    {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

}