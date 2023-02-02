package com.agmg.carsparadise.GestioneAccount.Interface;

import com.agmg.carsparadise.GestioneAccount.Control.GestoreAccount;
import com.agmg.carsparadise.Util.ProcessThread;
import com.agmg.carsparadise.Util.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class FormModificaProfilo {

    private GestoreAccount gestoreAccount = ProcessThread.getGestoreAccount();

    @FXML
    TextField indirizzoField;
    @FXML
    TextField telField;
    @FXML
    TextField ibanField;

    @FXML
    public void initialize() {
        ArrayList<String> dati = gestoreAccount.recuperaProfilo();
        indirizzoField.setText(dati.get(0));
        telField.setText(dati.get(1));
        ibanField.setText(dati.get(2));
    }

    @FXML
    public void tornaAGestioneAccount() {
       gestoreAccount.mostraInterfacciaAccount();
    }

    @FXML
    public void aggiornaDatiProfilo() {
        gestoreAccount.aggiornaProfiloImpiegato(indirizzoField, telField, ibanField);
    }
}
