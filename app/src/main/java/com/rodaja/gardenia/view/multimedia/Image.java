package com.rodaja.gardenia.view.multimedia;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;

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

    /**
     * Este metodo añade una imagen a partir de una URI a una vista seleccionada con las esquinas redondeadas
     * @param context El contexto de la aplicación
     * @param imagePath La ruta del archivo que queremos cargar
     * @param imageView La vista donde queremos cargar la imagen
     */

    public static void setUriImage(Context context, String imagePath, ImageView imageView) {
        Glide.with(context).load(new File(imagePath)).apply(new RequestOptions().centerCrop()).into(imageView);
    }

    /**
     * Este metodo añade una imagen a partir de una URI a una vista seleccionada con las esquinas redondeadas
     * @param context El contexto de la aplicación
     * @param imagePath La ruta del archivo que queremos cargar
     * @param imageView La vista donde queremos cargar la imagen
     */
    public static void setUriImageRoundedCorners(Context context, String imagePath, ImageView imageView, int radius) {
        Glide.with(context).load(new File(imagePath)).transform(new MultiTransformation(new CenterCrop(), new RoundedCorners(radius))).into(imageView);
    }

}
