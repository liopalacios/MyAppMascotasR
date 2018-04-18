package com.example.hp.myappmascotas.fragment;

import com.example.hp.myappmascotas.adapter.MascotaAdapter;
import com.example.hp.myappmascotas.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by HP on 25/03/2018.
 */

public interface IMascotasFragmentView {
    public void generarLinearLayoutFragmentVertical();
    public void generarGridLayout();
    public MascotaAdapter crearAdapter(ArrayList<Mascota>mascotas);
    public void inicializarAdapterMascota(MascotaAdapter mascotaAdapter);
}
