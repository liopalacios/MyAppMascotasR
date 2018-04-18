package com.example.hp.myappmascotas.resApi.adapter;

import android.util.Log;

import com.example.hp.myappmascotas.resApi.ConstantesResApi;
import com.example.hp.myappmascotas.resApi.EndPointsApi;
import com.example.hp.myappmascotas.resApi.deserializador.MascotaDeserializador;
import com.example.hp.myappmascotas.resApi.model.MascotaResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HP on 3/04/2018.
 */

public class RestApiAdapter {
    public EndPointsApi establecerConexionApiInstagram(Gson gson){
        Log.e("RESAPIADAPTER"," establecerConexionApiInstagram");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesResApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(EndPointsApi.class);
    }
    public Gson construyeGsonDeserializadorMediaPrecent(){
        Log.e("RESAPIADAPTER"," construyendoGsonDeserializador");
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class,new MascotaDeserializador());
        return gsonBuilder.create();
    }
    public EndPointsApi establecerConexionRestApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesResApi.ROOT_URL_HEROKU)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Log.d("RESTAPIADAPTER"," -> Establecer Conexion RestApi");
        return retrofit.create(EndPointsApi.class);
    };
}
