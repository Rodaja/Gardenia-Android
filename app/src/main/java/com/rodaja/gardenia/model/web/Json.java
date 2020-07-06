package com.rodaja.gardenia.model.web;

import com.google.gson.Gson;
import com.rodaja.gardenia.model.entity.FlowerPot;
import com.rodaja.gardenia.model.entity.User;

public class Json {

    /**
     * Este metodo crea un JSON en funcion de los atributos de un objeto
     * @param objeto El objeto con los datos
     * @return Un String en formato JSON que contiene todos los atributos del objeto
     */
    public static String crearJson(Object objeto){
        Gson gson = new Gson();
        String json = gson.toJson(objeto);
        return json;
    }


    //TODO: Revisar
    /**
     * Este metodo transforma un JSON en un objeto generico, para poder utilizarlo se debe de castear a la clase que se quiera usar
     * @param json El JSON a convertir
     * @param clase La clase del objeto
     * @return Un objeto generico con los datos del JSON
     */
    public static Object leerJson(String json, String clase){
        Gson gson = new Gson();
        Object objeto = null;

       /*
        switch (clase.toLowerCase()) {
            case "user":
                objeto = gson.fromJson(json, User.class);
                break;
                case "login":
                objeto = gson.fromJson(json, Login.class);
                break;
                case "flowerpot":
                objeto = gson.fromJson(json, FlowerPot.class);
                break;
        }
        */
        return objeto;
    }
}
