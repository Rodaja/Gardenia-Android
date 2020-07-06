package com.rodaja.gardenia.model.configuration;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;

import androidx.core.content.ContextCompat;

import com.rodaja.gardenia.R;
import com.rodaja.gardenia.model.entity.User;

public class Configuration {
    public static boolean notifications = false;
    //0=Celsius,1=Kelvin,2=Farhenheit
    public static int temperature = 0;
    //0=Claro,1=Oscuro
    public static String strTema;


    public static User defaultConfiguration(User user) {
        user.setTemperature("celsius");
        user.setNotifications(false);
        user.setTheme("light");

        return user;
    }

    public static String getTemperatureUnit(User user) {
        String response = " ºC";

        if (user.getTemperature().equalsIgnoreCase("kelvin")) {
            response = " ºK";
        } else if (user.getTemperature().equalsIgnoreCase("fahrenheit")) {
            response = " ºF";
        }

        return response;
    }


    public static int getTemperatureValue(User user, int temperature) {
        int response = temperature;

        if (user.getTemperature().equalsIgnoreCase("kelvin")) {
            response = temperature + 273;
        } else if (user.getTemperature().equalsIgnoreCase("fahrenheit")) {
            response = (int) Math.round(temperature * 1.8 + 32);
        }

        return response;
    }

    public static String getTemperatureString(int temperature) {
        String response = "celsius";

        if (temperature == 0) {
            response = "celsius";
        } else if (temperature == 1) {
            response = "kelvin";
        } else {
            response = "fahrenheit";
        }

        return response;
    }

    public static int getTema(User user) {
        int tema = 0;
        if (user.getTheme().equals("light")) {
            tema = R.string.settings_tema_claro;
        } else {
            tema = R.string.settings_tema_oscuro;
        }
        return tema;
    }

    public static void cambiarColorShapeDark(Drawable backgroundConstLAddMaceta, Context c) {
        if (backgroundConstLAddMaceta instanceof ShapeDrawable) {
            ShapeDrawable shapeDrawable = (ShapeDrawable) backgroundConstLAddMaceta;
            shapeDrawable.getPaint().setColor(ContextCompat.getColor(c, R.color.colorConstraintDark));
        } else if (backgroundConstLAddMaceta instanceof GradientDrawable) {
            GradientDrawable gradientDrawable = (GradientDrawable) backgroundConstLAddMaceta;
            gradientDrawable.setColor(ContextCompat.getColor(c, R.color.colorConstraintDark));
        } else if (backgroundConstLAddMaceta instanceof ColorDrawable) {
            ColorDrawable colorDrawable = (ColorDrawable) backgroundConstLAddMaceta;
            colorDrawable.setColor(ContextCompat.getColor(c, R.color.colorConstraintDark));
        }
    }

    public static void cambiarColorShapeWhite(Drawable backgroundConstLAddMaceta, Context c) {
        if (backgroundConstLAddMaceta instanceof ShapeDrawable) {
            ShapeDrawable shapeDrawable = (ShapeDrawable) backgroundConstLAddMaceta;
            shapeDrawable.getPaint().setColor(ContextCompat.getColor(c, R.color.colorWhite));
        } else if (backgroundConstLAddMaceta instanceof GradientDrawable) {
            GradientDrawable gradientDrawable = (GradientDrawable) backgroundConstLAddMaceta;
            gradientDrawable.setColor(ContextCompat.getColor(c, R.color.colorWhite));
        } else if (backgroundConstLAddMaceta instanceof ColorDrawable) {
            ColorDrawable colorDrawable = (ColorDrawable) backgroundConstLAddMaceta;
            colorDrawable.setColor(ContextCompat.getColor(c, R.color.colorWhite));
        }
    }

    public static void verificarTemaDark(Context context) {
        if (strTema.equalsIgnoreCase("dark")) {
            context.setTheme(R.style.Theme_App_Dark);
        }
    }
}


