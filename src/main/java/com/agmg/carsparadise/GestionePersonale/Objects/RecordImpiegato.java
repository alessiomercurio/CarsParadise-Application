package com.agmg.carsparadise.GestionePersonale.Objects;

public class RecordImpiegato {

    private int matricola;
    private String nome;
    private String cognome;
    private String indirizzo;
    private String telefono;
    private String iban;
    private String email;
    private String password;
    private int isAdmin;
    private int idRuolo;

    public RecordImpiegato(int matricola, String nome, String cognome, String indirizzo, String telefono, String iban, String email, String password, int isAdmin, int idRuolo) {
        this.matricola = matricola;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.telefono = telefono;
        this.iban = iban;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.idRuolo = idRuolo;
    }

    public int getMatricola() {
        return matricola;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getIban() {
        return iban;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getIsAdmin() {
        return isAdmin == 1 ? "Si" : "No";
    }

    public String getRuolo() {
        if(idRuolo == 1)
            return "Venditore";
        else if(idRuolo == 2)
            return "Noleggiatore";
        else if(idRuolo == 3)
            return "Meccanico";
        else
            return "Addetto al lavaggio";
    }
}
