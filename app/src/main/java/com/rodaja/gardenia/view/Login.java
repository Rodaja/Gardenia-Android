package com.rodaja.gardenia.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rodaja.gardenia.R;

import java.io.File;

public class Login extends AppCompatActivity {

    private ImageView ivBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        inicializar();

        setImage(R.drawable.login_background, ivBackground);
}

    private void inicializar() {
        ivBackground = findViewById(R.id.ivBackground);
    }

    /**
     * Este metodo añade una imagen (R.drawable.*) a una vista
     * @param resourceId El recurso de la imagen
     * @param imageView La vista donde se va a incluir la imagen
     */
    private void setImage(Integer resourceId, ImageView imageView) {
        Glide.with(this).load(resourceId).apply(new RequestOptions().centerCrop()).into(imageView);
    }
}
