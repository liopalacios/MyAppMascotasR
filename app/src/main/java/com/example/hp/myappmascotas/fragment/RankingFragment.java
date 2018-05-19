package com.example.hp.myappmascotas.fragment;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hp.myappmascotas.R;
import com.example.hp.myappmascotas.adapter.GaleryAdapter;
import com.example.hp.myappmascotas.adapter.MascotaAdapter;
import com.example.hp.myappmascotas.pojo.Galeria;
import com.example.hp.myappmascotas.pojo.Mascota;
import com.example.hp.myappmascotas.resApi.EndPointsApi;
import com.example.hp.myappmascotas.resApi.adapter.RestApiAdapter;
import com.example.hp.myappmascotas.resApi.model.UsuarioResponse;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RankingFragment extends Fragment
{
    private static final String EMISOR = "Mascota perro";
    ArrayList<Mascota> mascota;
    ArrayList<Galeria> galerias;
    GaleryAdapter mascotaAdapter;
    private RecyclerView recyclerView;
    private ImageView imageView;
    public RankingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ranking,container,false);
        recyclerView=(RecyclerView)v.findViewById(R.id.rvGalery);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        /*LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);*/
        imageView = (ImageView)v.findViewById(R.id.ivFotoPerfil);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"MNSAG TOAS",Toast.LENGTH_SHORT).show();
                String token = FirebaseInstanceId.getInstance().getToken();
                Log.d(" TOKEN MAIN-ACTIVITY ",token);
                enviarTokenRegistro(token,EMISOR);
            }
        });
        inicializarListaMascotas();
        inicializarAdaptador();

        return v;
    }
    private void enviarTokenRegistro(String toke,String user) {
        UsuarioResponse usuarioResponse = new UsuarioResponse("leyter","-LBSnSqKnUSGP_3ZUcxk");
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndPointsApi endPoint = restApiAdapter.establecerConexionRestApi();
        Call<UsuarioResponse>usuarioResponseCall=endPoint.toqueAnimal(usuarioResponse.getIdusuario(),usuarioResponse.getNombre());

        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse1 = response.body();
            }
            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {

            }
        });

        /*RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndPointsApi endPoint = restApiAdapter.establecerConexionRestApi();
        Call<UsuarioResponse> usuarioResponseCall = endPoint.regitrarTokenId(toke,user);
        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse = response.body();
                Log.d("FIREBASE ONRESPONSE ",usuarioResponse.getUsuario());
                Log.d("FIREBASE TOKEN RESP ",usuarioResponse.getToken());
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {

            }
        });*/
    }
    public  void inicializarListaMascotas(){
        mascota = new ArrayList<Mascota>();
        galerias = new ArrayList<Galeria>();
       // mascota.add(new Mascota(1,"pelusa",R.drawable.chancho,18));
        galerias.add(new Galeria(1,R.drawable.chancho));
        galerias.add(new Galeria(1,R.drawable.ch1));
        galerias.add(new Galeria(1,R.drawable.ch2));
        galerias.add(new Galeria(1,R.drawable.ch3));
        galerias.add(new Galeria(1,R.drawable.ch4));
        galerias.add(new Galeria(1,R.drawable.ch5));
        galerias.add(new Galeria(1,R.drawable.ch6));
        galerias.add(new Galeria(1,R.drawable.ch7));
        galerias.add(new Galeria(1,R.drawable.ch8));
        galerias.add(new Galeria(1,R.drawable.ch9));
        galerias.add(new Galeria(1,R.drawable.ch10));
        galerias.add(new Galeria(1,R.drawable.ch11));
        galerias.add(new Galeria(1,R.drawable.ch12));
       /* mascota.add(new Mascota(2,"gatorade",R.drawable.gato,13));
        mascota.add(new Mascota(3,"josefino",R.drawable.perro1,14));
        mascota.add(new Mascota(4,"cleopatra",R.drawable.perro2,15));
        mascota.add(new Mascota(5,"pelota",R.drawable.perro3,22));
        mascota.add(new Mascota(6,"princesa",R.drawable.perro4,11));
        mascota.add(new Mascota(7,"pablo",R.drawable.perro5,9));
        mascota.add(new Mascota(8,"lassy",R.drawable.perro6,8));*/
    }
    public void inicializarAdaptador(){
        mascotaAdapter = new GaleryAdapter(galerias);
        recyclerView.setAdapter(mascotaAdapter);

    }

}
