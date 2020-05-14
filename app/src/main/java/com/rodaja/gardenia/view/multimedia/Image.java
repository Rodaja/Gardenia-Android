package com.rodaja.gardenia.view.multimedia;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

public class Image {

    /**
     * Este metodo añade una imagen (R.drawable.*) a una vista
     * @param context El contexto de la aplicación
     * @param resourceId El recurso de la imagen
     * @param imageView La vista donde se va a incluir la imagen
     */
    public static void setImage(Context context, Integer resourceId, ImageView imageView) {
        Glide.with(context).load(resourceId).apply(new RequestOptions().centerCrop()).into(imageView);
    }

    /**
     * Este metodo añade un GIF a una vista
     * @param context El contexto de la aplicación
     * @param resourceId El recurso del GIF
     * @param imageView La vista donde se va a incluir la imagen
     */
    public static void setGif(Context context, Integer resourceId, ImageView imageView){
        Glide.with(context).asGif().load(resourceId).apply(new RequestOptions().centerCrop()).into(imageView);
    }

    /**
     * Este metodo añade una imagen con los bordes redondeados a una vista
     * @param context El contexto de la aplicación
     * @param resourceId El recurso de la imagen
     * @param imageView La vista donde se va a incluir la imagen
     */
    public static void setImageRoundedCorners(Context context, Integer resourceId, ImageView imageView, int radius){
        Glide.with(context).load(resourceId).transform(new MultiTransformation(new CenterCrop(), new RoundedCorners(radius))).into(imageView);
    }
}
