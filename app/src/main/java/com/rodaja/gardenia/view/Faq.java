package com.rodaja.gardenia.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rodaja.gardenia.R;
import com.rodaja.gardenia.model.entity.User;
import com.rodaja.gardenia.model.navegation.Navegation;

public class Faq extends AppCompatActivity {

    //Atributos Menu
    private TextView tvTitulo;
    private ImageView ivMenuIconLeft;
    private ImageView ivMenuIconRight;

    //Atributos
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        inicializar();
        context = this;

        ivMenuIconLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navegation.goToView(context, Settings.class);
            }
        });

        ivMenuIconRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navegation.goToView(context, AddFlowerPot.class);
            }
        });
    }

    private void inicializar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.menu);

        tvTitulo = findViewById(R.id.tvMenuTitulo);
        ivMenuIconLeft = findViewById(R.id.ivMenuIconLeft);
        ivMenuIconRight = findViewById(R.id.ivMenuIconRight);

        tvTitulo.setText(R.string.preguntas);
        ivMenuIconLeft.setImageResource(R.drawable.back);
        ivMenuIconRight.setImageResource(R.drawable.icon_add);
    }

}
