package com.example.hp.myappmascotas.presenter;

import android.content.Context;

import com.example.hp.myappmascotas.IRankingActivity;
import com.example.hp.myappmascotas.RankingActivity;
import com.example.hp.myappmascotas.fragment.IMascotasFragmentView;
import com.example.hp.myappmascotas.model.ConstructorMascotasInteractor;
import com.example.hp.myappmascotas.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by HP on 29/03/2018.
 */

public class RankingActivityPresenter implements IRankingActivityPresenter {
    private IRankingActivity iRankingActivity;
    private Context context;
    private RankingActivity rankingActivity;
    private ConstructorMascotasInteractor constructorMascotasInteractor;
    private ArrayList<Mascota> mascotas;

    public RankingActivityPresenter( RankingActivity rankingActivity) {

        this.rankingActivity=rankingActivity;
        obtenerMascotasBaseDatos();
    }

    @Override
    public void obtenerMascotasBaseDatos() {
        constructorMascotasInteractor=new ConstructorMascotasInteractor(context);
        mascotas = constructorMascotasInteractor.obtenerMascotas();
        mostrarMascotasActivity();
    }

    @Override
    public void mostrarMascotasActivity() {
        iRankingActivity.inicializarAdapterMascota(iRankingActivity.crearAdapter(mascotas));
        iRankingActivity.generarLinearLayoutFragmentVertical();

    }
}
