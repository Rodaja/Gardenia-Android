package com.rodaja.gardenia.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
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
import com.rodaja.gardenia.model.security.Hash;
import com.rodaja.gardenia.model.validation.Validation;
import com.rodaja.gardenia.model.web.Json;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Profile extends AppCompatActivity {

    //Atributos Menu
    private TextView tvTitulo, tituloCorreo, tituloNombreCompleto, tvPaisEt,
            tvApellidosEt, tvCorreoElectronicoEt, tvNombreUsuarioEt, tvNombreEt;
    private ImageView ivMenuIconLeft, ivMenuIconRight;

    private SQLiteDatabase sqLiteDatabase;
    private SQLiteOpenHelper sqLiteOpenHelper;

    private ConstraintLayout constLCerrarSesionEditable, constLApellidosEditable, constLNombreEditable, constLNombreUsuarioEditable, constLCorreoElectronicoEditable, constLBorrarCuentaEditable, constLCambiarContrasenaEditable, constLPaises, constLConfiuracion;
    private Context context;
    private User user;
    private boolean cambiosGuardar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        inicializarMenu();
        cambiosGuardar = false;
        cambiarImagenGuardar();
        context = this;
        Intent in = getIntent();
        user = (User) in.getSerializableExtra("user");

        cargarDatosUsuariosReales();

        ivMenuIconLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewView(Home.class, user);
            }
        });

        constLPaises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiosGuardar = true;
                goToNewView(Country.class, user);
            }
        });

        constLConfiuracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewView(Settings.class, user);
            }
        });

        ivMenuIconRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Si ha habido alguna modificacion puedes guardar los cambios
                if (cambiosGuardar == true) {
                    profileRequest();
                }
            }
        });


        constLCorreoElectronicoEditable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(context);
                //Seteamos el titulo
                dialog.setTitle(R.string.perfil_dialog_titulo_email);

                //Creamos un Linear Layout para organizar los editText de su interior
                //Ademas con el LinerLayout podemos darle margenes (con padding) a los ediText
                LinearLayout layout = new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.setPadding(65, 24, 65, 24);

                //Añadir editText al dialog
                final EditText etEmail = new EditText(context);
                etEmail.setHint(user.getEmail());
                layout.addView(etEmail);
                dialog.setView(layout);

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
                        user.setEmail(etEmail.getText().toString());
                        cargarDatosUsuariosReales();
                        cambiosGuardar = true;
                        cambiarImagenGuardar();
                    }
                });
                //Mostramos el cuadro de dialogo
                dialog.show();
            }
        });

        constLNombreUsuarioEditable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(context);
                //Seteamos el titulo
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
                        user.setUserName(etUserName.getText().toString());
                        cargarDatosUsuariosReales();
                        cambiosGuardar = true;
                        cambiarImagenGuardar();
                    }
                });
                //Mostramos el cuadro de dialogo
                dialog.show();
            }
        });

        constLNombreEditable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(context);
                //Seteamos el titulo
                dialog.setTitle(R.string.perfil_dialog_titulo_nombre);

                //Creamos un Linear Layout para organizar los editText de su interior
                //Ademas con el LinerLayout podemos darle margenes (con padding) a los ediText
                LinearLayout layout = new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.setPadding(65, 24, 65, 24);

                //Añadir editText al dialog
                final EditText etNombre = new EditText(context);
                etNombre.setHint(user.getName());
                layout.addView(etNombre);
                dialog.setView(layout);

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
                        user.setName(etNombre.getText().toString());
                        cargarDatosUsuariosReales();
                        cambiosGuardar = true;
                        cambiarImagenGuardar();
                    }
                });
                //Mostramos el cuadro de dialogo
                dialog.show();
            }
        });

        constLApellidosEditable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(context);
                //Seteamos el titulo
                dialog.setTitle(R.string.perfil_dialog_titulo_apellidos);

                //Creamos un Linear Layout para organizar los editText de su interior
                //Ademas con el LinerLayout podemos darle margenes (con padding) a los ediText
                LinearLayout layout = new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.setPadding(65, 24, 65, 24);

                //Añadir editText al dialog
                final EditText etApellidos = new EditText(context);
                etApellidos.setHint(user.getSurname());
                layout.addView(etApellidos);
                dialog.setView(layout);

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
                        user.setSurname(etApellidos.getText().toString());
                        cargarDatosUsuariosReales();
                        cambiosGuardar = true;
                        cambiarImagenGuardar();
                    }
                });
                //Mostramos el cuadro de dialogo
                dialog.show();
            }
        });
        constLCambiarContrasenaEditable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos un objeto
                MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(context);
                //Seteamos el titulo
                dialog.setTitle(R.string.perfil_dialog_titulo_cambiar_contrasena);

                //Creamos un Linear Layout para organizar los editText de su interior
                //Ademas con el LinerLayout podemos darle margenes (con padding) a los ediText
                LinearLayout layout = new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.setPadding(65, 24, 65, 24);

                //Añadir editText al dialog
                final EditText etContrasenaActual = new EditText(context);
                etContrasenaActual.setHint(R.string.perfil_dialog_et_contrasena_actual_hint);
                layout.addView(etContrasenaActual);

                final EditText etNuevaContrasena = new EditText(context);
                etNuevaContrasena.setHint(R.string.perfil_dialog_et_nueva_contrasena_hint);
                layout.addView(etNuevaContrasena);

                final EditText etRepetirContrasena = new EditText(context);
                etRepetirContrasena.setHint(R.string.perfil_dialog_et_repetir_contrasena_hint);
                layout.addView(etRepetirContrasena);
                dialog.setView(layout);

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
                        cambiarContrasena(etContrasenaActual.getText().toString(),
                                etNuevaContrasena.getText().toString(),
                                etRepetirContrasena.getText().toString());
                    }
                });
                //Mostramos el cuadro de dialogo
                dialog.show();
            }
        });

        constLCerrarSesionEditable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creamos un objeto
                MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(context);
                //Seteamos el titulo
                dialog.setTitle(R.string.perfil_dialog_titulo_cerrar_sesion);
                //Establecemos el mensaje que se mostrara
                dialog.setMessage(R.string.perfil_dialog_mensaje_cerrar_sesion);


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
                        setupDataBase();
                        borrarDatabase();
                        goLogin(Login.class);


                    }
                });
                //Mostramos el cuadro de dialogo
                dialog.show();
            }
        });
        constLBorrarCuentaEditable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creamos un objeto
                MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(context);
                //Seteamos el titulo
                dialog.setTitle(R.string.perfil_dialog_titulo_borrar_cuenta);
                //Establecemos el mensaje que se mostrara
                dialog.setMessage(R.string.perfil_dialog_mensaje_borrar_cuenta);
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
                        borrarUsuario();


                    }
                });
                //Mostramos el cuadro de dialogo
                dialog.show();
            }
        });
    }

    public void setupDataBase() {
        sqLiteOpenHelper = new SQLiteOpenHelper(getApplicationContext(), Constants.NOMBRE_BASE_DATOS, null, 1) {
            @Override
            public void onCreate(SQLiteDatabase sqLiteDatabase) {
                Log.d("OnCreate", "create table");
                String query = "CREATE TABLE " + Constants.NOMBRE_BASE_DATOS + "(email varchar(100), password varchar(100))";
                sqLiteDatabase.execSQL(query);
            }

            @Override
            public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

            }
        };
        sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
    }

    private void borrarDatabase() {
        context.deleteDatabase(Constants.NOMBRE_BASE_DATOS);
    }


    private void borrarUsuario() {

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = Constants.URL_USER + "/" + user.getId();
        final StringRequest request = new StringRequest(Request.Method.DELETE, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Toast toast = Toast.makeText(context,
                                R.string.profile_toast_usuario_borrado, Toast.LENGTH_LONG);
                        toast.show();
                        borrarDatabase();
                        goLogin(Login.class);

                    }

                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error: " + error.getMessage());
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("Api-Key", user.getApiKey());
                return headers;
            }

        };
        queue.add(request);
    }

    private void profileRequest() {

        String json = Json.crearJson(user);
        Log.d("json", json);
        Gson gson = new Gson();
        final Map body = gson.fromJson(json, Map.class);


        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT,
                Constants.URL_USER + "/" + user.getId(), new JSONObject(body),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        Log.d("Success", response.toString());
                        user = gson.fromJson(response.toString(), User.class);
                        Toast.makeText(context, R.string.profile_toast_cambios_actualizados, Toast.LENGTH_LONG).show();
                        cambiosGuardar = false;
                        cambiarImagenGuardar();


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error: " + error.getMessage());
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("Api-Key", user.getApiKey());
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = body;
                return params;
            }

        };
        queue.add(request);

    }

    public void cargarDatosUsuariosReales() {
        tituloCorreo.setText(user.getEmail());
        tvCorreoElectronicoEt.setText(user.getEmail());
        tvNombreUsuarioEt.setText(user.getUserName());
        tituloNombreCompleto.setText(user.getUserName());
        tvNombreEt.setText(user.getName());
        tvApellidosEt.setText(user.getSurname());
        tvPaisEt.setText(user.getCountry());
    }

    private void cambiarContrasena(String contrasenaAcual, String contrasenaNueva, String contrasenaRepetir) {
        //Hasheamos la contraseña actual
        String contrasenaHasheada = Hash.hashPassword(contrasenaAcual);
        //Contraseña actual correcta
        if (contrasenaHasheada.equalsIgnoreCase(user.getPassword())) {
            //Validamos contraseña nueva
            if (Validation.validarPassword(contrasenaNueva)) {
                //Vemos si la contraseña nueva y repetir contraseña son inguales
                if (contrasenaNueva.equalsIgnoreCase(contrasenaRepetir)) {
                    user.setPassword(contrasenaNueva);
                    profileRequest();
                } else {
                    Toast.makeText(context, R.string.perfil_toast_contrasena_repetir_incorrecta, Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, R.string.perfil_toast_contrasena_nueva_incorrecta, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, R.string.perfil_toast_contrasena_actual_incorrecta, Toast.LENGTH_SHORT).show();
        }
    }

    public void cambiarImagenGuardar() {
        if (cambiosGuardar == true) {
            ivMenuIconRight.setImageResource(R.drawable.icon_save_blue);
        } else {
            ivMenuIconRight.setImageResource(R.drawable.icon_save);
        }
    }


    private void inicializarMenu() {
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

    private void goLogin(Class goToView) {
        Intent in = new Intent(this, goToView);
        startActivity(in);
    }

    private void goToNewView(Class goToView, User user) {
        Intent in = new Intent(this, goToView);
        in.putExtra("user", user);
        startActivity(in);
    }
}
