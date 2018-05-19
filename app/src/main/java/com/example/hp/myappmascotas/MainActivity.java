package com.example.hp.myappmascotas;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.hp.myappmascotas.adapter.MascotaAdapter;
import com.example.hp.myappmascotas.adapter.PageAdapter;
import com.example.hp.myappmascotas.fragment.MascotasFragment;
import com.example.hp.myappmascotas.fragment.RankingFragment;
import com.example.hp.myappmascotas.pojo.Galeria;
import com.example.hp.myappmascotas.pojo.Mascota;
import com.example.hp.myappmascotas.resApi.EndPointsApi;
import com.example.hp.myappmascotas.resApi.adapter.RestApiAdapter;
import com.example.hp.myappmascotas.resApi.model.MascotaResponse;
import com.example.hp.myappmascotas.resApi.model.UsuarioResponse;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String EMISOR = "LEYTER MSM";
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
       // getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        toolbar=(Toolbar)findViewById(R.id.tbMascota);
        toolbar.setTitle(R.string.app_name_toolbar);
        toolbar.setSubtitle("mi ranking");

        tabLayout=(TabLayout) findViewById(R.id.tabLayout);
        viewPager=(ViewPager)findViewById(R.id.vpMascotaMain);
        setUpViewPager();
        obtenerSelf();
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
    public void obtenerSelf(){
        Log.e("RESPONSES","JSONMAINMAIN-1 ");
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Log.e("RESPONSES","JSONMAINMAIN-2 ");
        com.google.gson.Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorSelf();
        Log.e("RESPONSES","JSONMAINMAIN-3 ");
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionApiInstagram(gsonMediaRecent);
        Log.e("RESPONSES","JSONMAINMAIN-4 ");
        Call<UsuarioResponse> usuarioResponseCall = endPointsApi.getSelfUser();
        Log.e("RESPONSES","JSONMAINMAIN-5 ");
        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse = response.body();
                Log.e("RESPONSES","JSONMAINMAIN "+usuarioResponse.getIdusuario());
                String tokenUser = FirebaseInstanceId.getInstance().getToken();
                enviarTokenRegistroUser(usuarioResponse.getIdusuario(),usuarioResponse.getNombre(),tokenUser);

            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                Log.e("RESPONSES","JSONMAINMAIN-6 ERROR "+t.toString());
            }
        });

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
                Slide slide = new Slide(Gravity.BOTTOM);
                slide.setDuration(1000);
                getWindow().setExitTransition(slide);
                startActivity(intentCon, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
                return true;
            case R.id.itRecibirNoti:
                Intent i = new Intent(this, MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_ONE_SHOT);
                Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

                NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ch10)
                        .setContentTitle("Notify Mascotas")
                        .setContentText("ESTE ES UN MENSAJE NOTIFY")
                        .setSound(sonido)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);

                NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(1,notificacion.build());

                return true;
            case R.id.itEnviarNoti:
                String token = FirebaseInstanceId.getInstance().getToken();
                Log.d(" TOKEN MAIN-ACTIVITY ",token);
                enviarTokenRegistro(token,EMISOR);
                return true;
            case R.id.itRecibirNotificaciones:
                String tokenFire = FirebaseInstanceId.getInstance().getToken();
                Log.d(" TOKEN MAIN-ACTIVITY ",tokenFire);
                enviarTokenRegistroUsuInstagram(tokenFire,EMISOR);
                return true;
            case R.id.itMiUbicacion:
                Intent intentMaps = new Intent(MainActivity.this,MiUbicacionActivity.class);
                Fade slide2 = new Fade();
                slide2.setDuration(500);
                getWindow().setExitTransition(slide2);
                startActivity(intentMaps,ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
                return true;
            case R.id.itAcercaDe:
                Intent intentAce = new Intent(MainActivity.this,AcercaActivity.class);
                Slide slide1 = new Slide(Gravity.LEFT);
                slide1.setDuration(1000);
                getWindow().setExitTransition(slide1);
                startActivity(intentAce,ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void enviarTokenRegistroUser(String id, String nombre, String tokenUser) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndPointsApi endPoint = restApiAdapter.establecerConexionRestApi();
        Call<UsuarioResponse> usuarioResponseCall = endPoint.regitrarTokenIdUser(id,nombre,tokenUser);
        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                Log.e("FIREBASE OK","ENVIO CORRECTO");
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                Log.e("ERROR ",t.toString());
            }
        });
    }
    private void enviarTokenRegistro(String toke,String user) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndPointsApi endPoint = restApiAdapter.establecerConexionRestApi();
        Call<UsuarioResponse> usuarioResponseCall = endPoint.regitrarTokenId(toke,user);
        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse = response.body();

            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {

            }
        });
    }
    private void enviarTokenRegistroUsuInstagram(String toke,String user) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndPointsApi endPoint = restApiAdapter.establecerConexionRestApi();
        Call<UsuarioResponse> usuarioResponseCall = endPoint.regitrarTokenId(toke,user);
        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse = response.body();

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
    public void toqueMascota(View view){
        Log.d("MAINACTIVITY","TOQUE MASCOTA");
        UsuarioResponse usuarioResponse = new UsuarioResponse("leyter","-LAQYckVC6kvIDXZxXUC");
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
    }

}
