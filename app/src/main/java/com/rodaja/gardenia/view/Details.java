package com.rodaja.gardenia.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.rodaja.gardenia.model.configuration.Permissions;
import com.rodaja.gardenia.model.entity.FlowerPot;
import com.rodaja.gardenia.model.entity.User;
import com.rodaja.gardenia.model.navegation.Navegation;
import com.rodaja.gardenia.model.notification.Notifications;
import com.rodaja.gardenia.view.multimedia.Image;

import org.json.JSONObject;

import java.io.File;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.List;

public class Details extends AppCompatActivity {

    private Context context;

    //Atributos Menu
    private TextView tvTitulo;
    private ImageView ivMenuIconLeft;
    private ImageView ivMenuIconRight;
    private TextView tv_titulo_detalle_maceta, tv_humedad_tierra_medida, tv_humedad_ambiental_medida, tv_temperatura_ambiental_medida2;
    private User user;
    private ImageView ivDetails, ivChoosePlant, ivDeleteFlowerpot, ivChangeName;

    private SwipeRefreshLayout swipeRefreshLayout;
    private Button btnRegar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        inicializar();
        context = this;

        setValues();
        //checkMacetaImageUrl();

        ivChoosePlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //goToChooseImage(v);
                selectGalleryImage();
            }
        });

        ivMenuIconLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navegation.goToView(context, Home.class);
            }
        });
        ivMenuIconRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navegation.goToView(context, AddFlowerPot.class);
            }
        });

        ivChangeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();
            }
        });

        ivDeleteFlowerpot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(context);
                dialog.setTitle(R.string.details_borrar_maceta_titulo);
                dialog.setMessage(R.string.details_borrar_maceta_mensaje);
                //Damos funcionalidad al boton neutral (el de la izquierda)
                dialog.setNeutralButton(R.string.perfil_dialog_cancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("Cancelar", "Has seleccionado cancelar");
                    }
                });

                //Damos funcionalidad al boton positivo (el de la derecha)
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

        swipeRefreshLayout.setOnRefreshListener(mOnRefreshListener);

        btnRegar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //FUNCIONALIDAD REGAR
            }
        });


    }

    @Override
    public void onBackPressed() {
        Navegation.goToView(context, Home.class);
    }

    /*
    private void checkMacetaImageUrl() {
        File img = new File(maceta.getImageUrl());
        if (img.exists()){
            Image.setUriImageRoundedCorners(this, maceta.getImageUrl(), ivDetails, 25);
        } else{
            Image.setImageRoundedCorners(this, R.drawable.detalles_principal, ivDetails, 25);
        }
    }
    */


    //TODO: Refactorizar
    private void selectGalleryImage() {
        if (Permissions.checkPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            openGallery();
        } else {
            Log.d("Permisos", "Activa los permisos");
            Permissions.askForPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Permissions.READ_EXTERNAL_STORAGE);
        }
    }

    //TODO: Refactorizar
    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, 100);
    }

    //TODO: Documentar
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 100) {
            Uri imageUri = data.getData();
            Log.d("URI", imageUri.getPath());
            Log.d("Scheme", imageUri.getScheme());
            Log.d("String", imageUri.toString());

            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = context.getContentResolver().query(imageUri, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            Log.d("Cargando la imagen", "Cargando la imagen");
            Image.setUriImageRoundedCorners(context, picturePath, ivDetails, 25);
        }
    }


    protected SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            setValues();
            swipeRefreshLayout.setRefreshing(false);
        }
    };

    private void showAlertDialog() {
        MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(context);
        //Seteamos el titulo
        dialog.setTitle(R.string.cambiar_nombre_maceta);

        dialog.setMessage(R.string.cambiar_nombre_maceta_texto);

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(65, 24, 65, 24);
        final EditText etNombreMaceta = new EditText(context);
        etNombreMaceta.setHint(tv_titulo_detalle_maceta.getText().toString());
        layout.addView(etNombreMaceta);
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
                String newName = etNombreMaceta.getText().toString();
                Log.d("Borrar", "Has seleccionado aceptar");
                tv_titulo_detalle_maceta.setText(newName);
            }
        });

        dialog.show();
    }

    //TODO: Refactorizar
    private void setValues() {
        /**
         *

         tv_humedad_tierra_medida.setText(String.valueOf(maceta.getGroundHumidity()) + "%");

         int temperatura = Configuration.getTemperatureValue(user, maceta.getAirTemperature());
         tv_temperatura_ambiental_medida2.setText(String.valueOf(temperatura) + unidadTemperatura);
         tv_humedad_ambiental_medida.setText(String.valueOf(maceta.getAirHumidity()) + "%");
         tv_titulo_detalle_maceta.setText(String.valueOf(maceta.getName()));
         */

    }

    private boolean inicializar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.menu);

        ivDetails = findViewById(R.id.img_planta_1);
        ivChoosePlant = findViewById(R.id.img_elegir_imagen);

        tv_humedad_tierra_medida = findViewById(R.id.tv_humedad_tierra_medida);
        tv_temperatura_ambiental_medida2 = findViewById(R.id.tv_temperatura_ambiental_medida2);
        tv_humedad_ambiental_medida = findViewById(R.id.tv_humedad_ambiental_medida);
        tv_titulo_detalle_maceta = findViewById(R.id.tv_titulo_detalle_maceta);
        ivChangeName = findViewById(R.id.img_Tarjeta_elegir_nombre);
        ivDeleteFlowerpot = findViewById(R.id.ivDeleteFlowerpot);
        swipeRefreshLayout = findViewById(R.id.swipeDetails);
        btnRegar = findViewById(R.id.btnRegar);

        tvTitulo = findViewById(R.id.tvMenuTitulo);
        ivMenuIconLeft = findViewById(R.id.ivMenuIconLeft);
        ivMenuIconRight = findViewById(R.id.ivMenuIconRight);

        tvTitulo.setText(R.string.detalles);
        ivMenuIconLeft.setImageResource(R.drawable.back);
        ivMenuIconRight.setImageResource(R.drawable.icon_add);

        return true;
    }


}
