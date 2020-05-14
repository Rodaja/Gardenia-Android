package com.rodaja.gardenia.model.validation;

public class Validation {
    //Validamos que el email tenga @ y .
    public static boolean validarEmail(String email) {
        boolean esEmail = false;

        esEmail = email.contains("@") && email.contains(".") ? true : false;

        return esEmail;
    }

    //Validamos que el password no este vacio o sea null
    public static boolean validarPassword(String password) {
        boolean esPassword = false;
        esPassword = password.equals(null) || password.equals("") ? false : true;
        return esPassword;
    }

}
