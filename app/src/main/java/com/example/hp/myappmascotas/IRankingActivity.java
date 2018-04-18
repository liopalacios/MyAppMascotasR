package com.example.hp.myappmascotas;

import com.example.hp.myappmascotas.adapter.MascotaAdapter;
import com.example.hp.myappmascotas.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by HP on 29/03/2018.
 */

public interface IRankingActivity {
    public void generarLinearLayoutFragmentVertical();
    public MascotaAdapter crearAdapter(ArrayList<Mascota> mascotas);
    public void inicializarAdapterMascota(MascotaAdapter mascotaAdapter);
}
