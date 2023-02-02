package com.agmg.carsparadise.GestioneAccount.Interface;


import com.agmg.carsparadise.GestioneAccount.Control.GestoreAccount;
import com.agmg.carsparadise.GestioneAccount.Control.GestoreRecuperoPassword;
import com.agmg.carsparadise.GestionePresenza.Control.GestoreIngressoRemoto;
import com.agmg.carsparadise.Util.ErroreDBMS;
import com.agmg.carsparadise.Util.ProcessThread;
import com.agmg.carsparadise.Util.ProcessoDBMS;
import com.agmg.carsparadise.Util.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class FormLogin {
    @FXML
    AnchorPane rootPane;
    //show //submit //inserDati
    @FXML
    private TextField matricolaField;
    @FXML
    private PasswordField passwordField;


    @FXML
    public void vaiARecuperoPassw() throws IOException {
        GestoreRecuperoPassword gestoreRecuperoPassword = new GestoreRecuperoPassword();
        ProcessThread.setGestoreRecuperoPassword(gestoreRecuperoPassword);
    }

    @FXML
    public void cliccaLogin() throws IOException {
        GestoreAccount gestoreaccount = new GestoreAccount();
        ProcessThread.setGestoreAccount(gestoreaccount);
        gestoreaccount.verificaCredenziali(matricolaField, passwordField);
    }

}
