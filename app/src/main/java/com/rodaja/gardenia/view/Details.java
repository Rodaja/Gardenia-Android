package com.rodaja.gardenia.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rodaja.gardenia.R;

public class Details extends AppCompatActivity {
    ImageView ivDetails,ivChoose_plant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getSupportActionBar().hide();

        inicializar();

        setImage(R.drawable.main_plant,ivDetails);
        setImage(R.drawable.choose_photo_plot,ivChoose_plant);

        ivChoose_plant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToChooseImage(v);
            }
        });

    }

    private void inicializar() {
        ivDetails = findViewById(R.id.img_planta_1);
        ivChoose_plant = findViewById(R.id.img_Flecha_volver);
    }

    /**
     * Este metodo añade una imagen (R.drawable.*) a una vista
     * @param resourceId El recurso de la imagen
     * @param imageView La vista donde se va a incluir la imagen
     */
    private void setImage(Integer resourceId, ImageView imageView) {
        Glide.with(this).load(resourceId).apply(new RequestOptions().centerCrop()).into(imageView);
    }

    private void goToChooseImage(View view){
        Intent in = new Intent(this, ChooseImage.class);
        startActivity(in);
    }

}
