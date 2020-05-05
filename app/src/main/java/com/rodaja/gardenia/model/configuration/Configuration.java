package com.rodaja.gardenia.model.configuration;

public class Configuration {
    public static boolean notifications = false;
    //0=Celsius,1=Kelvin,2=Farhenheit
    public static int temperature = 0;
    //0=Claro,1=Oscuro
    public static int tema = 0;


    public void default_status() {
        notifications = false;
        temperature = 0;
        tema = 0;
    }

}


