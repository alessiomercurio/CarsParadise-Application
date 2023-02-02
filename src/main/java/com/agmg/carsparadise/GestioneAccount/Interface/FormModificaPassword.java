package com.agmg.carsparadise.GestioneAccount.Interface;

import com.agmg.carsparadise.GestioneAccount.Control.GestoreAccount;
import com.agmg.carsparadise.Util.ProcessThread;
import com.agmg.carsparadise.Util.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class FormModificaPassword {

    private GestoreAccount gestoreAccount = ProcessThread.getGestoreAccount();

    @FXML
    private PasswordField vecchiaPasswField;
    @FXML
    private PasswordField nuovaPasswField;

    @FXML
    //aggiornare nome metodo aggiornaPassword
    public void aggiornaPasswOnClick() {
        gestoreAccount.verificaPassword(vecchiaPasswField, nuovaPasswField);
    }

    @FXML
    public void tornaAGestioneAccount() {
        gestoreAccount.mostraInterfacciaAccount();
    }
}
