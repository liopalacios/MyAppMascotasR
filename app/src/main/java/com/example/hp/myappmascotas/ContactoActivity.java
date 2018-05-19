package com.example.hp.myappmascotas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ContactoActivity extends AppCompatActivity {
    private Button btnEnviarMail;
    private EditText etNombre;
    private EditText etEmail;
    private EditText etMensaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
        btnEnviarMail=(Button)findViewById(R.id.btnContacto);
        etNombre=(EditText)findViewById(R.id.etNombre);
        etEmail=(EditText)findViewById(R.id.etEmail);
        etMensaje=(EditText)findViewById(R.id.etMensaje);
        Slide fade = new Slide(Gravity.TOP);
        fade.setDuration(400);
        getWindow().setEnterTransition(fade);

        btnEnviarMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = etNombre.getText().toString();
                String email = etEmail.getText().toString();
                String mensaje = etMensaje.getText().toString();

                SendMail sm = new SendMail(ContactoActivity.this,email,nombre,mensaje);
                sm.execute();
            }
        });
    }
}
