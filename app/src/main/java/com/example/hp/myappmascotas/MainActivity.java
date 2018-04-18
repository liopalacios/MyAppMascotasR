package com.example.hp.myappmascotas;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.hp.myappmascotas.adapter.MascotaAdapter;
import com.example.hp.myappmascotas.adapter.PageAdapter;
import com.example.hp.myappmascotas.fragment.MascotasFragment;
import com.example.hp.myappmascotas.fragment.RankingFragment;
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

public class MainActivity extends AppCompatActivity {
    private static final String EMISOR = "LEYTER INSTA";
    ArrayList<Mascota> mascota;
    ArrayList<Galeria> galerias;
    private RecyclerView recyclerView;
    MascotaAdapter mascotaAdapter;

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Toolbar mitoolbar = (Toolbar)findViewById(R.id.miActionBarMain);
        mitoolbar.setTitle("mi page mascotas");
        mitoolbar.setSubtitle("mi ranking");
        setSupportActionBar(mitoolbar);*/

        toolbar=(Toolbar)findViewById(R.id.tbMascota);
        toolbar.setTitle("mi page mascotas");
        toolbar.setSubtitle("mi ranking");


        tabLayout=(TabLayout) findViewById(R.id.tabLayout);
        viewPager=(ViewPager)findViewById(R.id.vpMascotaMain);
        setUpViewPager();
/*

        recyclerView=(RecyclerView)findViewById(R.id.rvMascotas);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        inicializarListaMascotas();
        inicializarAdaptador();
*/
        if(toolbar != null){
            setSupportActionBar(toolbar);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.icStar:
                Intent intent = new Intent(MainActivity.this,RankingActivity.class);
                startActivity(intent);
                return true;
            case R.id.itContacto:
                Intent intentCon = new Intent(MainActivity.this,ContactoActivity.class);
                startActivity(intentCon);
                return true;
            case R.id.itRecibirNoti:
                String token = FirebaseInstanceId.getInstance().getToken();
                Log.d(" TOKEN MAIN-ACTIVITY ",token);
                enviarTokenRegistro(token,EMISOR);
                return true;
            case R.id.itAcercaDe:
                Intent intentAce = new Intent(MainActivity.this,AcercaActivity.class);
                startActivity(intentAce);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void enviarTokenRegistro(String toke,String user) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndPointsApi endPoint = restApiAdapter.establecerConexionRestApi();
        Call<UsuarioResponse> usuarioResponseCall = endPoint.regitrarTokenId(toke,user);
        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse = response.body();
                Log.d("FIREBASE ONRESPONSE",usuarioResponse.getUsuario());
                Log.d("FIREBASE TOKEN RESPONSE",usuarioResponse.getToken());
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {

            }
        });
    }

    /*public  void inicializarListaMascotas(){
        mascota = new ArrayList<Mascota>();
        galerias = new ArrayList<Galeria>();
        mascota.add(new Mascota(1,"pelusa",R.drawable.chancho,18));
            galerias.add(new Galeria(1,R.drawable.chancho));
            galerias.add(new Galeria(1,R.drawable.ch1));
            galerias.add(new Galeria(1,R.drawable.ch2));
            galerias.add(new Galeria(1,R.drawable.ch3));
            galerias.add(new Galeria(1,R.drawable.ch4));
            galerias.add(new Galeria(1,R.drawable.ch5));
        mascota.add(new Mascota(2,"gatorade",R.drawable.gato,13));
        mascota.add(new Mascota(3,"josefino",R.drawable.perro1,14));
        mascota.add(new Mascota(4,"cleopatra",R.drawable.perro2,15));
        mascota.add(new Mascota(5,"pelota",R.drawable.perro3,22));
        mascota.add(new Mascota(6,"princesa",R.drawable.perro4,11));
        mascota.add(new Mascota(7,"pablo",R.drawable.perro5,9));
        mascota.add(new Mascota(8,"lassy",R.drawable.perro6,8));
    }*/
    private ArrayList<Fragment> agregarFragments (){

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new MascotasFragment());
        fragments.add(new RankingFragment());
        return fragments;
    }
    private void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);
    }
}
