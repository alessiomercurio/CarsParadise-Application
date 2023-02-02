package com.agmg.carsparadise.Entity;

import com.agmg.carsparadise.Util.ProcessoDBMS;

public class Impiegato {

    private static int matricola;
    private static String password;
    private static String email;
    private static int isAdmin = 0; //non è amministratore
    private static String ruolo;

    private static int credenzialiVerificate = 0; // 0 = no, 1 = si

    public Impiegato(int matricola, String password, String email) {
        this.matricola = matricola;
        this.password = password;
        this.email = email;
    }

    //costruttore vuoto
    public Impiegato() {
        matricola = 0;
        password = null;
        email = null;
    }

    public static int getMatricola() {
        return matricola;
    }

    public static void setMatricola(int newMatricola) {
        matricola = newMatricola;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String newPassword) {
        password = newPassword;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String newEmail) {
        email = newEmail;
    }

    public static int getIsAdmin() {
        return isAdmin;
    }

    public static void setIsAdmin(int admin) {
        isAdmin = admin;
    }

    public static String getRuolo() {
        return ruolo;
    }

    public static void setRuolo(String newRuolo) {
        ruolo = newRuolo;
    }

    public static void recuperaEmail() {
        email = ProcessoDBMS.recuperaEmailImpiegato(matricola);
    }

    public static void recuperaIsAdmin() {
        isAdmin = ProcessoDBMS.recuperaAdmin(matricola);
    }

    public static void recuperaRuolo() {
        ruolo = ProcessoDBMS.recuperaRuolo(matricola);
    }

    public static int getCredenzialiVerificate() {
        return credenzialiVerificate;
    }

    public static void setCredenzialiVerificate(int credenzialiVerificate) {
        Impiegato.credenzialiVerificate = credenzialiVerificate;
    }

    public static void destroy() {
        matricola = 0;
        password = "";
        email = "";
        isAdmin = 0;
        ruolo = "";
        credenzialiVerificate = 0;
    }
}
