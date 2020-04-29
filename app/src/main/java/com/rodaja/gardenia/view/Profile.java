package com.rodaja.gardenia.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.rodaja.gardenia.R;
import com.rodaja.gardenia.view.multimedia.Image;

public class Profile extends AppCompatActivity {

    //Atributos Menu
    private TextView tvTitulo;
    private ImageView ivMenuIconLeft;
    private ImageView ivMenuIconRight;

    private ConstraintLayout constLCerrarSesionEditable,constLPaises,constLConfiuracion;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        inicializar();
        inicializarMenu();

        context = this;

        ivMenuIconLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewView(v,Home.class);
            }
        });

        constLPaises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewView(v,Country.class);
            }
        });

        constLConfiuracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewView(v,Settings.class);
            }
        });





        constLCerrarSesionEditable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creamos un objeto
                MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(context);
                //Seteamos el titulo
                dialog.setTitle("Hola");
                //Establecemos el mensaje que se mostrara
                dialog.setMessage("Esto es una prueba de funcionamiento");
                //Damos funcionalidad al boton neutral (el de la izquierda)
                dialog.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("Cancelar", "Has seleccionado cancelar");
                    }
                });

                //Damos funcionalidad al boton positivo (el de la derecha)
                dialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("Aceptar", "Has seleccionado aceptar");
                    }
                });
                //Mostramos el cuadro de dialogo
                dialog.show();
            }
        });
    }

    private void inicializar() {
        constLCerrarSesionEditable = findViewById(R.id.constLCerrarSesionEditable);
        constLPaises = findViewById(R.id.constLPaisEditable);
        constLConfiuracion = findViewById(R.id.constLConfiguracionEditable);
    }

    private void inicializarMenu() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.menu);

        tvTitulo  = findViewById(R.id.tvMenuTitulo);
        ivMenuIconLeft = findViewById(R.id.ivMenuIconLeft);
        ivMenuIconRight = findViewById(R.id.ivMenuIconRight);

        tvTitulo.setText(R.string.perfil);
        ivMenuIconRight.setImageResource(R.drawable.icon_save);
    }

    private void goToNewView(View view , Class goToView){
        Intent in = new Intent(this, goToView);
        startActivity(in);
    }
}
