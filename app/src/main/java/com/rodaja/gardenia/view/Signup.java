package com.rodaja.gardenia.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.rodaja.gardenia.R;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();
    }
}
