package com.example.hp.myappmascotas.model;

import android.content.Context;

import com.example.hp.myappmascotas.RankingActivity;
import com.example.hp.myappmascotas.pojo.Mascota;
import com.example.hp.myappmascotas.presenter.RankingActivityPresenter;

import java.util.ArrayList;

/**
 * Created by HP on 29/03/2018.
 */

public class ConstructorActivityInteractor {
    private static final int LIKE_CANT = 1;
    private Context context;
    private  BaseDatos bd;
    private RankingActivityPresenter rankingActivityPresenter;
    public ConstructorActivityInteractor(RankingActivityPresenter rankingActivityPresenter) {
        this.rankingActivityPresenter = rankingActivityPresenter;
    }
    public ArrayList<Mascota> obtenerMascotas(){
        /*
        ArrayList<Mascota> mascotas= new ArrayList<>();
        mascotas.add(new Mascota(1,"pelusa",R.drawable.chancho,18));
        mascotas.add(new Mascota(2,"gatorade", R.drawable.gato,13));
        mascotas.add(new Mascota(3,"josefino",R.drawable.perro1,14));
        mascotas.add(new Mascota(4,"cleopatra",R.drawable.perro2,15));
        mascotas.add(new Mascota(5,"pelota",R.drawable.perro3,22));
        mascotas.add(new Mascota(6,"princesa",R.drawable.perro4,11));
        mascotas.add(new Mascota(7,"pablo",R.drawable.perro5,9));
        mascotas.add(new Mascota(8,"lassy",R.drawable.perro6,8));
        return mascotas;
        */
        bd = new BaseDatos(this.context);

        return bd.obtenerMascotas();
    }
}
