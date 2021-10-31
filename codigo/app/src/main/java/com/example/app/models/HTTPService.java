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
    private Exception exception;

    public HTTPService(String class_name) {
        // Nombre del thread usado para debugging
        super(class_name);
        this.url = this.getUrl();
        this.exception = null;
        this.token = "";
        this.refreshToken = "";
    }

    public String getToken() {
        return token;
    }

    public String getRefreshToken() {
        return refreshToken;
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
                stopSelf();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Handler mainHandler = new Handler(getMainLooper());
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "No existe conexion a internet", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    protected void setConnectionHeadersPOST(HttpURLConnection connection) throws ProtocolException {
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
    }

    protected String[] POST(JSONObject request) {
        try {
            connectionManager = new ConnectionManager(this);
            HttpURLConnection connection = connectionManager.abrirConexion(this.url);
            this.setConnectionHeadersPOST(connection);

            //Se crea un paquete JSON que indica el estado(encendido o apagado) del led que se desea
            //modificar. Este paquete JSON se escribe en el campo body del mensaje POST
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());

            Log.i("POST REQUEST", request.toString());
            wr.writeBytes(request.toString());

            wr.flush();
            wr.close();

            //se envia el request al Servidor
            connection.connect();

            //Se obtiene la respuesta que envio el Servidor ante el request
            String[] parsedResponse = parseResponse(connection);

            connection.disconnect();

            return parsedResponse;

        } catch (Exception e) {
            exception = e;
        }
        return null;
    }

    private String[] parseResponse(HttpURLConnection connection) throws IOException, JSONException {
        String[] arrayReturn = new String[2];
        if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer response = new StringBuffer();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject jsonResponse = new JSONObject(response.toString());
            arrayReturn[0] = jsonResponse.getString("token");
            arrayReturn[1] = jsonResponse.getString("token_refresh");

            return arrayReturn;
        }
        arrayReturn[0] = "";
        arrayReturn[1] = "";
        return arrayReturn;
    }

    /**
    * Método a ser utilizado por las clases que lo extiendan para agregar
    * el endpoint y devolver la url a la que se van a conectar
    */
    protected String getUrl() {
        return URI;
    }
}
