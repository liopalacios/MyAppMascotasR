package com.example.hp.myappmascotas.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.myappmascotas.DetalleActivity;
import com.example.hp.myappmascotas.model.ConstructorMascotasInteractor;
import com.example.hp.myappmascotas.pojo.Mascota;
import com.example.hp.myappmascotas.R;
import com.example.hp.myappmascotas.resApi.EndPointsApi;
import com.example.hp.myappmascotas.resApi.adapter.RestApiAdapter;
import com.example.hp.myappmascotas.resApi.model.UsuarioResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by HP on 17/03/2018.
 */

public class MascotaAdapter extends RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder> {
    Activity activity;
    ArrayList<Mascota> mascotas;
    ArrayList<Integer> mascotasRanking;



    public MascotaAdapter(ArrayList<Mascota> mascotas,Activity activity){
        this.mascotasRanking= new ArrayList<Integer>();
        this.mascotas=mascotas;
        this.activity=activity;
    }
    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_gridmascotas,parent,false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MascotaViewHolder holder, final int position) {
        final Mascota mascota = mascotas.get(position);
        Picasso.with(activity)
                .load(mascota.getFoto())
                .placeholder(R.drawable.huesob)
                .into(holder.imgFoto);
        holder.tvLikes.setText(String.valueOf(mascota.getLike()));
        holder.imgLikeObt.setImageResource(R.drawable.like);
        Log.e("ID USU INST"," U I "+mascota.getId());

        holder.imgLikeObt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UsuarioResponse usuarioResponse = new UsuarioResponse(mascota.getId(),mascota.getNombre());
                RestApiAdapter restApiAdapter = new RestApiAdapter();
                EndPointsApi endPoint = restApiAdapter.establecerConexionRestApi();
                Call<UsuarioResponse>usuarioResponseCall=endPoint.likeUsuario(usuarioResponse.getIdusuario(),mascota.getFoto(),usuarioResponse.getNombre());
                usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
                    @Override
                    public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                        Toast.makeText(activity,"OK Diste like",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<UsuarioResponse> call, Throwable t) {

                    }
                });
            }
        });

        holder.imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UsuarioResponse usuarioResponse = new UsuarioResponse("leyter","-LAQYckVC6kvIDXZxXUC");
                RestApiAdapter restApiAdapter = new RestApiAdapter();
                EndPointsApi endPoint = restApiAdapter.establecerConexionRestApi();
                Call<UsuarioResponse>usuarioResponseCall=endPoint.toqueAnimal(usuarioResponse.getIdusuario(),usuarioResponse.getNombre());
                usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
                    @Override
                    public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                        /*UsuarioResponse usuarioResponse1 = response.body();
                        Log.d("FIREBASE ENQUEUE 1 ",usuarioResponse1.getUsuario());
                        Log.d("FIREBASE ENQUEUE 2 ",usuarioResponse1.getToken());*/
                    }

                    @Override
                    public void onFailure(Call<UsuarioResponse> call, Throwable t) {

                    }
                });

                Intent intent = new Intent(activity, DetalleActivity.class);
                intent.putExtra("url",mascota.getFoto());
                intent.putExtra("like",mascota.getLike());

                if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
                    Explode explode = new Explode();
                    explode.setDuration(1000);
                    activity.getWindow().setExitTransition(explode);
                    activity.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(activity,view,activity.getString(R.string.animacion_foto)).toBundle());
                }else{
                    activity.startActivity(intent);
                }

            }
        });
        /*
        holder.imgLike.setImageResource(R.drawable.huesob);
        holder.imgLikeObt.setImageResource(R.drawable.hueso);
        holder.tvNombre.setText(mascota.getNombre());


        holder.imgLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConstructorMascotasInteractor constructorMascotasInteractor=new ConstructorMascotasInteractor(activity);
                Log.e("MENSAJE",mascota.getId()+" MASCOTAS "+mascota.getNombre());
                constructorMascotasInteractor.darLike(mascota);
                holder.tvLikes.setText(String.valueOf(constructorMascotasInteractor.obtenerLikes(mascota)));

                //holder.tvLike.setText(String.valueOf(Integer.parseInt(holder.tvLike.getText().toString())+1));
                // mascotasRanking.add(new Mascota(holder.tvNombre.getText(),Integer.parseInt(holder.tvLike.toString())));
              //  mascotasRanking.add(position);
              //  Log.e(" lista " , " LISTADO "+mascotasRanking);
            }
        }); */

    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgFoto;
        /*private ImageView imgLike;
        private ImageView imgLikeObt;
        private TextView tvNombre;*/
        private TextView tvLikes;
        private ImageView imgLikeObt;
        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgFoto = (ImageView)itemView.findViewById(R.id.ivFotoGrid);
            tvLikes = (TextView)itemView.findViewById(R.id.tvLikesGrid);
            imgLikeObt = (ImageView)itemView.findViewById(R.id.imgLikesBone);
            /*
            imgLike = (ImageView)itemView.findViewById(R.id.ivLike);
            imgLikeObt = (ImageView)itemView.findViewById(R.id.ivLikeObt);
            tvNombre= (TextView)itemView.findViewById(R.id.tvNombre);*/
        }
    }


}
