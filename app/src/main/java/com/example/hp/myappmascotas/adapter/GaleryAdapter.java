package com.example.hp.myappmascotas.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.myappmascotas.R;
import com.example.hp.myappmascotas.pojo.Galeria;
import com.example.hp.myappmascotas.resApi.EndPointsApi;
import com.example.hp.myappmascotas.resApi.adapter.RestApiAdapter;
import com.example.hp.myappmascotas.resApi.model.UsuarioResponse;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by HP on 21/03/2018.
 */

public class GaleryAdapter extends RecyclerView.Adapter<GaleryAdapter.GaleryViewHolder> {
    private static final String EMISOR = "Mascota Lechon";
    ArrayList<Galeria> galerias;

    public GaleryAdapter(ArrayList<Galeria> galerias) {
        this.galerias = galerias;
    }

    @Override
    public GaleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_galery,parent,false);
        return new GaleryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GaleryViewHolder holder, int position) {

        Galeria galeria = galerias.get(position);
        holder.txtnombre.setText(String.valueOf(galeria.getId()));
        holder.imgFoto.setImageResource(galeria.getFoto());

    }

    @Override
    public int getItemCount() {
        return galerias.size();
    }

    public class GaleryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imgFoto;
        private TextView txtnombre;
        public GaleryViewHolder(View itemView) {
            super(itemView);
            imgFoto=(ImageView)itemView.findViewById(R.id.ivFotoGalery);
            txtnombre=(TextView)itemView.findViewById(R.id.tvNombreMascota);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(),"MNSAG TOAS LECHON",Toast.LENGTH_SHORT).show();
            String token = FirebaseInstanceId.getInstance().getToken();
            Log.d(" TOKEN MAIN-ACTIVITY ",token);
            enviarTokenRegistro(token,EMISOR);
        }
    }
    private void enviarTokenRegistro(String toke,String user) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndPointsApi endPoint = restApiAdapter.establecerConexionRestApi();
        Call<UsuarioResponse> usuarioResponseCall = endPoint.regitrarTokenId(toke,user);
        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse = response.body();
                Log.d("FIREBASE ONRESPONSE ",usuarioResponse.getIdusuario());
                Log.d("FIREBASE TOKEN RESP ",usuarioResponse.getNombre());
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {

            }
        });
    }
}
