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
    ImageView ivArrow_back_detaills,iv_plant_one;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_image);
        getSupportActionBar().hide();
        inicializar();
        setImage(R.drawable.arrow_back,ivArrow_back_detaills);
        setImage(R.drawable.main_plant,iv_plant_one);

        ivArrow_back_detaills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToDetails(v);
            }
        });



    }


    private void inicializar() {
        ivArrow_back_detaills = findViewById(R.id.img_Flecha_volver);
        iv_plant_one = findViewById(R.id.img_planta_uno);

    }

    private void setImage(Integer resourceId, ImageView imageView) {
        Glide.with(this).load(resourceId).apply(new RequestOptions().centerCrop()).into(imageView);
    }

    private void goToDetails(View view){
        Intent in = new Intent(this, Details.class);

        startActivity(in);
    }

}
