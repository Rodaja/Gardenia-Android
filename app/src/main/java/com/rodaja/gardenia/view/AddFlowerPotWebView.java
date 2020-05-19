package com.rodaja.gardenia.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.rodaja.gardenia.R;
import com.rodaja.gardenia.model.configuration.Constants;
import com.rodaja.gardenia.model.configuration.Permissions;
import com.rodaja.gardenia.model.entity.User;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddFlowerPotWebView extends AppCompatActivity {

    //Atributos Menu
    private TextView tvTitulo;
    private ImageView ivMenuIconLeft;
    private ImageView ivMenuIconRight;

    private Context context;
    private User user;

    private WebView webView;
    private String macAddress;
    private WifiManager wifi;
    private ConnectivityManager cm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flower_pot_web_view);

        inicializarMenu();
        context = this;

        user = getUser();


        ivMenuIconLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToView(AddFlowerPot.class, user);
            }
        });

        ivMenuIconRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Revisar
                String bssid = wifi.getConnectionInfo().getBSSID();
                if (bssid != null) {
                    if (bssid.equalsIgnoreCase(macAddress)) {
                        Log.d("comparacion", "True");
                        Toast.makeText(context, R.string.addflowerpotview_toast_wifi_habitual, Toast.LENGTH_LONG);
                    } else {
                        cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

                        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                        boolean isConnected = activeNetwork != null &&
                                activeNetwork.isConnected();

                        if (isConnected) {
                            userRequest(macAddress, user.getId());
                        }
                    }
                }
            }
        });

        setWebView();
        getMacAddress();


    }

    private User getUser() {
        Intent in = getIntent();
        return (User) in.getSerializableExtra("user");
    }

    private void userRequest(String macAddress, int userId) {

        final Map<String, String> body = new HashMap<String, String>();

        body.put("macAddress", macAddress.toUpperCase());
        Log.d("MAC", macAddress.toUpperCase());

        RequestQueue queue = Volley.newRequestQueue(context);

        String url = Constants.URL_USER + "/" + userId + Constants.URL_FLOWERPOT_ROOT;
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
                        R.string.addflowerpotview_toast_maceta_no_anadida, Toast.LENGTH_LONG);
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

    private String getMacAddress() {

        if (Permissions.checkPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != true) {
            Permissions.askForPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Permissions.REQUEST_ACCESS_FINE_LOCATION);
            getMacAddress();
        } else {
            wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            macAddress = String.valueOf(wifi.getConnectionInfo().getBSSID());
        }

        return macAddress;
    }

    private void setWebView() {
        WebView webView = new WebView(context);
        setContentView(webView);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient());

        webView.loadUrl(Constants.URL_ADD_POT);
    }

    private void inicializarMenu() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.menu);

        tvTitulo = findViewById(R.id.tvMenuTitulo);
        ivMenuIconLeft = findViewById(R.id.ivMenuIconLeft);
        ivMenuIconRight = findViewById(R.id.ivMenuIconRight);

        tvTitulo.setText(R.string.conectar_maceta);
        ivMenuIconRight.setImageResource(R.drawable.icon_save);

        webView = findViewById(R.id.wvAddFlowerpot);

    }


}
