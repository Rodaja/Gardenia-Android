package com.rodaja.gardenia.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rodaja.gardenia.R;

public class Login extends AppCompatActivity {

    private ImageView ivBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        inicializar();

        Glide.with(this).load(R.drawable.login_background).apply(new RequestOptions().centerCrop()).into(ivBackground);
    }

    private void inicializar() {
        ivBackground = findViewById(R.id.ivBackground);
    }
}
