package com.example.app.presenters;

import android.Manifest;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.app.models.SMSManager;
import com.example.app.views.VerificacionSMSActivity;

public class VerificacionSMS {

    private static final int REQUEST_SEND_SMS = 1;

    private SMSManager smsManager;
    private VerificacionSMSActivity view;

    public VerificacionSMS(VerificacionSMSActivity view) {
        this.smsManager = new SMSManager();
        this.view = view;
    }

    public boolean solicitarPermisoSMS() {
        if (ContextCompat.checkSelfPermission(this.view, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this.view, new String[]{Manifest.permission.SEND_SMS}, REQUEST_SEND_SMS);
        }
        return ContextCompat.checkSelfPermission(this.view, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED;
    }

    public void enviarSMS(String numCelular, boolean permisoConcedido) {
        smsManager.enviarSMS(numCelular, permisoConcedido);
    }

    public void setCodIngresado(String codigoIngresado){
        this.smsManager.setCodIngresado(codigoIngresado);
    }

    public boolean verificarCodIngresado(){return this.smsManager.verificarCodIngresado()}
}
