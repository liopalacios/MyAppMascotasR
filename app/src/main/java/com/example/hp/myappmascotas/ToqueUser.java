package com.example.hp.myappmascotas;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.hp.myappmascotas.resApi.EndPointsApi;
import com.example.hp.myappmascotas.resApi.adapter.RestApiAdapter;
import com.example.hp.myappmascotas.resApi.model.UsuarioResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ToqueUser extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String ACCION_KEY = "TOQUE_USER";
        String action = intent.getAction();
        if(ACCION_KEY.equals(action)){
            toqueuser();
        }

    }
    public void toqueuser(){
       /* UsuarioResponse usuarioResponse = new UsuarioResponse(mascota.getId(),mascota.getNombre());
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndPointsApi endPoint = restApiAdapter.establecerConexionRestApi();
        Call<UsuarioResponse> usuarioResponseCall=endPoint.likeUsuario(usuarioResponse.getIdusuario(),mascota.getFoto(),usuarioResponse.getNombre());
        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                Toast.makeText(activity,"OK Diste like",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {

            }
        });*/
    }
}
