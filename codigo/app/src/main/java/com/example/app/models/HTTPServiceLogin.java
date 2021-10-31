package com.example.app.models;

public class HTTPServiceLogin extends HTTPService{

    // Nombre del thread usado para debugging
    private static final String class_name = HTTPServiceLogin.class.getSimpleName();
    private static final String ENDPOINT = "/api/api/login";

    public HTTPServiceLogin() {
        super(class_name);
    }

    protected String getUrl() {
        return super.getUrl() + ENDPOINT;
    }

}
