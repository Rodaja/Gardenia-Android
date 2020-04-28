package com.rodaja.gardenia.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.rodaja.gardenia.R;

public class Profile extends AppCompatActivity {

    private ConstraintLayout constLCerrarSesionEditable;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        inicializar();

        context = this;

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
    }
}
