package com.agmg.carsparadise.GestioneCarriera.Objects;

public class Stipendio {

    private String data;
    private double stipendio;


    public Stipendio(String data, double stipendio) {
        this.data = data;
        this.stipendio = stipendio;
    }

    public String getData() {
        return data;
    }

    public double getStipendio() {
        return stipendio;
    }

}
