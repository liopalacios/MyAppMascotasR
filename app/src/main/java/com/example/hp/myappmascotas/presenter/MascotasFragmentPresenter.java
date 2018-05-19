package com.example.hp.myappmascotas.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.hp.myappmascotas.IRankingActivity;
import com.example.hp.myappmascotas.fragment.IMascotasFragmentView;
import com.example.hp.myappmascotas.model.ConstructorMascotasInteractor;
import com.example.hp.myappmascotas.pojo.Mascota;
import com.example.hp.myappmascotas.resApi.EndPointsApi;
import com.example.hp.myappmascotas.resApi.adapter.RestApiAdapter;
import com.example.hp.myappmascotas.resApi.model.MascotaResponse;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by HP on 25/03/2018.
 */

public class MascotasFragmentPresenter implements IMascotaFragmentPresenter {
    private IMascotasFragmentView iMascotasFragmentView;
    private Context context;
    private ConstructorMascotasInteractor constructorMascotasInteractor;
    private ArrayList<Mascota>mascotas;

    public MascotasFragmentPresenter(IMascotasFragmentView iMascotasFragmentView, Context context) {
        this.iMascotasFragmentView=iMascotasFragmentView;
        this.context=context;
       // obtenerMascotasBaseDatos();
        obtenerMediosRecientes();
        enviarKeyIdInstagram();
    }

    @Override
    public void obtenerMediosRecientes() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaPrecent();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionApiInstagram(gsonMediaRecent);
        Call<MascotaResponse> mascotaResponseCall = endPointsApi.getRecentMedia();
        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                Log.e("RESPONSES","JSON "+mascotaResponse.getMascotaArray());
                mascotas = mascotaResponse.getMascotaArray();
                mostrarMascotasFragment();
            }
            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context,"MENSAJE DE PRUEBA DE ERRORES",Toast.LENGTH_LONG);
                Log.e("MUESTRA ERROR",t.toString());
            }
        });
    }

    @Override
    public void enviarKeyIdInstagram() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorSelf();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionApiInstagram(gsonMediaRecent);
        Call<MascotaResponse> mascotaResponseCall = endPointsApi.getRecentMedia();


    }

    @Override
    public void obtenerMascotasBaseDatos() {
        constructorMascotasInteractor=new ConstructorMascotasInteractor(context);
        mascotas = constructorMascotasInteractor.obtenerMascotas();
        mostrarMascotasFragment();
    }
    @Override
    public void mostrarMascotasFragment() {
        iMascotasFragmentView.inicializarAdapterMascota(iMascotasFragmentView.crearAdapter(mascotas));
        iMascotasFragmentView.generarGridLayout();
    }
}
