package com.rodaja.gardenia.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rodaja.gardenia.R;
import com.rodaja.gardenia.model.entity.User;

public class Home extends AppCompatActivity {

    //Atributos Menu
    private TextView tvTitulo;
    private ImageView ivMenuIconLeft;
    private ImageView ivMenuIconRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        inicializarMenu();
        Intent in = getIntent();
        User user = (User) in.getSerializableExtra("user");
        Log.d("user: ", user.getEmail());

        ivMenuIconLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewView(v, Profile.class);
            }
        });

        ivMenuIconRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewView(v, AddFlowerPot.class);
            }
        });


    }

    private void inicializarMenu() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.menu);

        tvTitulo = findViewById(R.id.tvMenuTitulo);
        ivMenuIconLeft = findViewById(R.id.ivMenuIconLeft);
        ivMenuIconRight = findViewById(R.id.ivMenuIconRight);

        tvTitulo.setText(R.string.home);
        ivMenuIconLeft.setImageResource(R.drawable.perfil);
        ivMenuIconRight.setImageResource(R.drawable.icon_add);
    }


    private void goToNewView(View view, Class goToView) {
        Intent in = new Intent(this, goToView);
        startActivity(in);
    }

}
