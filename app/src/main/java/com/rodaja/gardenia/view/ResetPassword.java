package com.rodaja.gardenia.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.rodaja.gardenia.R;
import com.rodaja.gardenia.model.entity.User;
import com.rodaja.gardenia.view.multimedia.Image;

public class ResetPassword extends AppCompatActivity {
    private ImageView ivBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        getSupportActionBar().hide();
        inicializar();
        Image.setImage(this, R.drawable.background, ivBackground);

    }
    private void inicializar() {
        ivBackground = findViewById(R.id.ivBackground);
    }

    private void goToView(Class goToView, User user) {
        Intent in = new Intent(this, goToView);
        in.putExtra("user", user);
        startActivity(in);
    }
}
