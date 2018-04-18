package com.example.hp.myappmascotas.resApi;

import com.example.hp.myappmascotas.resApi.model.MascotaResponse;
import com.example.hp.myappmascotas.resApi.model.UsuarioResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by HP on 3/04/2018.
 */

public interface EndPointsApi {
    @GET(ConstantesResApi.URL_GET_RECENT_USER)
    Call<MascotaResponse> getRecentMedia();

    @FormUrlEncoded
    @POST(ConstantesResApi.KEY_POST_ID_TOKEN)
    Call<UsuarioResponse> regitrarTokenId(@Field("token") String token,@Field("usuario") String usuario);
}
