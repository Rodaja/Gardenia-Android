package com.rodaja.gardenia.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.rodaja.gardenia.R;

public class Settings extends AppCompatActivity {

    //Atributos Menu
    private TextView tvTitulo;
    private ImageView ivMenuIconLeft;
    private ImageView ivMenuIconRight;
    private ConstraintLayout constLPreguntasFrecuentes, constLAjustesPorDefectoEditable, constLTemaEditable, contLReportarFallos, constLUnidadTemperaturaEditable;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        inicializarMenu();
        context = this;

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


        constLUnidadTemperaturaEditable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creamos array de opciones a seleccionar
                String[] unidadesTemperatura = getResources().getStringArray(R.array.settings_dialog_unidades_temperatura);
                //Por defecto para que solo se pueda seleccionar una opcion
                final int itemSelected = 0;
                //Creamos un objeto
                MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(context);
                //Seteamos el titulo
                dialog.setTitle(R.string.settings_dialog_unidad_temperatura);
                //Seleccionamos la opcion del RadioButton
                dialog.setSingleChoiceItems(unidadesTemperatura, itemSelected, new DialogInterface.OnClickListener() {
                    @Override
                    //selectedIndex selecciona la posicion seleccionada del array
                    public void onClick(DialogInterface dialogInterface, int selectedIndex) {
                        Log.d(String.valueOf(selectedIndex), "opcion");
                    }
                });

                //Damos funcionalidad al boton neutral (el de la izquierda)
                dialog.setNeutralButton(R.string.perfil_dialog_cancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("Cancelar", "Has seleccionado cancelar");
                    }
                });

                //Damos funcionalidad al boton positivo (el de la derecha)
                dialog.setPositiveButton(R.string.perfil_dialog_confirmar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("Confirmar", "Has seleccionado aceptar");
                    }
                });
                //Mostramos el cuadro de dialogo
                dialog.show();
            }
        });
        constLTemaEditable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creamos array de opciones a seleccionar
                String[] temas = getResources().getStringArray(R.array.settings_dialog_tema);
                //Por defecto para que solo se pueda seleccionar una opcion
                final int itemSelected = 0;
                //Creamos un objeto
                MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(context);
                //Seteamos el titulo
                dialog.setTitle(R.string.settings_dialog_tema);
                //Seleccionamos la opcion del RadioButton
                dialog.setSingleChoiceItems(temas, itemSelected, new DialogInterface.OnClickListener() {
                    @Override
                    //selectedIndex selecciona la posicion seleccionada del array
                    public void onClick(DialogInterface dialogInterface, int selectedIndex) {
                        Log.d(String.valueOf(selectedIndex), "opcion");
                    }
                });

                //Damos funcionalidad al boton neutral (el de la izquierda)
                dialog.setNeutralButton(R.string.perfil_dialog_cancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("Cancelar", "Has seleccionado cancelar");
                    }
                });

                //Damos funcionalidad al boton positivo (el de la derecha)
                dialog.setPositiveButton(R.string.perfil_dialog_confirmar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("Confirmar", "Has seleccionado aceptar");
                    }
                });
                //Mostramos el cuadro de dialogo
                dialog.show();
            }
        });
        constLAjustesPorDefectoEditable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creamos un objeto
                MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(context);
                //Seteamos el titulo
                dialog.setTitle(R.string.settings_dialog_titulo_ajustes_por_defecto);
                //Establecemos el mensaje que se mostrara
                dialog.setMessage(R.string.settings_dialog_mensaje_ajustes_por_defecto);
                //Damos funcionalidad al boton neutral (el de la izquierda)
                dialog.setNeutralButton(R.string.perfil_dialog_cancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("Cancelar", "Has seleccionado cancelar");
                    }
                });

                //Damos funcionalidad al boton positivo (el de la derecha)
                dialog.setPositiveButton(R.string.perfil_dialog_confirmar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("Confirmar", "Has seleccionado aceptar");
                    }
                });
                //Mostramos el cuadro de dialogo
                dialog.show();
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

        constLAjustesPorDefectoEditable = findViewById(R.id.constLAjustesPorDefectoEditable);
        constLTemaEditable = findViewById(R.id.constLTemaEditable);
        constLUnidadTemperaturaEditable = findViewById(R.id.constLUnidadTemperaturaEditable);
        constLPreguntasFrecuentes = findViewById(R.id.constLPreguntasFrecuentesEditable);
        contLReportarFallos = findViewById(R.id.constLReportarFalloEditable);
    }

    private void goToNewView(View view , Class goToView){
        Intent in = new Intent(this, goToView);
        startActivity(in);
    }

}
