package com.rodaja.gardenia.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import com.rodaja.gardenia.model.configuration.Constants;
import com.rodaja.gardenia.model.entity.FlowerPot;
import com.rodaja.gardenia.model.entity.User;
import com.rodaja.gardenia.view.multimedia.Image;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.List;

public class Details extends AppCompatActivity {

    //Atributos Menu
    private TextView tvTitulo;
    private ImageView ivMenuIconLeft;
    private ImageView ivMenuIconRight;
    private TextView tv_titulo_detalle_maceta, tv_humedad_tierra_medida, tv_humedad_ambiental_medida, tv_temperatura_ambiental_medida2;
    private User user;
    private ImageView ivDetails, ivChoose_plant, ivDeleteFlowerpot, ivChangeName;
    private FlowerPot maceta;
    private Context contexto;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Button btnRegar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        maceta = getExtras();
        inicializarMenu();
        contexto = this;
        setearValores();

        Image.setImageRoundedCorners(this, R.drawable.detalles_principal, ivDetails, 25);

        ivChoose_plant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToChooseImage(v);
            }
        });

        ivMenuIconLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewView(Home.class, user);
            }
        });
        ivMenuIconRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewView(AddFlowerPot.class, user);
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
                MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(contexto);
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
                        deleteFlowerpotRequest();
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
                regarRequest();
            }
        });

    }

    private void regarRequest() {
        String macAddress = maceta.getMacAddress();
        Log.d("Mac", macAddress.toString());
        final Map<String, String> body = new HashMap<String, String>();

        body.put("macAddress", macAddress);
        body.put("water", String.valueOf(true));

        RequestQueue queue = Volley.newRequestQueue(contexto);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT,
                Constants.URL_FLOWERPOT + "/" + macAddress, new JSONObject(body),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        Log.d("Success", response.toString());
                        maceta = gson.fromJson(response.toString(), FlowerPot.class);

                        Toast toast = Toast.makeText(contexto,
                                "Planta regandose", Toast.LENGTH_LONG);
                        toast.show();
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

    protected SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            updateFlowerpotRequest();
            setearValores();
            swipeRefreshLayout.setRefreshing(false);
        }
    };


    private void updateFlowerpotRequest() {
        RequestQueue queue = Volley.newRequestQueue(contexto);

        StringRequest request = new StringRequest(Request.Method.GET,
                Constants.URL_FLOWERPOT + "/" + maceta.getMacAddress(),
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        Log.d("Success", response.toString());
                        maceta = gson.fromJson(response.toString(), FlowerPot.class);

                        Toast toast = Toast.makeText(contexto,
                                "Maceta actualizada", Toast.LENGTH_LONG);
                        toast.show();

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


    private void deleteFlowerpotRequest() {
        RequestQueue queue = Volley.newRequestQueue(contexto);

        String url = Constants.URL_USER + "/" + user.getId() + Constants.URL_FLOWERPOT_ROOT + "/" + maceta.getMacAddress();
        final StringRequest request = new StringRequest(Request.Method.DELETE, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Toast toast = Toast.makeText(contexto,
                                "Maceta Borrada", Toast.LENGTH_LONG);
                        toast.show();
                        requestUpdateUser(Home.class);
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

    private void showAlertDialog() {
        MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(contexto);
        //Seteamos el titulo
        dialog.setTitle(R.string.cambiar_nombre_maceta);

        dialog.setMessage(R.string.cambiar_nombre_maceta_texto);

        LinearLayout layout = new LinearLayout(contexto);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(65, 24, 65, 24);
        final EditText etNombreMaceta = new EditText(contexto);
        etNombreMaceta.setHint(tv_titulo_detalle_maceta.getText().toString());
        layout.addView(etNombreMaceta);
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
                String newName = etNombreMaceta.getText().toString();
                Log.d("Borrar", "Has seleccionado aceptar");
                requestModifyFlowerpot(newName);
                tv_titulo_detalle_maceta.setText(newName);
                requestUpdateUser(null);
            }
        });
        //Mostramos el cuadro de dialogo
        dialog.show();
    }

    private void requestUpdateUser(final Class goTo) {
        RequestQueue queue = Volley.newRequestQueue(contexto);

        StringRequest request = new StringRequest(Request.Method.GET,
                Constants.URL_USER + "/" + user.getId(),
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        Log.d("Success", response.toString());
                        user = gson.fromJson(response.toString(), User.class);

                        Toast toast = Toast.makeText(contexto,
                                "Usuario actualizado", Toast.LENGTH_LONG);
                        toast.show();

                        if (goTo != null) {
                            goToNewView(goTo, user);
                        }
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


    private void requestModifyFlowerpot(String name) {
        String macAddress = maceta.getMacAddress();
        final Map<String, String> body = new HashMap<String, String>();

        body.put("macAdress", macAddress);
        body.put("name", name);


        RequestQueue queue = Volley.newRequestQueue(contexto);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT,
                Constants.URL_FLOWERPOT + "/" + macAddress, new JSONObject(body),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        Log.d("Success", response.toString());
                        User user = gson.fromJson(response.toString(), User.class);

                        Toast toast = Toast.makeText(contexto,
                                "Nombre cambiado", Toast.LENGTH_LONG);
                        toast.show();
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

    private FlowerPot getExtras() {
        Intent in = getIntent();
        int numeroMacetas = (int) in.getSerializableExtra("numeroMaceta");
        user = (User) in.getSerializableExtra("user");
        //Coge la maceta seleccionada
        FlowerPot maceta = user.getListFlowerPots().get(numeroMacetas);
        return maceta;
    }


    private void setearValores() {
        tv_humedad_tierra_medida.setText(String.valueOf(maceta.getGroundHumidity()) + "%");
        tv_temperatura_ambiental_medida2.setText(String.valueOf(maceta.getAirTemperature()) + " ºC");
        tv_humedad_ambiental_medida.setText(String.valueOf(maceta.getAirHumidity()) + "%");
        tv_titulo_detalle_maceta.setText(String.valueOf(maceta.getName()));

    }

    private void goToChooseImage(View view) {
        Intent in = new Intent(this, ChooseImage.class);
        startActivity(in);
    }

    private void inicializarMenu() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.menu);

        ivDetails = findViewById(R.id.img_planta_1);
        ivChoose_plant = findViewById(R.id.img_elegir_imagen);

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
    }

    private void goToNewView(Class goToView, User user) {
        Intent in = new Intent(this, goToView);
        in.putExtra("user", user);
        startActivity(in);
    }


}
