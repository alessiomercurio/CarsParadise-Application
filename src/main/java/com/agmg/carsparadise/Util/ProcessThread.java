package com.agmg.carsparadise.Util;

import com.agmg.carsparadise.GestioneAccount.Control.GestoreAccount;
import com.agmg.carsparadise.GestioneAccount.Control.GestoreRecuperoPassword;
import com.agmg.carsparadise.GestioneCarriera.Control.GestoreCarriera;
import com.agmg.carsparadise.GestionePersonale.Control.GestorePersonale;
import com.agmg.carsparadise.GestionePresenza.Control.GestoreIngressoRemoto;

public class ProcessThread {
    private static GestoreRecuperoPassword gestoreRecuperoPassword;
    private static GestoreAccount gestoreAccount;
    private static GestoreCarriera gestoreCarriera;
    private static GestorePersonale gestorePersonale;
    private static GestoreIngressoRemoto gestoreIngressoRemoto;

    public static GestoreRecuperoPassword getGestoreRecuperoPassword() {
        return gestoreRecuperoPassword;
    }

    public static void setGestoreRecuperoPassword(GestoreRecuperoPassword gestoreRecuperoPassword) {
        ProcessThread.gestoreRecuperoPassword = gestoreRecuperoPassword;
    }
    public static GestoreAccount getGestoreAccount() {
        return gestoreAccount;
    }

    public static void setGestoreAccount(GestoreAccount gestoreAccount) {
        ProcessThread.gestoreAccount = gestoreAccount;
    }

    public static GestoreCarriera getGestoreCarriera() {
        return gestoreCarriera;
    }

    public static void setGestoreCarriera(GestoreCarriera gestoreCarriera) {
        ProcessThread.gestoreCarriera = gestoreCarriera;
    }

    public static GestorePersonale getGestorePersonale() {
        return gestorePersonale;
    }

    public static void setGestorePersonale(GestorePersonale gestorePersonale) {
        ProcessThread.gestorePersonale = gestorePersonale;
    }

    public static GestoreIngressoRemoto getGestoreIngressoRemoto() {
        return gestoreIngressoRemoto;
    }

    public static void setGestoreIngressoRemoto(GestoreIngressoRemoto gestoreIngressoRemoto) {
        ProcessThread.gestoreIngressoRemoto = gestoreIngressoRemoto;
    }


}
