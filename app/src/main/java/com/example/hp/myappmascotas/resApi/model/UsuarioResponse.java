package com.example.hp.myappmascotas.resApi.model;

public class UsuarioResponse {
    private String idusuario;
    private String nombre;

    public UsuarioResponse(String idusuario, String nombre) {
        this.idusuario = idusuario;
        this.nombre = nombre;
    }

    public UsuarioResponse() {
    }

    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
