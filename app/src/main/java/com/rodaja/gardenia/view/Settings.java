package com.rodaja.gardenia.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.gson.Gson;
import com.rodaja.gardenia.R;
import com.rodaja.gardenia.model.configuration.Configuration;
import com.rodaja.gardenia.model.configuration.Constants;
import com.rodaja.gardenia.model.entity.User;
import com.rodaja.gardenia.model.navegation.Navegation;
import com.rodaja.gardenia.model.web.Json;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Settings extends Fragment {

    //Atributos Menu
    private TextView tvTitulo, tvUnidadTemperaturaSimbolo;
    private ImageView ivMenuIconLeft;
    private ConstraintLayout constLPreguntasFrecuentes, constLAjustesPorDefectoEditable, constLTemaEditable, contLReportarFallos, constLUnidadTemperaturaEditable;
    private Context context;
    private View view;
    private int cambiaOpcion = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_settings, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        super.onCreate(savedInstanceState);
        view = getView();
        context = view.getContext();
        inicializarMenu();

        //tvUnidadTemperaturaSimbolo.setText(Configuration.getTemperatureUnit(user));

        constLPreguntasFrecuentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navegation.goToView(context, Faq.class);
            }
        });

        contLReportarFallos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navegation.goToView(context, FailReport.class);
            }
        });


        constLUnidadTemperaturaEditable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creamos array de opciones a seleccionar
                String[] unidadesTemperatura = getResources().getStringArray(R.array.settings_dialog_unidades_temperatura);
                //Por defecto para que solo se pueda seleccionar una opcion
                final int itemSelected = 0;
                //Variable para cambiar de opcion al seleccionar en aceptar

                //Creamos un objeto
                MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(context);
                //Seteamos el titulo
                dialog.setTitle(R.string.settings_dialog_unidad_temperatura);
                //Seleccionamos la opcion del RadioButton
                dialog.setSingleChoiceItems(unidadesTemperatura, itemSelected, new DialogInterface.OnClickListener() {
                    @Override
                    //selectedIndex selecciona la posicion seleccionada del array
                    public void onClick(DialogInterface dialogInterface, int selectedIndex) {
                        cambiaOpcion = selectedIndex;
                    }
                });

                //Damos funcionalidad al boton neutral (el de la izquierda)
                dialog.setNeutralButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("Cancelar", "Has seleccionado cancelar");
                    }
                });

                //Damos funcionalidad al boton positivo (el de la derecha)
                dialog.setPositiveButton(R.string.alert_dialog_accept, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //String temperatura = Configuration.getTemperatureString(cambiaOpcion);
                        //user.setTemperature(temperatura);
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
                dialog.setNeutralButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("Cancelar", "Has seleccionado cancelar");
                    }
                });

                //Damos funcionalidad al boton positivo (el de la derecha)
                dialog.setPositiveButton(R.string.alert_dialog_accept, new DialogInterface.OnClickListener() {
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
                MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(context);
                dialog.setTitle(R.string.settings_dialog_titulo_ajustes_por_defecto);
                dialog.setMessage(R.string.settings_dialog_mensaje_ajustes_por_defecto);

                dialog.setNeutralButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                dialog.setPositiveButton(R.string.alert_dialog_accept, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Configuration.defaultConfiguration(user);
                    }
                });
                dialog.show();
            }
        });
    }


    private void inicializarMenu() {

        constLAjustesPorDefectoEditable = view.findViewById(R.id.constLAjustesPorDefectoEditable);
        constLTemaEditable = view.findViewById(R.id.constLTemaEditable);
        constLUnidadTemperaturaEditable = view.findViewById(R.id.constLUnidadTemperaturaEditable);
        constLPreguntasFrecuentes = view.findViewById(R.id.constLPreguntasFrecuentesEditable);
        contLReportarFallos = view.findViewById(R.id.constLReportarFalloEditable);
        tvUnidadTemperaturaSimbolo = view.findViewById(R.id.tvUnidadTemperaturaSimbolo);


    }

    /*
    @Override
    public void onBackPressed() {
        Navegation.goToView(context, Login.class);
    }


     */
}
