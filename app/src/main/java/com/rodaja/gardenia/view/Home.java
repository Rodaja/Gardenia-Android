package com.rodaja.gardenia.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rodaja.gardenia.R;
import com.rodaja.gardenia.model.adapter.HomeAdapter;
import com.rodaja.gardenia.model.configuration.Configuration;
import com.rodaja.gardenia.model.entity.FlowerPot;
import com.rodaja.gardenia.model.entity.User;

import java.util.List;

public class Home extends AppCompatActivity {

    //Atributos Menu
    private TextView tvTitulo;
    private ImageView ivMenuIconLeft;
    private ImageView ivMenuIconRight;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayout;
    private Context context;
    private HomeAdapter adapter;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Configuration.verificarTemaDark(this);

        setContentView(R.layout.activity_home);
        inicializarMenu();

        //Cambiamos algunos aspectos de color para el tema oscuro
        temaOscuroActivadoColores();

        context = this;

        Intent in = getIntent();
        user = (User) in.getSerializableExtra("user");
        final List<FlowerPot> listaMacetas = user.getListFlowerPots();
        recyclerView.setLayoutManager(linearLayout);
        adapter = new HomeAdapter(context, listaMacetas);
        recyclerView.setAdapter(adapter);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goDetails(v, Details.class, recyclerView.getChildAdapterPosition(v), user);
            }
        });

        ivMenuIconLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewView(v, Profile.class, user);
            }
        });

        ivMenuIconRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewView(v, AddFlowerPot.class, user);
            }
        });


    }

    private void temaOscuroActivadoColores() {
        //Coloreamos las imagenes a blanco solo en tema DARK
        if (Configuration.strTema.equalsIgnoreCase("dark")) {
            ivMenuIconLeft.setColorFilter(getResources().getColor(R.color.colorWhite));
        }
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

        recyclerView = (RecyclerView) findViewById(R.id.rvHome);
        linearLayout = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayout);
    }

    @Override
    public void onBackPressed() {
        Intent in = new Intent(this, Home.class);
        in.putExtra("user", user);
        startActivity(in);
    }

    private void goDetails(View view, Class goToView, int numeroMaceta, User user) {
        Intent in = new Intent(this, goToView);
        in.putExtra("numeroMaceta", numeroMaceta);
        in.putExtra("user", user);
        startActivity(in);
    }

    private void goToNewView(View view, Class goToView, User user) {
        Intent in = new Intent(this, goToView);
        in.putExtra("user", user);
        startActivity(in);
    }

}
