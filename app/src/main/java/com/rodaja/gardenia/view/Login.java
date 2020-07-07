package com.rodaja.gardenia.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.rodaja.gardenia.R;
import com.rodaja.gardenia.model.configuration.Constants;
import com.rodaja.gardenia.model.entity.User;
import com.rodaja.gardenia.model.firebase.Authentication;
import com.rodaja.gardenia.model.navegation.Navegation;
import com.rodaja.gardenia.view.menu.NavegationDrawerActivity;
import com.rodaja.gardenia.view.multimedia.Image;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private Context context;

    private ImageView ivBackground;
    private Button btnLogin;
    private TextInputEditText etEmail;
    private TextInputEditText etPassword;
    private TextView tvSignUp, tvForgotPassword;
    private CheckBox chboxRecordarme;

    private String email;
    private String password;

    private SQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        inicializar();
        context = this;

        Image.setImage(this, R.drawable.background, ivBackground);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Obligatorio usar el trim ya que el texto aparece con un espacio al principio
                email = String.valueOf(etEmail.getText()).trim();
                password = String.valueOf(etPassword.getText()).trim();
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navegation.goToView(context, Signup.class);
            }
        });

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Enviar email con cambio de contraseña
            }
        });
    }

    private void inicializar() {
        ivBackground = findViewById(R.id.ivBackground);
        btnLogin = findViewById(R.id.btnLogin);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        tvSignUp = findViewById(R.id.tvSignUp);
        chboxRecordarme = findViewById(R.id.chboxRecordar);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);

    }

    @Override
    public void onBackPressed() {
        Intent in = new Intent(this, Login.class);
        startActivity(in);
    }

}
