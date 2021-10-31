package com.example.app.models;

import android.content.Context;
import android.content.Intent;

import org.json.JSONException;
import org.json.JSONObject;

public class User {

    private static final String ENV = "TEST";

    private String email;
    private String pass;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    private String nombre;

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    private String apellido;

    public void setDni(String dni) {
        this.dni = dni;
    }

    private String dni;

    public void setComision(String comision) {
        this.comision = comision;
    }

    private String comision;

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    private String grupo;

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

    public JSONObject getJSONForSignup(){
        // aca se implementaria lo del Signup con la api de los profes
        // Hasta que se implemente lo de la api por defecto deveulvo True
        JSONObject req = new JSONObject();
        try {
            req.put("env", ENV);
            req.put("name", this.nombre);
            req.put("lastname", this.apellido);
            req.put("dni", Integer.parseInt(this.dni));
            req.put("email", this.email);
            req.put("password", this.pass);
            req.put("commission", Integer.parseInt(this.comision));
            req.put("group", Integer.parseInt(this.grupo));
            return req;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}
