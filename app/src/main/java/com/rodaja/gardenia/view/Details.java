package com.rodaja.gardenia.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rodaja.gardenia.R;
import com.rodaja.gardenia.model.entity.FlowerPot;
import com.rodaja.gardenia.model.entity.User;
import com.rodaja.gardenia.view.multimedia.Image;

import java.util.List;

public class Details extends AppCompatActivity {

    //Atributos Menu
    private TextView tvTitulo;
    private ImageView ivMenuIconLeft;
    private ImageView ivMenuIconRight;
    private TextView tv_titulo_detalle_maceta, tv_humedad_tierra_medida, tv_humedad_ambiental_medida, tv_temperatura_ambiental_medida2;
    private User user;
    private ImageView ivDetails, ivChoose_plant, ivDeleteFlowerpot;
    private FlowerPot maceta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        maceta = getExtras();
        inicializarMenu();
        setearValores();

        Image.setImage(this, R.drawable.detalles_principal, ivDetails);

        ivChoose_plant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToChooseImage(v);
            }
        });

        ivMenuIconLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewView(v, Home.class);
            }
        });
        ivMenuIconRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewView(v, AddFlowerPot.class);
            }
        });

    }

    private FlowerPot getExtras() {
        Intent in = getIntent();
        int numeroMacetas = (int) in.getSerializableExtra("numeroMaceta");
        user = (User) in.getSerializableExtra("user");
        //Coge la maceta seleccionada
        FlowerPot maceta = user.getListFlowerPots().get(numeroMacetas);
        return maceta;
    }


    private void setearValores() {
        tv_humedad_tierra_medida.setText(String.valueOf(maceta.getGroundHumidity()));
        tv_temperatura_ambiental_medida2.setText(String.valueOf(maceta.getAirTemperature()));
        tv_humedad_ambiental_medida.setText(String.valueOf(maceta.getAirHumidity()));
        tv_titulo_detalle_maceta.setText(String.valueOf(maceta.getName()));
    }

    private void goToChooseImage(View view) {
        Intent in = new Intent(this, ChooseImage.class);
        startActivity(in);
    }

    private void inicializarMenu() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.menu);

        ivDetails = findViewById(R.id.img_planta_1);
        ivChoose_plant = findViewById(R.id.img_elegir_imagen);

        tv_humedad_tierra_medida = findViewById(R.id.tv_humedad_tierra_medida);
        tv_temperatura_ambiental_medida2 = findViewById(R.id.tv_temperatura_ambiental_medida2);
        tv_humedad_ambiental_medida = findViewById(R.id.tv_humedad_ambiental_medida);
        tv_titulo_detalle_maceta = findViewById(R.id.tv_titulo_detalle_maceta);
        ivDeleteFlowerpot = findViewById(R.id.ivDeleteFlowerpot);

        tvTitulo = findViewById(R.id.tvMenuTitulo);
        ivMenuIconLeft = findViewById(R.id.ivMenuIconLeft);
        ivMenuIconRight = findViewById(R.id.ivMenuIconRight);

        tvTitulo.setText(R.string.detalles);
        ivMenuIconLeft.setImageResource(R.drawable.back);
        ivMenuIconRight.setImageResource(R.drawable.icon_add);
    }

    private void goToNewView(View view, Class goToView) {
        Intent in = new Intent(this, goToView);
        startActivity(in);
    }


}
