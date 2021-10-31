package com.example.app.presenters;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.example.app.models.HTTPServiceLogin;
import com.example.app.models.User;
import com.example.app.views.VerificacionUserLoginActivity;

import org.json.JSONObject;

public class VerificacionLoginUsuario extends BroadcastReceiver {

    private User user;
    private VerificacionUserLoginActivity view;
    private Intent intentServiceLogin;


    public VerificacionLoginUsuario(VerificacionUserLoginActivity view) {
        this.user = new User();
        this.view = view;
        this.intentServiceLogin = new Intent(view, HTTPServiceLogin.class);
    }

    public String getEmail() {
        return this.user.getEmail();
    }

    public void setEmail(String email) {
        this.user.setEmail(email);
    }

    public String getPass() {
        return this.user.getPass();
    }

    public void setPass(String pass) {
        this.user.setPass(pass);
    }

    public void logIn(){
        JSONObject req = this.user.getJSONForLogIn();
        this.intentServiceLogin.putExtra("jsonObject", req.toString());
        this.view.startService(this.intentServiceLogin);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String mensaje= intent.getStringExtra("mensaje");
        this.view.mostrarToastMake(mensaje);
    }
}
