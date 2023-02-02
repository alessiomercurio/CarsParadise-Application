package com.agmg.carsparadise.GestionePresenza.Object;

public class TurnoPresenza {

    private int idTurno;
    private String data;
    private String orario;

    public TurnoPresenza(int idTurno, String data, String orario) {
        this.idTurno = idTurno;
        this.data = data;
        this.orario = orario;
    }

    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getOrario() {
        return orario;
    }

    public void setOrario(String orario) {
        this.orario = orario;
    }
}
