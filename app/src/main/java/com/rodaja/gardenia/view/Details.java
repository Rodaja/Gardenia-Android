package com.rodaja.gardenia.view;

import androidx.appcompat.app.ActionBar;
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

public class Details extends AppCompatActivity {

    //Atributos Menu
    private TextView tvTitulo;
    private ImageView ivMenuIconLeft;
    private ImageView ivMenuIconRight;

    private ImageView ivDetails, ivChoose_plant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        inicializar();
        inicializarMenu();
        datosMaceta();

        Image.setImage(this, R.drawable.detalles_principal, ivDetails);

        ivChoose_plant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToChooseImage(v);
            }
        });

    }

    private void inicializar() {
        ivDetails = findViewById(R.id.img_planta_1);
        ivChoose_plant = findViewById(R.id.img_elegir_imagen);
    }

    private void datosMaceta() {

    }

    private void goToChooseImage(View view) {
        Intent in = new Intent(this, ChooseImage.class);
        startActivity(in);
    }

    private void inicializarMenu() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.menu);

        tvTitulo = findViewById(R.id.tvMenuTitulo);
        ivMenuIconLeft = findViewById(R.id.ivMenuIconLeft);
        ivMenuIconRight = findViewById(R.id.ivMenuIconRight);

        tvTitulo.setText(R.string.detalles);
        ivMenuIconLeft.setImageResource(R.drawable.back);
        ivMenuIconRight.setImageResource(R.drawable.icon_add);
    }

}
