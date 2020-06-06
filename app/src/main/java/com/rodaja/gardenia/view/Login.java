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
import com.rodaja.gardenia.view.multimedia.Image;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private Context contexto;

    private ImageView ivBackground;
    private Button btnLogin;
    private TextInputEditText etEmail;
    private TextInputEditText etPassword;
    private TextView tvSignUp, tvForgotPassword;
    private CheckBox chboxRecordarme;
    private User user;

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
        contexto = this;

        Image.setImage(this, R.drawable.background, ivBackground);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Obligatorio usar el trim ya que el texto aparece con un espacio al principio
                email = String.valueOf(etEmail.getText()).trim();
                password = String.valueOf(etPassword.getText()).trim();

                loginRequest(Constants.URL_LOGIN, email, password);
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToNewView(null, Signup.class);
            }
        });

        setupDataBase();
        if (userCheckLogin()) {
            loginRequest(Constants.URL_LOGIN, email, password);
        }

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToNewView(null, ForgotPassword.class);
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

    private void loginRequest(String url, String email, String password) {

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
                        user = gson.fromJson(response.toString(), User.class);

                        if (chboxRecordarme.isChecked()) {
                            Log.d("Recuerdame", "Recuerdame seleccionado");
                            guardarDatosUsuario(user);
                            Log.d("Datos de usario", "Datos usuario guardados");
                        }
                                Toast toast = Toast.makeText(contexto,getString(R.string.toast_bienvenido) + " " + user.getEmail(), Toast.LENGTH_LONG);
                        toast.show();

                        goToHome(Home.class, user);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast = Toast.makeText(contexto,
                        getString(R.string.login_error), Toast.LENGTH_LONG);
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

    private void goToHome(Class goToView, User user) {
        Intent in = new Intent(this, goToView);
        in.putExtra("user", user);
        startActivity(in);
    }

    private void goToNewView(View view, Class goToView) {
        Intent in = new Intent(this, goToView);
        startActivity(in);
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

    private boolean userCheckLogin() {
        //Query
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + Constants.NOMBRE_BASE_DATOS, null);

        if (cursor.moveToNext()) {
            email = cursor.getString(0);
            Log.d("Email guardado", email);
            password = cursor.getString(1);
            Log.d("Password guardado", password);
            return true;
        }

        return false;
    }

    private void guardarDatosUsuario(User user) {
        ContentValues valores = new ContentValues();
        valores.put("email", user.getEmail());
        valores.put("password", password);
        sqLiteDatabase.insert(Constants.NOMBRE_BASE_DATOS, null, valores);
    }
}
