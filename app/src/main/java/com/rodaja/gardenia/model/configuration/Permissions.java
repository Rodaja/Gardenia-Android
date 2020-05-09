package com.rodaja.gardenia.model.configuration;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Permissions {

    public static final int REQUEST_ACCESS_FINE_LOCATION = 1;

    public static void askForPermissions(Activity activity, String[] permisoSolicitado, Integer codigoPermiso) {
        ActivityCompat.requestPermissions(activity,
                permisoSolicitado,
                codigoPermiso);
    }

    public static boolean checkPermission(Context contexto, String permiso) {
        if (ContextCompat.checkSelfPermission(contexto, permiso)
                != PackageManager.PERMISSION_GRANTED) {
            // Permiso denegado
            return false;
        } else {
            return true;
        }

    }
}
