package com.rodaja.gardenia.model.configuration;

import com.rodaja.gardenia.model.entity.User;

public class Configuration {
    public static boolean notifications = false;
    //0=Celsius,1=Kelvin,2=Farhenheit
    public static int temperature = 0;
    //0=Claro,1=Oscuro
    public static int tema = 0;


    public static User defaultConfiguration(User user) {
        user.setTemperature("celsius");
        user.setNotifications(false);
        user.setTheme("light");

        return user;
    }

    public static String getTemperatureUnit(User user){
        String response = " ºC";

        if (user.getTemperature().equalsIgnoreCase("kelvin")){
            response = " ºK";
        } else if(user.getTemperature().equalsIgnoreCase("fahrenheit")){
            response = " ºF";
        }

        return response;
    }
    public static int getTemperatureValue(User user, int temperature){
        int response = temperature;

        if (user.getTemperature().equalsIgnoreCase("kelvin")){
            response = temperature + 273;
        } else if(user.getTemperature().equalsIgnoreCase("fahrenheit")){
            response = (int) Math.round(temperature * 1.8 + 32);
        }

        return response;
    }

    public static String  getTemperatureString(int temperature){
        String response = "celsius";

        if (temperature == 0){
            response = "celsius";
        } else if(temperature == 1){
            response = "kelvin";
        } else {
            response = "fahrenheit";
        }

        return response;
    }

}


