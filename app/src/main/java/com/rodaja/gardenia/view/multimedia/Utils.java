package com.rodaja.gardenia.view.multimedia;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.pixplicity.sharp.Sharp;
import com.rodaja.gardenia.R;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Utils {
    private static OkHttpClient httpClient;
    private static Context c;

    public static void fetchSvg(Context context, String url, final ImageView target) {
        c = context;
        if (httpClient == null) {
            // Use cache for performance and basic offline capability
            httpClient = new OkHttpClient.Builder()
                    .cache(new Cache(context.getCacheDir(), 5 * 24 * 48))
                    .build();
        }

        Request request = new Request.Builder().url(url).build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Drawable d = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    d = target.getResources().getDrawable(R.drawable.buscar,null);
                }
                target.setImageDrawable(d);
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                    InputStream stream = response.body().byteStream();
                    try {
                            Sharp.loadInputStream(stream).into(target);
                }catch (IllegalArgumentException e){
                    e.printStackTrace();
                }
                stream.close();

            }
        });
    }
}
