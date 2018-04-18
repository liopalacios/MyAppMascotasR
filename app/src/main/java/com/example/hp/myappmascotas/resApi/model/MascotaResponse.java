package com.example.hp.myappmascotas.resApi.model;

import com.example.hp.myappmascotas.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by HP on 3/04/2018.
 */

public class MascotaResponse {
    ArrayList<Mascota> mascotaArray;

    public ArrayList<Mascota> getMascotaArray() {
        return mascotaArray;
    }

    public void setMascotaArray(ArrayList<Mascota> mascotaArray) {
        this.mascotaArray = mascotaArray;
    }
}
