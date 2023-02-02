package com.agmg.carsparadise.GestioneAccount.Control;

import com.agmg.carsparadise.Entity.Impiegato;
import com.agmg.carsparadise.Util.ErroreDBMS;
import com.agmg.carsparadise.Util.ProcessoDBMS;
import com.agmg.carsparadise.Util.Utils;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.ArrayList;

public class GestoreAccount {

    public GestoreAccount() {
        if (Impiegato.getCredenzialiVerificate() == 1)
            Utils.cambiaInterfaccia("GestioneAccount/InterfacciaGestioneAccount.fxml", "Gestione Account");
    }

    // Dobbiamo svolgere tutte le query dentro il PRocessoDBMS, non nella gestione account
    public void verificaCredenziali(TextField matricolaField, PasswordField passwordField) {
        if (matricolaField.getText().isBlank() || passwordField.getText().isBlank()) {
            Utils.creaPannelloErrore("I campi non possono essere vuoti!");
            return;
        }

        try {
            int matricola = Integer.parseInt(matricolaField.getText().trim());
        } catch (NumberFormatException e) {
            Utils.creaPannelloErrore("Il campo 'matricola' deve contenere solo numeri");
            return;
        }

        try {
            int result = ProcessoDBMS.verificaCredenziali(Integer.parseInt(matricolaField.getText().trim()), passwordField.getText().trim()); //0 non corrispondono, 1 corrispondono, -1 i campi sono vuoi

            if (result == 1) {
                creaImpiegato(Integer.parseInt(matricolaField.getText().trim()), passwordField.getText().trim());
                Impiegato.setCredenzialiVerificate(1);
                Utils.cambiaInterfaccia("GestioneAccount/InterfacciaGestioneAccount.fxml", "Gestione Account");
            } else if (result == 0) {
                Utils.creaPannelloErrore("Le credenziali sono errate");
            } else {
                Utils.creaPannelloErrore("I campi non possono essere vuoti");
            }
        } catch (SQLException e) {
            ErroreDBMS.erroreGenericoDBMS(e);
        }
    }

    private void creaImpiegato(int matricola, String password) {
        Impiegato.setMatricola(matricola);
        Impiegato.setPassword(password);
        Impiegato.recuperaEmail();
        Impiegato.recuperaIsAdmin();
        Impiegato.recuperaRuolo();
    }

    public void verificaPassword(PasswordField vecchiaPassw, PasswordField nuovaPassw) {
        if (vecchiaPassw.getText().isBlank() || nuovaPassw.getText().isBlank()) {
            Utils.creaPannelloErrore("I campi non possono essere vuoti!");
            return;
        }
        String vecchiaPasswDb = ProcessoDBMS.getPassword(Impiegato.getMatricola());
        if (vecchiaPassw.getText().trim().equals(vecchiaPasswDb)) {
            aggiornaPassword(nuovaPassw.getText().trim());
        } else {
            Utils.creaPannelloErrore("Le password non coincidono");
        }
    }

    public void aggiornaPassword(String password) {
        ProcessoDBMS.aggiornaPassword(password);
        Impiegato.setPassword(password);
        Utils.creaPannelloInformazione("Password Aggiornata!");
    }

    public ArrayList<String> recuperaProfilo() {
        ArrayList<String> dati = ProcessoDBMS.recuperaDatiProfilo();
        return dati;
    }

    public void aggiornaProfiloImpiegato(TextField indirizzoField, TextField telField, TextField ibanField) {
        if (indirizzoField.getText().isBlank() || telField.getText().isBlank() || ibanField.getText().isBlank()) {
            Utils.creaPannelloErrore("I campi non possono essere vuoti!");
            return;
        }

        ProcessoDBMS.aggiornaImpiegato(indirizzoField.getText().trim(), telField.getText().trim(), ibanField.getText().trim());
        Utils.creaPannelloInformazione("Dati aggiornati");
    }

    public void mostraInterfacciaAccount() {
        Utils.cambiaInterfaccia("GestioneAccount/InterfacciaGestioneAccount.fxml", "GestioneAccount");
    }

    public void mostraFormModificaPassword() {
        Utils.cambiaInterfaccia("GestioneAccount/ModificaPassword.fxml", "Modifica Password");
    }
    public void disconnettiImpiegato() {
        Impiegato.destroy();
        Utils.creaPannelloInformazione("Disconnessione avvenuta con successo");
        Utils.cambiaInterfaccia("GestioneAccount/Login.fxml", "Login");
    }

    public void mostraFormModificaProfilo(){
        Utils.cambiaInterfaccia("GestioneAccount/ModificaProfilo.fxml", "Modifica Profilo");
    }
}
