package com.example.hp.myappmascotas;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class NotiificationIdTokenService extends FirebaseInstanceIdService {
    private static final String TAG = "NOTIFICATIONIDTOKENSERV";
    @Override
    public void onTokenRefresh() {
        //super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();
        enviarTokenRegistro(token);
    }

    private void enviarTokenRegistro(String token) {
        Log.d(TAG,token);
    }
}
