package com.example.app.models;

import android.content.Intent;

import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

public class HTTPServiceSignup extends HTTPService{

    // Nombre del thread usado para debugging
    private static final String class_name = HTTPServiceSignup.class.getSimpleName();
    private static final String ENDPOINT = "/api/api/register";

    public HTTPServiceSignup() {
        super(class_name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(connectionManager.hayConexion()) {
            try {
                if (intent.hasExtra("jsonObject")) {
                    request = new JSONObject(intent.getStringExtra("jsonObject"));
                }
                String[] response = POST(request);
                token = response[0];
                refreshToken = response[1];
                if (exception != null) {
                    Intent i = new Intent("com.example.intentservice.intent.action.SIGNUP_RESPONSE");
                    i.putExtra("success", false);
                    i.putExtra("mensaje", "Error en envio de request");
                    //Se envian los valores al bradcast reciever del presenter de login
                    sendBroadcast(i);
                }
                else if (token.isEmpty()) {
                    Intent i = new Intent("com.example.intentservice.intent.action.SIGNUP_RESPONSE");
                    i.putExtra("success", false);
                    i.putExtra("mensaje", "Error en datos de request");
                    //Se envian los valores al bradcast reciever del presenter de login
                    sendBroadcast(i);
                }
                else {
                    Intent i = new Intent("com.example.intentservice.intent.action.SIGNUP_RESPONSE");
                    i.putExtra("success", true);
                    i.putExtra("mensaje", "Registración exitosa");
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
            Intent i = new Intent("com.example.intentservice.intent.action.SIGNUP_RESPONSE");
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
