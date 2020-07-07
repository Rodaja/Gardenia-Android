package com.rodaja.gardenia.model.notification;

import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;
import com.rodaja.gardenia.R;

public class Notifications {

    //TODO: Revisar metodo
    public static boolean generateSnackBar(Context context, int stringId, View view) {
        final Snackbar snackBar = Snackbar.make(view, stringId, Snackbar.LENGTH_LONG);
        snackBar.setActionTextColor(context.getResources().getColor(R.color.colorAccent))
                .setAction(R.string.snack_bar_close, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        snackBar.dismiss();
                    }
                })
                .show();
        return true;
    }
}
