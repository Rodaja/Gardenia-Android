package com.rodaja.gardenia.model.configuration;

import com.rodaja.gardenia.model.entity.User;

public class Configuration {

    //TODO: Cambiar para hacerlo en la nube directamente
    public static User defaultConfiguration(User user) {
        user.setTemperature("celsius");
        user.setNotifications(false);
        user.setTheme("light");

        return user;
    }

    //TODO: Renombrar el metodo a convertir (o algo asi) y moverlo de clase
    //Solo dejar las conversiones
    public static int getTemperatureValue(User user, int temperature){
        int response = temperature;

        if (user.getTemperature().equalsIgnoreCase("kelvin")){
            response = temperature + 273;
        } else if(user.getTemperature().equalsIgnoreCase("fahrenheit")){
            response = (int) Math.round(temperature * 1.8 + 32);
        }

        return response;
    }

}


