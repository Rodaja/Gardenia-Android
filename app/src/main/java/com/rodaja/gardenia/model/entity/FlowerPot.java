package com.rodaja.gardenia.model.entity;

public class FlowerPot {

    private String macAddress;
    private String name;
    private int groundHumidity;
    private int airHumidity;
    private int airTemperature;

    public FlowerPot() {
    }

    public FlowerPot(String macAddress, String name, int groundHumidity, int airHumidity,
                     int airTemperature) {
        super();
        this.macAddress = macAddress;
        this.name = name;
        this.groundHumidity = groundHumidity;
        this.airHumidity = airHumidity;
        this.airTemperature = airTemperature;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "FlowerPot [macAddress=" + macAddress + ", name=" + name + ", groundHumidity=" + groundHumidity
                + ", airHumidity=" + airHumidity + ", airTemperature=" + airTemperature
                + "]";
    }
}
