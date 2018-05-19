package com.example.hp.myappmascotas.resApi.deserializador;

import android.util.Log;

import com.example.hp.myappmascotas.resApi.JsonKey;
import com.example.hp.myappmascotas.resApi.model.MascotaResponse;
import com.example.hp.myappmascotas.resApi.model.UsuarioResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class UserselfDesearializador implements JsonDeserializer<UsuarioResponse> {
    @Override
    public UsuarioResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        UsuarioResponse usuarioResponse = gson.fromJson(json,UsuarioResponse.class);
        JsonObject usuarioResponseDataJson = json.getAsJsonObject().getAsJsonObject(JsonKey.MEDIA_RESPONSE_ARRAY);

        String id = usuarioResponseDataJson.get(JsonKey.ID).getAsString();
        String nombre = usuarioResponseDataJson.get(JsonKey.FULL_NAME).getAsString();

        usuarioResponse.setIdusuario(id);
        usuarioResponse.setNombre(nombre);
        return usuarioResponse;
    }
}

