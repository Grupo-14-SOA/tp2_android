package com.example.app.models;

import android.content.Intent;

import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

public class HTTPServiceLogin extends HTTPService{

    // Nombre del thread usado para debugging
    private static final String class_name = HTTPServiceLogin.class.getSimpleName();
    private static final String ENDPOINT = "/api/api/login";

    public HTTPServiceLogin() {
        super(class_name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(connectionManager.hayConexion()) {
            try {
                if (intent.hasExtra("jsonObject")) {
                    request = new JSONObject(intent.getStringExtra("jsonObject"));
                }
                POST(request);
                if (exception != null) {
                    Intent i = new Intent("com.example.intentservice.intent.action.LOGIN_RESPONSE");
                    i.putExtra("success", false);
                    i.putExtra("mensaje", "Error en envio de request");
                    //Se envian los valores al bradcast reciever del presenter de login
                    sendBroadcast(i);
                }
                else if (token.isEmpty()) {
                    Intent i = new Intent("com.example.intentservice.intent.action.LOGIN_RESPONSE");
                    i.putExtra("success", false);
                    i.putExtra("mensaje", "Usuario o contraseña incorrectos");
                    //Se envian los valores al bradcast reciever del presenter de login
                    sendBroadcast(i);
                }
                else {
                    Intent i = new Intent("com.example.intentservice.intent.action.LOGIN_RESPONSE");
                    i.putExtra("success", true);
                    i.putExtra("mensaje", "Login exitoso");
                    i.putExtra("token", token);
                    i.putExtra("refresh_token", refreshToken);
                    //Se envian los valores al bradcast reciever del presenter de login
                    sendBroadcast(i);
                }
                stopSelf();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Intent i = new Intent("com.example.intentservice.intent.action.LOGIN_RESPONSE");
            i.putExtra("success", false);
            i.putExtra("mensaje", "No hay  conexión a Internet");
            //Se envian los valores al bradcast reciever del presenter de login
            sendBroadcast(i);
        }
    }

    protected String getUrl() {
        return super.getUrl() + ENDPOINT;
    }

}
