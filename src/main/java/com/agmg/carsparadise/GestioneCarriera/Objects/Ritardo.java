package com.agmg.carsparadise.GestioneCarriera.Objects;

public class Ritardo {
    private String data;
    private String motivazione;

    public Ritardo(String data, String motivazione) {
        this.data = data;
        this.motivazione = motivazione;
    }

    public String getData(){
        return data;
    }

    public String getMotivazione(){
        return motivazione;
    }
}
