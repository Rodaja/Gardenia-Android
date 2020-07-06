package com.rodaja.gardenia.model.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RealTimeDatabase {

    private static FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    /**
     * Este metodo devuelve la referencia de la base de datos Real Time Database en funcion de un string pasado por parametro
     * @param reference El nombre de la referencia de la base de datos
     * @return La referencia de la base de datos
     */
    //TODO: Añadir comprobacion para saber si existe la referencia
    public static DatabaseReference getRealTimeDatabaseReference(String reference){
        DatabaseReference databaseReference = firebaseDatabase.getReference(reference);
        return databaseReference;
    }

}
