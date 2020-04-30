package com.rodaja.gardenia.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rodaja.gardenia.R;
import com.rodaja.gardenia.view.multimedia.Image;

import java.io.File;

public class Login extends AppCompatActivity {

    private ImageView ivBackground;
    private TextView tvSignUp;
    private TextView tvForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        inicializar();

        Image.setImage(this, R.drawable.login_background, ivBackground);

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewView(v, Signup.class);
            }
        });
        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewView(v, ForgotPassword.class);
            }
        });
    }

    private void inicializar() {
        ivBackground = findViewById(R.id.ivBackground);
        tvSignUp = findViewById(R.id.tvSignUp);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
    }

    private void goToNewView(View view, Class goToView) {
        Intent in = new Intent(this, goToView);
        startActivity(in);
    }

}
