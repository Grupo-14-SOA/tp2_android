package com.example.app.presenters;

import android.content.Intent;

import com.example.app.models.ConnectionManager;
import com.example.app.models.RequestTask;
import com.example.app.views.RefrescarTokenActivity;

public class RefrescarToken {

    private RefrescarTokenActivity view;
    private RequestTask model;

    public RefrescarToken(RefrescarTokenActivity view, String refresh_token) {
        this.view = view;
        this.model = new RequestTask(refresh_token, this);
    }

    public ConnectionManager getConnectionManager() {
        return new ConnectionManager(this.view);
    }

    public void ejecutarTask() {
        this.model.execute();
    }

    public void actualizarActivity(Intent intent) {
        boolean success = intent.getBooleanExtra("success", false);
        String mensaje = intent.getStringExtra("mensaje");
        this.view.mostrarToastMake(mensaje);
        if (success) {
            //Ejecuto método de llamado de siguiente activity
            this.view.iniciarActivityPrincipal(intent);
        } else {
            this.view.salirRefresh();
        }
    }

}
