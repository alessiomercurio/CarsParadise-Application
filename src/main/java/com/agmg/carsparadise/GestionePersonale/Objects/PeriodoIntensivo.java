package com.agmg.carsparadise.GestionePersonale.Objects;

public class PeriodoIntensivo {
    private String dataInizio;
    private String dataFine;

    public PeriodoIntensivo(String dataInizio, String dataFine) {
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
    }

    public String getDataInizio() {
        return dataInizio;
    }

    public String getDataFine() {
        return dataFine;
    }
}
