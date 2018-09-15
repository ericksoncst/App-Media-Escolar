package net.br.mobiledesign.mediaescolarmvc.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import net.br.mobiledesign.mediaescolarmvc.R;
import net.br.mobiledesign.mediaescolarmvc.controller.AplicativoController;
import net.br.mobiledesign.mediaescolarmvc.controller.MediaEscolarController;
import net.br.mobiledesign.mediaescolarmvc.model.MediaEscolar;

import java.util.List;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (AplicativoController.verificarGooglePlayServices(SplashActivity.this)) {
            apresentarTelaSplash();
        }else {
            Toast.makeText(this, "Google PLay Services não está configurado", Toast.LENGTH_LONG).show();
        }
    }

    private void apresentarTelaSplash() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                MediaEscolarController mediaEscolarController = new MediaEscolarController(getApplicationContext());


                List<MediaEscolar> objetos = mediaEscolarController.listar();

                for (MediaEscolar obj : objetos) {

                    Log.i("CRUD LISTAR", "ID: " + obj.getId() + " - Matéria: " + obj.getMateria() + " - Situação: " + obj.getSituacao());

                }


                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
