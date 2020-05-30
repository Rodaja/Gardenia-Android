package com.rodaja.gardenia.model.web;

import com.google.gson.Gson;

import org.junit.Test;

import static org.junit.Assert.*;

public class JsonTest {
    String jsonDePrueba = "{\n" +
            "    \"id\": 1,\n" +
            "    \"email\": \"gardenia@gmail.com\",\n" +
            "    \"userName\": \"Gardenia\",\n" +
            "    \"name\": \"gar\",\n" +
            "    \"surname\": \"roncia\",\n" +
            "    \"password\": \"03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4\",\n" +
            "    \"country\": \"Brasil\",\n" +
            "    \"apiKey\": \"SSFNTFCFXQUVAXZWMOKOBJKPBTMXLJ\",\n" +
            "    \"temperature\": \"celsius\",\n" +
            "    \"theme\": \"light\",\n" +
            "    \"notifications\": false,\n" +
            "    \"listFlowerPots\": [\n" +
            "        {\n" +
            "            \"macAddress\": \"E2:98:06:85:D6:A3\",\n" +
            "            \"version\": null,\n" +
            "            \"name\": \"Gardenia\",\n" +
            "            \"imageUrl\": \"/storage/emulated/0/Download/logo.png\",\n" +
            "            \"groundHumidity\": 158,\n" +
            "            \"airHumidity\": 2147483647,\n" +
            "            \"airTemperature\": 2147483647,\n" +
            "            \"water\": false\n" +
            "        },\n" +
            "        {\n" +
            "            \"macAddress\": \"26:62:AB:0A:FD:21\",\n" +
            "            \"version\": null,\n" +
            "            \"name\": \"Mesita salon\",\n" +
            "            \"imageUrl\": \"\",\n" +
            "            \"groundHumidity\": 28,\n" +
            "            \"airHumidity\": 44,\n" +
            "            \"airTemperature\": 24,\n" +
            "            \"water\": false\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    private static String nuevoJson(Object objeto) {
        Gson gson = new Gson();
        String json = gson.toJson(objeto);
        return json;
    }

    @Test
    public void crearJson() {
        assertEquals(nuevoJson(jsonDePrueba), Json.crearJson(jsonDePrueba));

    }

}