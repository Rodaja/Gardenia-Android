package com.rodaja.gardenia.model.configuration;

public class Configuration {
    public static boolean notifications = false;
    //0=Celsius,1=Kelvin,2=Farhenheit
    public static int temperature = 0;
    //simbolo de la temperatura
    public static String temperaturaSimbolo = "ºC";
    //0=Claro,1=Oscuro
    public static int tema = 0;


    public static void cambioSimboloTemperatura(int numTemperatura) {
        switch (numTemperatura) {
            case 0:
                temperaturaSimbolo = "ºC";
                break;
            case 1:
                temperaturaSimbolo = "K";
                break;
            case 2:
                temperaturaSimbolo = "ºF";
                break;
        }
    }

    public void default_status() {
        notifications = false;
        temperature = 0;
        temperaturaSimbolo = "ºC";
        tema = 0;
    }

}


