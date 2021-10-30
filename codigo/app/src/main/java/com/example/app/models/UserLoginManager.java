package com.example.app.models;

public class UserLoginManager {

    private String mail;
    private String pass;

    public UserLoginManager() {}

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

    public boolean validarUsuario(){
        // aca se implementaria lo del login con la api de los profes
        // Hasta que se implemente lo de la api por defecto deveulvo True
        return true;
    }

}
