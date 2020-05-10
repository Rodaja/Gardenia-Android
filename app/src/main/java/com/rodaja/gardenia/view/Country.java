package com.rodaja.gardenia.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.rodaja.gardenia.R;
import com.rodaja.gardenia.model.adapter.CountryAdapter;
import com.rodaja.gardenia.model.configuration.Constants;
import com.rodaja.gardenia.model.entity.Pais;
import com.rodaja.gardenia.model.entity.User;
import com.rodaja.gardenia.model.web.Json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Country extends AppCompatActivity {
    //Atributos Menu
    private TextView tvTitulo;
    private ImageView ivMenuIconLeft;
    private ImageView ivMenuIconRight;
    //
    private EditText et_buscador;
    //
    private  LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private CountryAdapter adapter;
    public RequestQueue queue;
    private List<Pais> paises  = new ArrayList<>() ;



    public static String pais_seleccionado = "";
    private Context context;
    //
    private User user;
    private String macAddress;
    private WifiManager wifi;
    private ConnectivityManager cm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);
        inicializarMenu();
        context = this;
        queue = Volley.newRequestQueue(this);
        user = getUser();
        String url = "https://restcountries.eu/rest/v2/all";
//      String url = "https://restcountries.eu/rest/v2/name/japan";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                recyclerView.setLayoutManager(linearLayoutManager );
                try {

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject mjsonObject = response.getJSONObject(i);
                        String siglas = mjsonObject.getString("alpha3Code");
                        int poblacion = mjsonObject.getInt("population");
                        JSONObject traduccion = mjsonObject.getJSONObject("translations");
                        String nombre = traduccion.getString("es");
                        if (nombre.contains("República")){
                            nombre = nombre_abreviado(nombre);
                        }
                        String foto = mjsonObject.getString("flag");
                        if (nombre.length()<21 && poblacion > 10000000  ){
                            paises.add(new Pais(nombre,siglas, foto));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter = new CountryAdapter( context, paises);
                recyclerView.setAdapter(adapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(request);
        ivMenuIconLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewView(v,Profile.class);
            }
        });
        ivMenuIconRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goToNewView(v,Profile.class);
            }
        });






        et_buscador.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_NEXT) {
                    String nombre = String.valueOf(et_buscador.getText());
                    if (nombre.length()>2) {
                        peticion_nombre(nombre);
                        handled = true;
                    }else{
                        Toast.makeText(context,"Necesitas mínimo caracteres para buscar",Toast.LENGTH_LONG).show();
                    }
                }
                return handled;
            }
        });

        ivMenuIconRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                        boolean isConnected = activeNetwork != null &&
                                activeNetwork.isConnected();
                        if(isConnected){
                            userRequest(user.getId());
                        }else{
                            Toast.makeText(context,"No se ha podido conectar",Toast.LENGTH_LONG).show();
                        }
            }
        });



    }

    private void inicializarMenu() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.menu);

        tvTitulo  = findViewById(R.id.tvMenuTitulo);
        ivMenuIconLeft = findViewById(R.id.ivMenuIconLeft);
        ivMenuIconRight = findViewById(R.id.ivMenuIconRight);
        tvTitulo.setText(R.string.paises);
        ivMenuIconRight.setImageResource(R.drawable.icon_save);
        et_buscador = (EditText)findViewById(R.id.editText_buscador);

        //Adaptador Recycler
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        //
    }

    private void goToNewView(View view , Class goToView){
        Intent in = new Intent(this, goToView);
        startActivity(in);
    }



    public void peticion_nombre(final String nombre_pais) {
        String url = "https://restcountries.eu/rest/v2/name/" + nombre_pais;
//      String url = "https://restcountries.eu/rest/v2/name/japan";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                recyclerView.setLayoutManager(linearLayoutManager);
                try {
                    paises.clear();
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject mjsonObject = response.getJSONObject(i);
                        String siglas = mjsonObject.getString("alpha3Code");
                        int poblacion = mjsonObject.getInt("population");
                        JSONObject traduccion = mjsonObject.getJSONObject("translations");
                        String nombre = traduccion.getString("es");
                        if (nombre.contains(" ")){
                            nombre = nombre_abreviado(nombre);
                        }
                        String foto = mjsonObject.getString("flag");
                        if (nombre.length() < 30 && poblacion > 70000) {
                            paises.add(new Pais(nombre, siglas, foto));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter = new CountryAdapter(context, paises);
                recyclerView.setAdapter(adapter);


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(request);


    }


    public String nombre_abreviado(String nombre){
        String nombre_final = "";
        if (nombre.contains(" ")){
            String nombre_largo [] = nombre.split(" ");
            String inicial = nombre_largo[0].substring(0,1);
            nombre_final = inicial+"."+nombre_largo[1];
        }

        return   nombre_final;

    }

    private User getUser() {
        Intent in = getIntent();
        return (User) in.getSerializableExtra("user");
    }

    private void userRequest(int userId) {

        user.setCountry(pais_seleccionado);
        String json = Json.crearJson(user);
        Log.d("json",json);
        Gson gson = new Gson();
        final Map body = gson.fromJson(json, Map.class);
        final RequestQueue queue = Volley.newRequestQueue(context);
        final String url = Constants.URL_USER + "/" + userId ;
       // String url = "http://url/api/users/" + userId;
        Log.d("URL", url);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT,
                url, new JSONObject(body),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        Log.d("Success", response.toString());
                        user = gson.fromJson(response.toString(), User.class);
                        Log.d("Añadir maceta", "Maceta añadida con exito");
                        goToView(Home.class, user);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast = Toast.makeText(context,
                        "Pais no se ha cambiado " , Toast.LENGTH_LONG);
                toast.show();
                VolleyLog.d("Error: " + error.getMessage());
                Log.d("Error", "Ha habido un error");
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

    private void goToView(Class goToView, User user) {
        Intent in = new Intent(this, goToView);
        in.putExtra("user", user);
        startActivity(in);
    }


}
