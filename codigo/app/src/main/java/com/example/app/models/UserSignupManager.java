package com.example.app.models;

public class UserSignupManager {

    private String mail;
    private String pass;

    public UserSignupManager() {}

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean registrarUsuario(){
        // aca se implementaria lo del Signup con la api de los profes
        // Hasta que se implemente lo de la api por defecto deveulvo True
        return true;
    }

}
