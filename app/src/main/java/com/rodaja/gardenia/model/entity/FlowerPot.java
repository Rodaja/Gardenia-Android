package com.rodaja.gardenia.model.entity;

import java.io.Serializable;

public class FlowerPot implements Serializable {

    private String macAddress;
    private String version;
    private String name;
    private String imageUrl;
    private int groundHumidity;
    private int airHumidity;
    private int airTemperature;
    private boolean water;

    public FlowerPot(){

    }

    public FlowerPot(String macAddress, String version, String name, String imageUrl, int groundHumidity, int airHumidity, int airTemperature, boolean water) {
        this.macAddress = macAddress;
        this.version = version;
        this.name = name;
        this.imageUrl = imageUrl;
        this.groundHumidity = groundHumidity;
        this.airHumidity = airHumidity;
        this.airTemperature = airTemperature;
        this.water = water;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getGroundHumidity() {
        return groundHumidity;
    }

    public void setGroundHumidity(int groundHumidity) {
        this.groundHumidity = groundHumidity;
    }

    public int getAirHumidity() {
        return airHumidity;
    }

    public void setAirHumidity(int airHumidity) {
        this.airHumidity = airHumidity;
    }

    public int getAirTemperature() {
        return airTemperature;
    }

    public void setAirTemperature(int airTemperature) {
        this.airTemperature = airTemperature;
    }

    public boolean isWater() {
        return water;
    }

    public void setWater(boolean water) {
        this.water = water;
    }

    @Override
    public String toString() {
        return "FlowerPot{" +
                "macAddress='" + macAddress + '\'' +
                ", version='" + version + '\'' +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", groundHumidity=" + groundHumidity +
                ", airHumidity=" + airHumidity +
                ", airTemperature=" + airTemperature +
                ", water=" + water +
                '}';
    }
}
