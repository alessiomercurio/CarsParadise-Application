package com.agmg.carsparadise.GestioneAccount.Interface;

import com.agmg.carsparadise.GestioneAccount.Control.GestoreRecuperoPassword;
import com.agmg.carsparadise.Util.ProcessThread;
import com.agmg.carsparadise.Util.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class FormRecuperoPassw {

    private GestoreRecuperoPassword gestoreRecuperoPassword;

    @FXML
    AnchorPane recuperoPSWAP;

    @FXML
    TextField matricolaField;

    @FXML
    public void tornaALogin() {
        gestoreRecuperoPassword = ProcessThread.getGestoreRecuperoPassword();
        gestoreRecuperoPassword.mostraSchermataLogin();
    }

    @FXML
    public void cliccaRecupera() {
        GestoreRecuperoPassword gestoreRecuperoPassword = new GestoreRecuperoPassword();
        gestoreRecuperoPassword.recuperaPassword(matricolaField);
    }
}
