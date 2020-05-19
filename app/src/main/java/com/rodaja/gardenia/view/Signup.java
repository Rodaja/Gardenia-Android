package com.rodaja.gardenia.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.rodaja.gardenia.R;
import com.rodaja.gardenia.model.configuration.Constants;
import com.rodaja.gardenia.model.entity.User;
import com.rodaja.gardenia.model.validation.Validation;
import com.rodaja.gardenia.view.multimedia.Image;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {

    private Context contexto;

    private ImageView ivBackground;
    private Button btnSignUp;
    private TextInputEditText etEmail;
    private TextInputEditText etPassword;
    private TextInputEditText etRepeatPassword;
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();

        inicializar();
        contexto = this;

        Image.setImage(this, R.drawable.background, ivBackground);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Obligatorio usar el trim ya que el texto aparece con un espacio al principio
                String email = String.valueOf(etEmail.getText()).trim();
                String password = String.valueOf(etPassword.getText()).trim();

                if (validaciones(email, password)) {
                    signUpRequest(Constants.URL_USER, email, password);
                }

            }
        });

    }

    private boolean validaciones(String email, String password) {
        if (validarEmail(email) && validarPassword(password)) {
            return true;
        } else {
            Toast.makeText(contexto, "Email o contraseña no validos", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    private boolean validarEmail(String email) {
        if (Validation.validarEmail(email)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean validarPassword(String password) {
        if (Validation.validarPassword(password)) {
            return true;
        } else {
            return false;
        }
    }

    private void inicializar() {
        ivBackground = findViewById(R.id.ivBackground);
        btnSignUp = findViewById(R.id.btnSignUp);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etRepeatPassword = findViewById(R.id.etPasswordRepeat);
        ivBack = findViewById(R.id.ivBack);
    }

    private void signUpRequest(String url, String email, String password) {

        final Map<String, String> body = new HashMap<String, String>();

        body.put("email", email);
        body.put("password", password);


        RequestQueue queue = Volley.newRequestQueue(contexto);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                url, new JSONObject(body),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        Log.d("Success", response.toString());
                        User user = gson.fromJson(response.toString(), User.class);

                        Toast toast = Toast.makeText(contexto,
                                R.string.signup_toast_binevenido + " " + user.getName(), Toast.LENGTH_LONG);
                        toast.show();

                        goTo(Home.class, user);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error: " + error.getMessage());
                Toast toast = Toast.makeText(contexto,
                        R.string.signup_toast_correo_en_uso, Toast.LENGTH_LONG);
                toast.show();
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

    private void goTo(Class goToView, User user) {
        Intent in = new Intent(this, goToView);
        in.putExtra("user", user);
        startActivity(in);
    }
}
