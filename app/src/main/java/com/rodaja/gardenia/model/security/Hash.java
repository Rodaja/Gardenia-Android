package com.rodaja.gardenia.model.security;

import android.annotation.SuppressLint;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class Hash {
    /**
     * This method hash the user password using SHA256
     * Este metodo hashea la contraseña de un usuario usando SHA256
     *
     * @param password La contraseña a hashear
     * @return La contraseña hasheada
     */
    @SuppressLint("NewApi")
    public static String hashPassword(String password) {
        return Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
    }
}
