package com.rodaja.gardenia.model.configuration;

public class Settings {
    public static boolean notifications = false;
    public static String  temperature = "celsius";


    public void default_status(){
        notifications  = false;
        temperature = "celsius";
    }

}


