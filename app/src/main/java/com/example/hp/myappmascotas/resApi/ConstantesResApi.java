package com.example.hp.myappmascotas.resApi;

/**
 * Created by HP on 3/04/2018.
 */

public final class ConstantesResApi {
    public static final String VERSION = "/v1/";
    public static final String ROOT_URL = "https://api.instagram.com"+VERSION;

    public static final String KEY_GET_SELF_USER = "users/self/";
    public static final String KEY_GET_RECENT_USER = "users/self/media/recent/";
    public static final String ACCES_TOKEN = "?access_token=";
    public static final String KEY_ACCES_TOKEN = "7419704567.2417e23.2ced82fcb8994a92ac4f1cae2c4c0d08";


    public static final String URL_GET_RECENT_USER = KEY_GET_RECENT_USER+ACCES_TOKEN+KEY_ACCES_TOKEN;
    public static final String URL_GET_SELF_USER = KEY_GET_SELF_USER+ACCES_TOKEN+KEY_ACCES_TOKEN;

//    https://powerful-mesa-31425.herokuapp.com
    public static final String ROOT_URL_HEROKU = "https://powerful-mesa-31425.herokuapp.com/";
    public static final String KEY_POST_ID_TOKEN = "token-device/";
    public static final String KEY_POST_ID_TOKEN_USER = "registrar-usuario/";
    public static final String KEY_TOQUE_USUARIO = "toque_usuario/{id}/{usuario}/";
    public static final String KEY_LIKE_USUARIO = "enviar-like/{keyfirebase}/{foto}/{usuario}";
    //https://api.instagram.com/v1/users/self/media/recent/?access_token=ACCESS-TOKEN
    //https://api.instagram.com/v1/


}
