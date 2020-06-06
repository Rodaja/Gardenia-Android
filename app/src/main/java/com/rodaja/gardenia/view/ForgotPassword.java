package com.rodaja.gardenia.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.rodaja.gardenia.R;
import com.rodaja.gardenia.model.entity.User;
import com.rodaja.gardenia.view.multimedia.Image;

public class ForgotPassword extends AppCompatActivity {

    private Button btnEntendido;
    private ImageView ivBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        getSupportActionBar().hide();
        inicializar();
        Image.setImage(this, R.drawable.background, ivBackground);

        btnEntendido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToView(ResetPassword.class, null);
            }
        });
    }

    private void inicializar() {
        btnEntendido = findViewById(R.id.btnEntendido);
        ivBackground = findViewById(R.id.ivBackground);
    }

    private void goToView(Class goToView, User user) {
        Intent in = new Intent(this, goToView);
        in.putExtra("user", user);
        startActivity(in);
    }
}
