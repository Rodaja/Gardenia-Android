package com.rodaja.gardenia.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
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

                userRequest(getMacAddress(), user.getId());
                goToView(Home.class, user);
            }
        });

        setWebView();


    }

    private User getUser() {
        Intent in = getIntent();
        return (User) in.getSerializableExtra("user");
    }

    private void userRequest(String macAddress, int userId) {

        final Map<String, String> body = new HashMap<String, String>();

        body.put("macAddress", macAddress);

        RequestQueue queue = Volley.newRequestQueue(context);

        String url = Constants.URL_USER + userId + Constants.URL_FLOWERPOT_ROOT;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT,
                url, new JSONObject(body),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        Log.d("Success", response.toString());
                        user = gson.fromJson(response.toString(), User.class);

                        Toast toast = Toast.makeText(context,
                                "Maceta añadida " + user.getEmail(), Toast.LENGTH_LONG);
                        toast.show();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast = Toast.makeText(context,
                        "Maceta no añadida " , Toast.LENGTH_LONG);
                toast.show();
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

    private void goToView(Class goToView, User user) {
        Intent in = new Intent(this, goToView);
        in.putExtra("user", user);
        startActivity(in);
    }

    private String getMacAddress() {
        String macAddress = "";

        if (Permissions.checkPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != true){
            Permissions.askForPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Permissions.REQUEST_ACCESS_FINE_LOCATION);
            getMacAddress();
        } else{
            WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
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
