package com.example.app.models;

import android.content.Context;
import android.content.Intent;

import org.json.JSONException;
import org.json.JSONObject;

public class User {

    private String email;
    private String pass;

    public User() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public JSONObject getJSONForLogIn(){
        // aca se implementaria lo del login con la api de los profes
        // Hasta que se implemente lo de la api por defecto deveulvo True
        JSONObject req = new JSONObject();
        try {
            req.put("email", this.email);
            req.put("password", this.pass);
            return req;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean registrarUsuario(){
        // aca se implementaria lo del Signup con la api de los profes
        // Hasta que se implemente lo de la api por defecto deveulvo True
        return true;
    }

}
