package com.rodaja.gardenia.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rodaja.gardenia.R;

public class ChooseImage extends AppCompatActivity {
    ImageView ivArrow_back_details, iv_plant_1, iv_plant_2, iv_plant_3, iv_plant_4, iv_plant_5, iv_plant_6, iv_plant_7, iv_plant_8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_image);
        getSupportActionBar().hide();
        inicializar();
        setImage(R.drawable.detalles_principal, iv_plant_1);
        setImage(R.drawable.detalles_principal, iv_plant_2);
        setImage(R.drawable.detalles_principal, iv_plant_3);
        setImage(R.drawable.detalles_principal, iv_plant_4);
        setImage(R.drawable.detalles_principal, iv_plant_5);
        setImage(R.drawable.detalles_principal, iv_plant_6);
        setImage(R.drawable.detalles_principal, iv_plant_7);
        setImage(R.drawable.detalles_principal, iv_plant_8);

        ivArrow_back_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToDetails(v);
            }
        });



    }


    private void inicializar() {
        ivArrow_back_details = findViewById(R.id.img_back);
        iv_plant_1 = findViewById(R.id.img_planta_1);
        iv_plant_2 = findViewById(R.id.img_planta_2);
        iv_plant_3 = findViewById(R.id.img_planta_3);
        iv_plant_4 = findViewById(R.id.img_planta_4);
        iv_plant_5 = findViewById(R.id.img_planta_5);
        iv_plant_6 = findViewById(R.id.img_planta_6);
        iv_plant_7 = findViewById(R.id.img_planta_7);
        iv_plant_8 = findViewById(R.id.img_planta_8);

    }

    private void setImage(Integer resourceId, ImageView imageView) {
        Glide.with(this).load(resourceId).apply(new RequestOptions().centerCrop()).into(imageView);
    }

    private void goToDetails(View view){
        Intent in = new Intent(this, Details.class);

        startActivity(in);
    }

}
