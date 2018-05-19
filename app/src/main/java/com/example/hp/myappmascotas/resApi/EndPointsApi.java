package com.example.hp.myappmascotas.resApi;

import com.example.hp.myappmascotas.resApi.model.MascotaResponse;
import com.example.hp.myappmascotas.resApi.model.UsuarioResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by HP on 3/04/2018.
 */

public interface EndPointsApi {
    @GET(ConstantesResApi.URL_GET_RECENT_USER)
    Call<MascotaResponse> getRecentMedia();

    @GET(ConstantesResApi.URL_GET_SELF_USER)
    Call<UsuarioResponse> getSelfUser();

    @FormUrlEncoded
    @POST(ConstantesResApi.KEY_POST_ID_TOKEN)
    Call<UsuarioResponse> regitrarTokenId(@Field("token") String token,@Field("usuario") String usuario);

    @FormUrlEncoded
    @POST(ConstantesResApi.KEY_POST_ID_TOKEN_USER)
    Call<UsuarioResponse> regitrarTokenIdUser(@Field("id") String id,@Field("usuario") String usuario,@Field("token") String token);

    @GET(ConstantesResApi.KEY_TOQUE_USUARIO)
    Call<UsuarioResponse>toqueAnimal(@Path("id") String id, @Path("usuario")String usuario);

    @GET(ConstantesResApi.KEY_LIKE_USUARIO)
    Call<UsuarioResponse>likeUsuario(@Path("keyfirebase") String keyfirebase, @Path("foto")String foto,@Path("usuario")String usuario);
}
