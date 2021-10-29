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

public class VerificacionSMSActivity extends AppCompatActivity {

    private VerificacionSMS presenter;
    private Button buttonEnviarSMS;
    private EditText numCelular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificacion_sms);

        buttonEnviarSMS = findViewById(R.id.buttonEnviarSMS);
        numCelular = findViewById(R.id.editTextNumCelular);

        presenter = new VerificacionSMS(this);

        boolean permisoConcedido = presenter.solicitarPermisoSMS();

        buttonEnviarSMS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(numCelular.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "¡Ingrese un número de teléfono!", Toast.LENGTH_LONG).show();
                }
                else{
                    presenter.enviarSMS(numCelular.getText().toString(), permisoConcedido);
                    //lanzarVerificarCodigo();
                }
            }
        });
    }

    /*@Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_SEND_SMS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), "Permiso a enviar SMS concedido", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText((getApplicationContext()), "Permiso a enviar SMS denegado", Toast.LENGTH_LONG).show();
            }
        }
    }*/
}