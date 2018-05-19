package com.example.hp.myappmascotas;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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

        imgFotoDetalle = (ImageView)findViewById(R.id.ivFotoDeatlle);

        Picasso.with(this)
                .load(url)
                .placeholder(R.drawable.ic_launcher_background)
                .into(imgFotoDetalle);
        tvLikes=(TextView)findViewById(R.id.tvLikesDetalle);
        tvLikes.setText(String.valueOf(like));

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            Slide slide = new Slide(Gravity.BOTTOM);
            slide.setDuration(1000);
            slide.addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {

                }

                @Override
                public void onTransitionEnd(Transition transition) {

                }

                @Override
                public void onTransitionCancel(Transition transition) {

                }

                @Override
                public void onTransitionPause(Transition transition) {

                }

                @Override
                public void onTransitionResume(Transition transition) {

                }
            });
            getWindow().setEnterTransition(slide);

            getWindow().setReturnTransition(new Fade());
        }
    }
}
