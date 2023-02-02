package com.agmg.carsparadise.GestioneAccount.Interface;

import com.agmg.carsparadise.Entity.Impiegato;
import com.agmg.carsparadise.GestioneAccount.Control.GestoreAccount;
import com.agmg.carsparadise.GestioneCarriera.Control.GestoreCarriera;
import com.agmg.carsparadise.GestionePersonale.Control.GestorePersonale;
import com.agmg.carsparadise.GestionePresenza.Control.GestoreIngressoRemoto;
import com.agmg.carsparadise.Util.ProcessThread;
import com.agmg.carsparadise.Util.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;


public class InterfacciaGestioneAccount {

    private GestoreAccount gestoreAccount = ProcessThread.getGestoreAccount();

    @FXML
    private AnchorPane paneGestioneAccount;
    @FXML
    private Label emailImpiegato;
    @FXML
    private Label ruoloImpiegato;
    @FXML
    private Label labelAmministratore;
    @FXML
    private Button gestionePersonaleBtn;

    @FXML
    public void initialize() {
        gestionePersonaleBtn.setVisible(Impiegato.getIsAdmin() != 0);
        labelAmministratore.setVisible(Impiegato.getIsAdmin() != 0);
        emailImpiegato.setText(Impiegato.getEmail());
        ruoloImpiegato.setText(Impiegato.getRuolo());
    }


    @FXML
    public void mostraFormModificaProfilo() {
        gestoreAccount.mostraFormModificaProfilo();
    }

    @FXML
    public void mostraFormModificaPassword() {
        gestoreAccount.mostraFormModificaPassword();
    }

    @FXML
    public void disconnettiImpiegato() {
        gestoreAccount.disconnettiImpiegato();
    }

    @FXML
    public void vaiAGestioneCarriera() {
        GestoreCarriera gestoreCarriera = new GestoreCarriera();
        ProcessThread.setGestoreCarriera(gestoreCarriera);
    }

    @FXML
    public void vaiARegRemoto() {
        GestoreIngressoRemoto gestoreIngressoRemoto = new GestoreIngressoRemoto();
        ProcessThread.setGestoreIngressoRemoto(gestoreIngressoRemoto);
    }

    @FXML
    public void vaiAGestionePersonale(){
        GestorePersonale gestorePersonale = new GestorePersonale();
        ProcessThread.setGestorePersonale(gestorePersonale);
    }

}
