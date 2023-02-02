package com.agmg.carsparadise.GestionePersonale.Interface;

import com.agmg.carsparadise.Entity.Impiegato;
import com.agmg.carsparadise.GestioneAccount.Control.GestoreAccount;
import com.agmg.carsparadise.GestioneCarriera.Control.GestoreCarriera;
import com.agmg.carsparadise.GestionePersonale.Control.GestorePersonale;
import com.agmg.carsparadise.GestionePresenza.Control.GestoreIngressoRemoto;
import com.agmg.carsparadise.Util.ProcessThread;
import com.agmg.carsparadise.Util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class InterfacciaGestionePersonale {

    private GestorePersonale gestorePersonale;
    @FXML
    private Label emailImpiegato;

    @FXML
    private Button gestionePersonaleBtn;

    @FXML
    private AnchorPane paneGestionePersonale;

    @FXML
    private Label ruoloImpiegato;

    @FXML
    private Label labelAmministratore;

    @FXML
    private ImageView vi;

    public void initialize() {
        gestionePersonaleBtn.setVisible(Impiegato.getIsAdmin() != 0);
        labelAmministratore.setVisible(Impiegato.getIsAdmin() != 0);
        emailImpiegato.setText(Impiegato.getEmail());
        ruoloImpiegato.setText(Impiegato.getRuolo());
    }

    @FXML
    public void vaiAGestioneAccount() {
        GestoreAccount gestoreAccount = new GestoreAccount();
        ProcessThread.setGestoreAccount(gestoreAccount);
    }

    @FXML
   public void mostraFormInserisciImpiegato(){
        gestorePersonale = ProcessThread.getGestorePersonale();
        gestorePersonale.mostraFormInserisciImpiegato();
    }

    @FXML
    public void mostraFormLicenziaImpiegato(){
        gestorePersonale = ProcessThread.getGestorePersonale();
        gestorePersonale.mostraFormLicenziaImpiegato();
    }

    @FXML
    public void mostraImpiegati(){
        gestorePersonale = ProcessThread.getGestorePersonale();
        gestorePersonale.mostraImpiegati();
    }

    @FXML
    public void vaiAGestioneCarriera(){
        GestoreCarriera gestoreCarriera = new GestoreCarriera();
        ProcessThread.setGestoreCarriera(gestoreCarriera);
    }

    @FXML
    public void mostraFormModificaDatiImpiegati(){
        gestorePersonale = ProcessThread.getGestorePersonale();
        gestorePersonale.mostraFormModificaDatiImpiegati();
    }

    @FXML
    public void mostraInserisciPeriodoIntensivo(){
        gestorePersonale = ProcessThread.getGestorePersonale();
        gestorePersonale.mostraInserisciPeriodoIntensivo();
    }

    @FXML
    public void mostraEliminaPeriodoIntensivo(){
        gestorePersonale = ProcessThread.getGestorePersonale();
        gestorePersonale.mostraEliminaPeriodoIntensivo();
    }

    @FXML
    public void vaiARegRemoto() {
        GestoreIngressoRemoto gestoreIngressoRemoto = new GestoreIngressoRemoto();
        ProcessThread.setGestoreIngressoRemoto(gestoreIngressoRemoto);
    }
}
