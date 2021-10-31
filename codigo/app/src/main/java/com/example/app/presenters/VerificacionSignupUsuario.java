package com.example.app.presenters;

import com.example.app.models.User;
import com.example.app.views.VerificacionUserSignupActivity;

public class VerificacionSignupUsuario {

    private User user;
    private VerificacionUserSignupActivity view;

    public VerificacionSignupUsuario(VerificacionUserSignupActivity view) {
        this.user = new User();
        this.view = view;
    }

    public String getEmail() {
        return this.user.getEmail();
    }

    public void setEmail(String mail) {
        this.user.setEmail(mail);
    }

    public String getPass() {
        return this.user.getPass();
    }

    public void setPass(String pass) {
        this.user.setPass(pass);
    }

    public boolean signUp(){
        return this.user.registrarUsuario();
    }

}
