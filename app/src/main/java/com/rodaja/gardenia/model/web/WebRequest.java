package com.rodaja.gardenia.model.web;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class WebRequest {

    private static String result;

    public static String getRequestNoHeader(Context contexto, String url) {

        RequestQueue queue = Volley.newRequestQueue(contexto);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("Ok", response);
                        result = response;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", "No ha funcionado");
                result = error.toString();
            }
        });

        queue.add(stringRequest);
        return result;

    }

    //Con headers
    public static String getRequestWithHeader(Context contexto, String url, final HashMap headers) {

        RequestQueue queue = Volley.newRequestQueue(contexto);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("Ok", response);
                        result = response;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", "No ha funcionado");
                result = error.toString();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers;
            }
        };

        queue.add(stringRequest);
        return result;

    }
}
