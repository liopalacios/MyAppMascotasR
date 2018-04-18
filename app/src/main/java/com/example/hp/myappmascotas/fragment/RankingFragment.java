package com.example.hp.myappmascotas.fragment;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hp.myappmascotas.R;
import com.example.hp.myappmascotas.adapter.GaleryAdapter;
import com.example.hp.myappmascotas.adapter.MascotaAdapter;
import com.example.hp.myappmascotas.pojo.Galeria;
import com.example.hp.myappmascotas.pojo.Mascota;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RankingFragment extends Fragment
{
    ArrayList<Mascota> mascota;
    ArrayList<Galeria> galerias;
    GaleryAdapter mascotaAdapter;
    private RecyclerView recyclerView;
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
        inicializarListaMascotas();
        inicializarAdaptador();
        return v;
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
