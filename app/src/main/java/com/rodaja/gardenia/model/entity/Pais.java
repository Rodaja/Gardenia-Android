package com.rodaja.gardenia.model.entity;

public class Pais {

    private String foto_bandera,nombre_pais,siglas_pais;



    public Pais(String nombre_pais, String siglas_pais, String foto_bandera) {
        this.foto_bandera = foto_bandera;
        this.nombre_pais = nombre_pais;
        this.siglas_pais = siglas_pais;

    }

    public String getFoto_bandera() {
        return foto_bandera;
    }

    public void setFoto_bandera(String foto_bandera) {
        this.foto_bandera = foto_bandera;
    }

    public String getNombre_pais() {
        return nombre_pais;
    }

    public void setNombre_pais(String nombre_pais) {
        this.nombre_pais = nombre_pais;
    }

    public String getSiglas_pais() {
        return siglas_pais;
    }

    public void setSiglas_pais(String siglas_pais) {
        this.siglas_pais = siglas_pais;
    }



    @Override
    public String toString() {
        return "Pais{" +
                "foto_bandera='" + foto_bandera + '\'' +
                ", nombre_pais='" + nombre_pais + '\'' +
                ", siglas_pais='" + siglas_pais + '\'' +
                '}';
    }
}
