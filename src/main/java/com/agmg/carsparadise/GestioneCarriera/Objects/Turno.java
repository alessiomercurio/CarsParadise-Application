package com.agmg.carsparadise.GestioneCarriera.Objects;

public class Turno {

    private int idTurno;
    // per le date yyyy-mm-dd
    // per gli orari HH:MM:SS
    private String inizioData;

    private String fineData;

    private String inizioOrario;

    private String fineOrario;
    private String servizio;
    private int isStraordinario;

    public Turno(int idTurno, String inizioData, String fineData, String iniziOrario, String fineOrario, String servizio, int isStraordinario) {
        this.idTurno = idTurno;
        this.inizioData = inizioData;
        this.fineData = fineData;
        this.inizioOrario = iniziOrario;
        this.fineOrario = fineOrario;
        this.servizio = servizio;
        this.isStraordinario = isStraordinario;
    }

    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }

    public String getInizioData() {
        return inizioData;
    }

    public void setInizioData(String inizioData) {
        this.inizioData = inizioData;
    }

    public String getFineData() {
        return fineData;
    }

    public void setFineData(String fineData) {
        this.fineData = fineData;
    }

    public String getInizioOrario() {
        return inizioOrario;
    }

    public void setInizioOrario(String iniziOrario) {
        this.inizioOrario = iniziOrario;
    }

    public String getFineOrario() {
        return fineOrario;
    }

    public void setFineOrario(String fineOrario) {
        this.fineOrario = fineOrario;
    }

    public String getServizio() {
        return servizio;
    }

    public void setServizio(String servizio) {
        this.servizio = servizio;
    }

    public String getIsStraordinario() {
        return (this.isStraordinario == 1 ? "Si" : "No");
        //return isStraordinario;
    }

    public void setIsStraordinario(int isStraordinario) {
        this.isStraordinario = isStraordinario;
    }
}
