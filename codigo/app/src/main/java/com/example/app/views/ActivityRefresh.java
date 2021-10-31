package com.example.app.views;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.R;
import com.example.app.models.ConnectionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ActivityRefresh extends AppCompatActivity {

    Intent intentPrevio, intentRefrescar,intentSalir, intentService;
    Button botonSalir, botonRefrescar;
    ConnectionManager connectionManager;
    String refresh_token, access_token;
    String[] parsedResponse;
    private static final int STATUS_SUCCESS = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_refresh);

        connectionManager = new ConnectionManager(this);
        parsedResponse = new String[2];

        botonSalir = findViewById(R.id.buttonSalir);
        botonRefrescar = findViewById(R.id.buttonRefrescar);

        /*intentRefrescar = new Intent(this, ActivityFuncional.class);
        intentSalir = new Intent(this,SegundaActivityLogin.class);
        intentService = new Intent(this,ServiceCheckToken.class);
*/
        intentPrevio = getIntent();
        refresh_token = "Bearer " + intentPrevio.getStringExtra("refresh_token");

        botonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salirRefresh();
            }
        });

        botonRefrescar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RequestTask().execute();
            }
        });
    }

    private class RequestTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            enviarPeticion();
            return null;
        }
    }

    private void enviarPeticion() {
        String ENDPOINT = "/api/api/refresh";
        String URI = "http://so-unlam.net.ar";
        String url = URI + ENDPOINT;
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", refresh_token);

            if(connectionManager.hayConexion()){
                con.getOutputStream();

                parsedResponse = parsearResponse(con);
                access_token = parsedResponse[0];
                refresh_token = parsedResponse[1];

                if(!access_token.equals("")) {
                    intentRefrescar.putExtra("access_token",access_token);
                    intentRefrescar.putExtra("refresh_token",refresh_token);
                    startActivity(intentRefrescar);
                } else {
                    Handler mainHandler = new Handler(getMainLooper());
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Ocurrio un error con el refresh", Toast.LENGTH_LONG).show();
                        }
                    });
                    salirRefresh();
                }
            } else {
                Handler mainHandler = new Handler(getMainLooper());
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "No existe conexion a internet", Toast.LENGTH_LONG).show();
                        salirRefresh();
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void salirRefresh(){
        stopService(intentService);
        startActivity(intentSalir);
    }

    private String[] parsearResponse(HttpURLConnection con) {
        String[] arrayReturn = new String[2];
        try {
            if(con.getResponseCode() == STATUS_SUCCESS){
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
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
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        arrayReturn[0] = "";
        arrayReturn[1] = "";
        return arrayReturn;
    }
}
