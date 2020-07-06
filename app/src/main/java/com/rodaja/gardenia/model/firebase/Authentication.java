package com.rodaja.gardenia.model.firebase;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Authentication {

    private static FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private static boolean response = false;

    /**
     * Este metodo sirve para realizar un login en Firebase
     * @param context La activity desde donde se inicia el metodo
     * @param email El email del usuario
     * @param password La contraseña del usuario
     * @return <b>True</b> si el login ha sido ejecutado correctamente, <b>false</b> en caso contrario
     */
    public static boolean signInNewUser(Activity context, String email, String password){
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("Sign in success", "signInWithEmail:success");
                            response = true;
                        } else {
                            Log.w("Sign in unsuccess", "signInWithEmail:failure", task.getException());
                            response = false;
                        }
                    }
                });
        return response;
    }

    /**
     * Este metodo sirve para dar de alta un usuario en Firebase a traves de un email y una contraseña
     * @param context La activity desde donde se inicia el metodo
     * @param email El email del usuario
     * @param password La contraseña del usuario
     * @return <b>True</b> si el sign up ha sido ejecutado correctamente, <b>false</b> en caso contrario
     */
    public static boolean signUpNewUser(Activity context, String email, String password){
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("Sign up success", "createUserWithEmail:success");
                            response = true;
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Sign up unsuccess", "createUserWithEmail:failure", task.getException());
                            response = false;
                        }

                    }
                });
        return response;
    }

    /**
     * Este metodo sirve para obtener el <b>ID</b> del usuario que esta actualmente logueado en la aplicación
     * @return El String con el <b>ID</b> del usuario
     */
    public static String getUserID(){
        FirebaseUser user = firebaseAuth.getCurrentUser();
        return user.getUid();
    }

    /**
     * Este metodo sirve para obtener el <b>nombre</b> del usuario que esta actualmente logueado en la aplicación
     * @return El String con el <b>name</b> del usuario
     */
    public static String getUserName(){
        FirebaseUser user = firebaseAuth.getCurrentUser();
        return user.getDisplayName();
    }

    /**
     * Este metodo sirve para obtener el <b>email</b> del usuario que esta actualmente logueado en la aplicación
     * @return El String con el <b>email</b> del usuario
     */
    public static String getUserEmail(){
        FirebaseUser user = firebaseAuth.getCurrentUser();
        return user.getEmail();
    }

    /**
     * Este metodo sirve para obtener la <b>URI de la foto</b> del usuario que esta actualmente logueado en la aplicación
     * @return La URI de la foto del usuario
     */
    public static Uri getUserPhotoUrl(){
        FirebaseUser user = firebaseAuth.getCurrentUser();
        return user.getPhotoUrl();
    }

    /**
     * Este metodo sirve para saber si el usuario esta <b>verificado</b>  en la aplicación
     * @return True si esta verificado, false en caso contrario
     */
    public static boolean getUserEmailVerification(){
        FirebaseUser user = firebaseAuth.getCurrentUser();
        return user.isEmailVerified();
    }

    /**
     * Este metodo sirve para comprobar si hay un usuario autenticado en la aplicación
     * @return True si esta autenticado, false en caso contrario
     */
    public static boolean checkUser() {
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Este metodo sirve para obtener el usuario autenticado de la aplicacion
     * @return El usuario autenticado
     */
    public static FirebaseUser getCurrentUser(){
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        return currentUser;
    }



}
