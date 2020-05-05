package com.rodaja.gardenia.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rodaja.gardenia.R;

public class Settings extends AppCompatActivity {

    //Atributos Menu
    private TextView tvTitulo;
    private ImageView ivMenuIconLeft;
    private ImageView ivMenuIconRight;
    private ConstraintLayout constLPreguntasFrecuentes,contLReportarFallos;
    //
    private com.rodaja.gardenia.model.configuration.Settings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        inicializarMenu();

        ivMenuIconLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewView(v,Profile.class);
            }
        });
        ivMenuIconRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewView(v,AddFlowerPot.class);
            }
        });
        constLPreguntasFrecuentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewView(v,Faq.class);
            }
        });
        contLReportarFallos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewView(v,FailReport.class);
            }
        });


    }

    private void inicializarMenu() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.menu);

        tvTitulo  = findViewById(R.id.tvMenuTitulo);
        ivMenuIconLeft = findViewById(R.id.ivMenuIconLeft);
        ivMenuIconRight = findViewById(R.id.ivMenuIconRight);

        tvTitulo.setText(R.string.configuracion);
        ivMenuIconRight.setImageResource(R.drawable.icon_add);

        constLPreguntasFrecuentes = findViewById(R.id.constLPreguntasFrecuentesEditable);
        contLReportarFallos = findViewById(R.id.constLReportarFalloEditable);
    }

    private void goToNewView(View view , Class goToView){
        Intent in = new Intent(this, goToView);
        startActivity(in);
    }

}
