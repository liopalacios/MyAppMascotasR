package com.example.hp.myappmascotas.resApi.deserializador;

import android.util.Log;

import com.example.hp.myappmascotas.pojo.Mascota;
import com.example.hp.myappmascotas.resApi.JsonKey;
import com.example.hp.myappmascotas.resApi.model.MascotaResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by HP on 3/04/2018.
 */

public class MascotaDeserializador implements JsonDeserializer<MascotaResponse>{
    @Override
    public MascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        MascotaResponse mascotaResponse = gson.fromJson(json,MascotaResponse.class);
        JsonArray mascotaResponseData = json.getAsJsonObject().getAsJsonArray(JsonKey.MEDIA_RESPONSE_ARRAY);
        mascotaResponse.setMascotaArray(deserializadorMascota(mascotaResponseData));
        return mascotaResponse;
    }
    private ArrayList<Mascota>deserializadorMascota(JsonArray mascotaArrayData){
        ArrayList<Mascota> mascotas=new ArrayList<>();
        for (int i=0;i<mascotaArrayData.size();i++){
            JsonObject mascotaResponseDataObject = mascotaArrayData.get(i).getAsJsonObject();
            JsonObject userJson = mascotaResponseDataObject.getAsJsonObject(JsonKey.USER);
            String id = userJson.get(JsonKey.ID).getAsString();
            String nombre = userJson.get(JsonKey.FULL_NAME).getAsString();

            JsonObject imageJson = mascotaResponseDataObject.getAsJsonObject(JsonKey.MEDIA_IMAGES);
            JsonObject stdResolution = imageJson.getAsJsonObject(JsonKey.RESOLUTION);
            String urlFoto = stdResolution.get(JsonKey.MEDIA_URL).getAsString();

            JsonObject likesJson = mascotaResponseDataObject.getAsJsonObject(JsonKey.MEDIA_LIKES);
            int likes = likesJson.get(JsonKey.MEDIA_LIKES_COUNT).getAsInt();

            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(id);
            mascotaActual.setNombre(nombre);
            mascotaActual.setFoto(urlFoto);
            mascotaActual.setLike(likes);

            mascotas.add(mascotaActual);
        }
        return mascotas;
    }
}
