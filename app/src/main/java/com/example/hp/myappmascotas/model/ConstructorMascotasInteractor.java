package com.example.hp.myappmascotas.model;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.example.hp.myappmascotas.R;
import com.example.hp.myappmascotas.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by HP on 25/03/2018.
 */

public class ConstructorMascotasInteractor {
    private static final int LIKE_CANT = 1;
    private Context context;
    private  BaseDatos bd;
    public ConstructorMascotasInteractor(Context context) {
        this.context=context;
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
        insertarMascota(bd);
        return bd.obtenerMascotas();
    }
    public void insertarMascota(BaseDatos bd){
        ContentValues contentValues=new ContentValues();
        contentValues.put(ConstanteBD.TABLE_MASCOTA_NOMBRE,"pepelucho");
        contentValues.put(ConstanteBD.TABLE_MASCOTA_FOTO,R.drawable.ch1);
        bd.insertarMascota(contentValues);
        contentValues=new ContentValues();
        contentValues.put(ConstanteBD.TABLE_MASCOTA_NOMBRE,"lechon");
        contentValues.put(ConstanteBD.TABLE_MASCOTA_FOTO,R.drawable.ch2);
        bd.insertarMascota(contentValues);
        contentValues=new ContentValues();
        contentValues.put(ConstanteBD.TABLE_MASCOTA_NOMBRE,"porkies");
        contentValues.put(ConstanteBD.TABLE_MASCOTA_FOTO,R.drawable.ch3);
        bd.insertarMascota(contentValues);
        contentValues=new ContentValues();
        contentValues.put(ConstanteBD.TABLE_MASCOTA_NOMBRE,"perezoso");
        contentValues.put(ConstanteBD.TABLE_MASCOTA_FOTO,R.drawable.ch4);
        bd.insertarMascota(contentValues);
        contentValues=new ContentValues();
        contentValues.put(ConstanteBD.TABLE_MASCOTA_NOMBRE,"gordo");
        contentValues.put(ConstanteBD.TABLE_MASCOTA_FOTO,R.drawable.ch5);
        bd.insertarMascota(contentValues);
        contentValues=new ContentValues();
        contentValues.put(ConstanteBD.TABLE_MASCOTA_NOMBRE,"manchas");
        contentValues.put(ConstanteBD.TABLE_MASCOTA_FOTO,R.drawable.ch6);
        bd.insertarMascota(contentValues);
    }
    public void darLike(Mascota mascota){
        BaseDatos  bd = new BaseDatos(context);
        ContentValues contentValues=new ContentValues();
        Log.e(" CONSTRUCTOR "," MASCOTAID "+mascota.getId());
        contentValues.put(ConstanteBD.TABLE_LIKE_MASCOTA_ID,mascota.getId());
        contentValues.put(ConstanteBD.TABLE_LIKE_CANT,LIKE_CANT);
        bd.insertarLike(contentValues);

    }
    public int obtenerLikes(Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        return db.obtenerLike(mascota);
    }
}
