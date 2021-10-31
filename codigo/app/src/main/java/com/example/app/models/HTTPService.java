package com.example.app.models;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;

public abstract class HTTPService extends IntentService {

    private static final String URI = "http://so-unlam.net.ar";

    protected String url, token, refreshToken;
    protected ConnectionManager connectionManager;
    protected JSONObject request;
    protected JSONObject response;
    protected Exception exception;

    public HTTPService(String class_name) {
        // Nombre del thread usado para debugging
        super(class_name);
        this.url = this.getUrl();
        this.exception = null;
        this.token = "";
        this.refreshToken = "";
        this.connectionManager = new ConnectionManager(this);
    }

    protected void setConnectionHeadersPOST(HttpURLConnection connection) throws ProtocolException {
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
    }

    protected void POST(JSONObject request) {
        try {
            HttpURLConnection connection = connectionManager.abrirConexion(this.url);
            this.setConnectionHeadersPOST(connection);

            //Se crea un paquete JSON que indica el estado(encendido o apagado) del led que se desea
            //modificar. Este paquete JSON se escribe en el campo body del mensaje POST
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());

            // Loggeo el request enviado en el POST
            Log.i("POST REQUEST", request.toString());
            wr.writeBytes(request.toString());

            wr.flush();
            wr.close();

            //Se envia el request al Servidor
            connection.connect();

            //Se obtiene la respuesta que envio el Servidor ante el request
            parseResponse(connection);

            connection.disconnect();

        } catch (Exception e) {
            exception = e;
        }
    }

    private void parseResponse(HttpURLConnection connection) throws IOException, JSONException {
        if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer responseBuffer = new StringBuffer();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                responseBuffer.append(inputLine);
            }
            in.close();

            response = new JSONObject(responseBuffer.toString());
            Log.i("RESPONSE", response.toString());
            token = response.getString("token");
            refreshToken = response.getString("token_refresh");
        }
    }

    /**
    * Método a ser utilizado por las clases que lo extiendan para agregar
    * el endpoint y devolver la url a la que se van a conectar
    */
    protected String getUrl() {
        return URI;
    }
}
