package com.example.app.presenters;

import android.Manifest;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.app.models.SMSManager;
import com.example.app.views.VerificacionSMSActivity;

public class VerificacionLoginUsuario {

    private static final int REQUEST_SEND_SMS = 1;

    private UserLoginManager userLoginManager;
    private VerificacionUserLoginActivity view;

    public VerificacionLoginUsuario(VerificacionSMSActivity view) {
        this.userLoginManager = new UserLoginManager();
        this.view = view;
    }

    public String getMail() {
        return this.userLoginManager.getMail();
    }

    public void setMail(String mail) {
        this.userLoginManager.setMail(mail);
    }

    public String getPass() {
        return this.userLoginManager.getPass();
    }

    public void setPass(String pass) {
        this.userLoginManager.setPass(pass);
    }

    public boolean login(){
        return this.userLoginManager.validarUsuario();
    }

}
