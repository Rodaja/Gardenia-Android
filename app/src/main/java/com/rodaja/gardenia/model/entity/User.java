package com.rodaja.gardenia.model.entity;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

    private int id;
    private String email;
    private String userName;
    private String name;
    private String surname;
    private String password;
    private String country;
    private String apiKey;

    private String temperature;
    private String theme;
    private boolean notifications;

    private List<FlowerPot> listFlowerPots;

    public User() {
    }

    public User(String email, String userName, String name, String surname, String password, String country, String apiKey, String temperature, String theme, boolean notifications, List<FlowerPot> listFlowerPots) {
        this.email = email;
        this.userName = userName;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.country = country;
        this.apiKey = apiKey;
        this.temperature = temperature;
        this.theme = theme;
        this.notifications = notifications;
        this.listFlowerPots = listFlowerPots;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public List<FlowerPot> getListFlowerPots() {
        return listFlowerPots;
    }

    public void setListFlowerPots(List<FlowerPot> listFlowerPots) {
        this.listFlowerPots = listFlowerPots;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public boolean isNotifications() {
        return notifications;
    }

    public void setNotifications(boolean notifications) {
        this.notifications = notifications;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", password='" + password + '\'' +
                ", country='" + country + '\'' +
                ", apiKey='" + apiKey + '\'' +
                ", temperature='" + temperature + '\'' +
                ", theme='" + theme + '\'' +
                ", notifications=" + notifications +
                ", listFlowerPots=" + listFlowerPots +
                '}';
    }
}
