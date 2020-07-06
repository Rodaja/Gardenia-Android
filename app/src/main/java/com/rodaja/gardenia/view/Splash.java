package com.rodaja.gardenia.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import com.rodaja.gardenia.R;
import com.rodaja.gardenia.model.configuration.Configuration;
import com.rodaja.gardenia.model.configuration.Permissions;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        cargarPreferencias();

        getSupportActionBar().hide();
        goLogin();

    }

    private void goLogin() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, Login.class);
                startActivity(intent);
                finish();
            }
        }, 1000);
    }

    private void cargarPreferencias() {
        SharedPreferences preferences = getSharedPreferences("configuracionTema", Context.MODE_PRIVATE);

        Configuration.strTema = preferences.getString("temaApp", "");

    }
}
