package com.rodaja.gardenia.model.navegation;

import android.content.Context;
import android.content.Intent;

import com.rodaja.gardenia.model.entity.User;

public class Navegation {

    public static void goToView(Context context, Class activity) {
        Intent in = new Intent(context, activity);
        context.startActivity(in);
    }
}
