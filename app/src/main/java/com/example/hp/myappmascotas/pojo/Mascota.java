package com.example.hp.myappmascotas.pojo;

/**
 * Created by HP on 17/03/2018.
 */

public class Mascota {
    private String id;
    private String nombre;
    private String foto;
    private int like=0;

    public Mascota(String nombre, String foto, int like) {

        this.nombre = nombre;
        this.foto = foto;
        this.like = like;
    }

    public Mascota() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }
}
