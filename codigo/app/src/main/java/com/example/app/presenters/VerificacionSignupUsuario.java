package com.example.app.presenters;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.app.models.User;
import com.example.app.views.VerificacionUserSignupActivity;

public class VerificacionSignupUsuario extends BroadcastReceiver {

    private User user;
    private VerificacionUserSignupActivity view;

    public VerificacionSignupUsuario(VerificacionUserSignupActivity view) {
        this.user = new User();
        this.view = view;
    }

    public String getEmail() {
        return this.user.getEmail();
    }

    public void setEmail(String mail) {
        this.user.setEmail(mail);
    }

    public String getPass() {
        return this.user.getPass();
    }

    public void setPass(String pass) {
        this.user.setPass(pass);
    }

    public boolean signUp(){
        return this.user.registrarUsuario();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String mensaje= intent.getStringExtra("mensaje");
        this.view.mostrarToastMake(mensaje);
    }
}
