package com.example.hp.myappmascotas.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hp.myappmascotas.R;
import com.example.hp.myappmascotas.adapter.MascotaAdapter;
import com.example.hp.myappmascotas.pojo.Mascota;
import com.example.hp.myappmascotas.presenter.IMascotaFragmentPresenter;
import com.example.hp.myappmascotas.presenter.MascotasFragmentPresenter;

import java.util.ArrayList;

/**
 * Created by HP on 18/03/2018.
 */

public class MascotasFragment extends Fragment implements IMascotasFragmentView{
    private RecyclerView recyclerView;
    MascotaAdapter mascotaAdapter;
    ArrayList<Mascota> mascota;
    private IMascotaFragmentPresenter iMascotaFragmentPresenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_mascotas,container,false);
        recyclerView=(RecyclerView)v.findViewById(R.id.rvMascotas);
        iMascotaFragmentPresenter = new MascotasFragmentPresenter(this,getContext());
        return v;
    }
/*
    public  void inicializarListaMascotas(){
        mascota = new ArrayList<Mascota>();
        mascota.add(new Mascota(1,"pelusa",R.drawable.chancho,18));
        mascota.add(new Mascota(2,"gatorade",R.drawable.gato,13));
        mascota.add(new Mascota(3,"josefino",R.drawable.perro1,14));
        mascota.add(new Mascota(4,"cleopatra",R.drawable.perro2,15));
        mascota.add(new Mascota(5,"pelota",R.drawable.perro3,22));
        mascota.add(new Mascota(6,"princesa",R.drawable.perro4,11));
        mascota.add(new Mascota(7,"pablo",R.drawable.perro5,9));
        mascota.add(new Mascota(8,"lassy",R.drawable.perro6,8));
    }
*/
    @Override
    public void generarLinearLayoutFragmentVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
    }

    @Override
    public void generarGridLayout() {
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    @Override
    public MascotaAdapter crearAdapter(ArrayList<Mascota> mascotas) {
        mascotaAdapter = new MascotaAdapter(mascotas,getActivity());
        return mascotaAdapter;
    }

    @Override
    public void inicializarAdapterMascota(MascotaAdapter mascotaAdapter) {
        recyclerView.setAdapter(mascotaAdapter);
    }
}
