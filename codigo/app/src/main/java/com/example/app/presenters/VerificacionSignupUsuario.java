package com.example.app.presenters;

import android.Manifest;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.app.models.SMSManager;
import com.example.app.views.VerificacionSMSActivity;

public class VerificacionSignupUsuario {

    private UserSignupManager userSignupManager;
    private VerificacionUserSignupActivity view;

    public VerificacionSignupUsuario(VerificacionSMSActivity view) {
        this.userSignupManager = new UserSignupManager();
        this.view = view;
    }

    public String getMail() {
        return this.userLoginManager.getMail();
    }

    public void setMail(String mail) {
        this.userSignupManager.setMail(mail);
    }

    public String getPass() {
        return this.userSignupManager.getPass();
    }

    public void setPass(String pass) {
        this.userSignupManager.setPass(pass);
    }

    public boolean signup(){
        return this.userSignupManager.registrarUsuario();
    }

}
