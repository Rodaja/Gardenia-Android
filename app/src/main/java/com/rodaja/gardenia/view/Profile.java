package com.rodaja.gardenia.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

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
import com.rodaja.gardenia.model.configuration.Constants;
import com.rodaja.gardenia.model.entity.User;
import com.rodaja.gardenia.model.navegation.Navegation;
import com.rodaja.gardenia.model.validation.Validation;
import com.rodaja.gardenia.model.web.Json;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Profile extends AppCompatActivity {

    private Context context;

    //Atributos Menu
    private ImageView ivMenuIconLeft, ivMenuIconRight;

    private TextView tvTitulo, tituloCorreo, tituloNombreCompleto, tvPaisEt,
            tvApellidosEt, tvCorreoElectronicoEt, tvNombreUsuarioEt, tvNombreEt;
    private ConstraintLayout constLNombreUsuarioEditable, constLCorreoElectronicoEditable, constLBorrarCuentaEditable, constLCambiarContrasenaEditable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        inicializar();
        context = this;

        cargarDatosUsuariosReales();

        ivMenuIconLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navegation.goToView(context, Home.class);
            }
        });

        constLCorreoElectronicoEditable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Enviar correo de cambio de email unicamente
            }
        });

        //TODO: Revisar
        constLNombreUsuarioEditable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(context);
                dialog.setTitle(R.string.perfil_dialog_titulo_username);

                //Creamos un Linear Layout para organizar los editText de su interior
                //Ademas con el LinerLayout podemos darle margenes (con padding) a los ediText
                LinearLayout layout = new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.setPadding(65, 24, 65, 24);

                //Añadir editText al dialog
                final EditText etUserName = new EditText(context);
                etUserName.setHint(user.getUserName());
                layout.addView(etUserName);
                dialog.setView(layout);

                dialog.setNeutralButton(R.string.perfil_dialog_cancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("Cancelar", "Has seleccionado cancelar");
                    }
                });

                dialog.setPositiveButton(R.string.perfil_dialog_confirmar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("Confirmar", "Has seleccionado aceptar");
                        cargarDatosUsuariosReales();
                    }
                });

                dialog.show();
            }
        });

        constLCambiarContrasenaEditable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Enviar email con cambio de contraseña
            }
        });

        constLBorrarCuentaEditable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(context);
                dialog.setTitle(R.string.perfil_dialog_titulo_borrar_cuenta);

                dialog.setMessage(R.string.perfil_dialog_mensaje_borrar_cuenta);

                dialog.setNeutralButton(R.string.perfil_dialog_cancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("Cancelar", "Has seleccionado cancelar");
                    }
                });

                dialog.setPositiveButton(R.string.perfil_dialog_borrar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("Borrar", "Has seleccionado aceptar");
                    }
                });
                //Mostramos el cuadro de dialogo
                dialog.show();
            }
        });
    }

    //TODO: Cargar los datos del usuario desde firestore
    private void cargarDatosUsuariosReales() {
        tituloCorreo.setText(user.getEmail());
        tvCorreoElectronicoEt.setText(user.getEmail());
        tvNombreUsuarioEt.setText(user.getUserName());
        tituloNombreCompleto.setText(user.getUserName());
        tvNombreEt.setText(user.getName());
        tvApellidosEt.setText(user.getSurname());
        tvPaisEt.setText(user.getCountry());
    }


    private void inicializar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.menu);

        tvTitulo = findViewById(R.id.tvMenuTitulo);
        ivMenuIconLeft = findViewById(R.id.ivMenuIconLeft);
        ivMenuIconRight = findViewById(R.id.ivMenuIconRight);

        tvTitulo.setText(R.string.perfil);
        ivMenuIconRight.setImageResource(R.drawable.icon_save);
        tituloCorreo = findViewById(R.id.tituloCorreo);
        tvCorreoElectronicoEt = findViewById(R.id.tvCorreoElectronicoEt);
        tvNombreUsuarioEt = findViewById(R.id.tvNombreUsuarioEt);
        tituloNombreCompleto = findViewById(R.id.tituloNombreCompleto);
        tvNombreEt = findViewById(R.id.tvNombreEt);
        tvApellidosEt = findViewById(R.id.tvApellidosEt);
        tvPaisEt = findViewById(R.id.tvPaisEt);

        constLCerrarSesionEditable = findViewById(R.id.constLCerrarSesionEditable);
        constLBorrarCuentaEditable = findViewById(R.id.constLBorrarCuentaEditable);
        constLPaises = findViewById(R.id.constLPaisEditable);
        constLConfiuracion = findViewById(R.id.constLConfiguracionEditable);
        constLCambiarContrasenaEditable = findViewById(R.id.constLCambiarContrasenaEditable);
        constLCorreoElectronicoEditable = findViewById(R.id.constLCorreoElectronicoEditable);
        constLNombreUsuarioEditable = findViewById(R.id.constLNombreUsuarioEditable);
        constLNombreEditable = findViewById(R.id.constLNombreEditable);
        constLApellidosEditable = findViewById(R.id.constLApellidosEditable);
    }

    @Override
    public void onBackPressed() {
        Navegation.goToView(context, Home.class);
    }
}
