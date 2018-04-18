package com.example.hp.myappmascotas;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.hp.myappmascotas.adapter.MascotaAdapter;
import com.example.hp.myappmascotas.pojo.Mascota;
import com.example.hp.myappmascotas.presenter.MascotasFragmentPresenter;
import com.example.hp.myappmascotas.presenter.IRankingActivityPresenter;
import com.example.hp.myappmascotas.presenter.RankingActivityPresenter;


import java.util.ArrayList;

public class RankingActivity extends AppCompatActivity{
    ArrayList<Mascota> mascota ;
    private RecyclerView recyclerView;

    private IRankingActivityPresenter iRankingActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        recyclerView=(RecyclerView)findViewById(R.id.rvRanking);
        //iRankingActivity=new RankingActivityPresenter(this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        inicializarListaMascotas();
        inicializarAdaptador();
    }

    public void inicializarAdaptador(){
        MascotaAdapter mascotaAdapter = new MascotaAdapter(mascota,this);
        recyclerView.setAdapter(mascotaAdapter);

    }
    public  void inicializarListaMascotas(){
        mascota = new ArrayList<Mascota>();
       /* mascota.add(new Mascota(1,"pelusa",R.drawable.chancho,64));
        mascota.add(new Mascota(2,"gatorade",R.drawable.gato,47));
        mascota.add(new Mascota(3,"josefino",R.drawable.perro1,88));
        mascota.add(new Mascota(4,"cleopatra",R.drawable.perro2,56));*/
    }
}
