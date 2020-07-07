package com.rodaja.gardenia.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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
import com.rodaja.gardenia.model.firebase.Authentication;
import com.rodaja.gardenia.model.navegation.Navegation;
import com.rodaja.gardenia.model.validation.Validation;
import com.rodaja.gardenia.view.multimedia.Image;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {

    private Context context;

    private ImageView ivBackground, ivBack;
    private Button btnSignUp;
    private TextInputEditText etEmail;
    private TextInputEditText etPassword;
    private TextInputEditText etRepeatPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();
        inicializar();
        context = this;

        Image.setImage(this, R.drawable.background, ivBackground);

        //TODO: Revisar
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Obligatorio usar el trim ya que el texto aparece con un espacio al principio
                String email = String.valueOf(etEmail.getText()).trim();
                String password = String.valueOf(etPassword.getText()).trim();

                if (validaciones(email, password)) {
                    Authentication.signUpNewUser((Activity) context, email, password);
                }

            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navegation.goToView(context, Login.class);
            }
        });

    }

    private void inicializar() {
        ivBackground = findViewById(R.id.ivBackground);
        btnSignUp = findViewById(R.id.btnSignUp);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etRepeatPassword = findViewById(R.id.etPasswordRepeat);
        ivBack = findViewById(R.id.ivBack);
    }


    private boolean validaciones(String email, String password) {
        if (validarEmail(email) && validarPassword(password)) {
            return true;
        } else {
            Toast.makeText(this, "Email o contraseña no validos", Toast.LENGTH_SHORT).show();
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
}
