package com.example.hp.myappmascotas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleActivity extends AppCompatActivity {
    private static final String KEY_EXTRA_URL="url";
    private static final String KEY_EXTRA_LIKE="like";
    private ImageView imgFotoDetalle;
    private TextView tvLikes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        Bundle extras = getIntent().getExtras();
        String url = extras.getString(KEY_EXTRA_URL);
        int like = extras.getInt(KEY_EXTRA_LIKE);
        tvLikes=(TextView)findViewById(R.id.tvLikesDetalle);
        tvLikes.setText(String.valueOf(like));
    }
}
