package com.rodaja.gardenia.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rodaja.gardenia.R;
import com.rodaja.gardenia.view.multimedia.Image;

import java.io.File;

public class Login extends AppCompatActivity {

    private ImageView ivBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        inicializar();

        Image.setImage(this, R.drawable.login_background, ivBackground);
}

    private void inicializar() {
        ivBackground = findViewById(R.id.ivBackground);
    }

}
