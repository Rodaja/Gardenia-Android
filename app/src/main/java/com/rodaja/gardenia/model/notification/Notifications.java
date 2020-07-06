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
    public static boolean generateSnackBar(Context context) {
        /**
         *

         final ConstraintLayout constraintLayout = findViewById(R.id.constLAddMaceta);
         Snackbar snackbar = Snackbar
         .make(constraintLayout, R.string.wifi_actualizada, Snackbar.LENGTH_LONG);
         View mView = snackbar.getView();
         // get textview inside snackbar view
         TextView mTextView = (TextView) mView.findViewById(R.id.snackbar_text);

         // set text to center
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
         mTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
         else
         mTextView.setGravity(Gravity.CENTER_HORIZONTAL);

         snackbar.show();
         */
        //REVISAR
        return true;
    }
}
