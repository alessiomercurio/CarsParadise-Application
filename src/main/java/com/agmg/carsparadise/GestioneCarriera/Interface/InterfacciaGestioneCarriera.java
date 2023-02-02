package com.agmg.carsparadise.GestioneCarriera.Interface;

import com.agmg.carsparadise.Entity.Impiegato;
import com.agmg.carsparadise.GestioneCarriera.Control.GestoreCarriera;
import com.agmg.carsparadise.GestionePersonale.Control.GestorePersonale;
import com.agmg.carsparadise.GestionePresenza.Control.GestoreIngressoRemoto;
import com.agmg.carsparadise.Util.ProcessThread;
import com.agmg.carsparadise.Util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class InterfacciaGestioneCarriera {

    private GestoreCarriera gestoreCarriera;
    @FXML
    AnchorPane paneGestioneCarriera;
    @FXML
    Label emailImpiegato;
    @FXML
    Label ruoloImpiegato;
    @FXML
    Label labelAmministratore;
    @FXML
    Button gestionePersonaleBtn;

    @FXML
    public void initialize() {
        gestionePersonaleBtn.setVisible(Impiegato.getIsAdmin() != 0);
        labelAmministratore.setVisible(Impiegato.getIsAdmin() != 0);
        emailImpiegato.setText(Impiegato.getEmail());
        ruoloImpiegato.setText(Impiegato.getRuolo());
    }

    @FXML
    public void cliccaVisStipendi() {
        gestoreCarriera = ProcessThread.getGestoreCarriera();
        gestoreCarriera.mostraVisStipendi();
    }

    @FXML
    public void cliccaVisTurni() {
        gestoreCarriera = ProcessThread.getGestoreCarriera();
        gestoreCarriera.cliccaVisTurni();
    }

    @FXML
    public void cliccaVisRitardi(){
        gestoreCarriera = ProcessThread.getGestoreCarriera();
        gestoreCarriera.mostraVisRitardi();
    }

    @FXML
    public void cliccaGestioneAccount() {
        ProcessThread.getGestoreAccount().mostraInterfacciaAccount();
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

    @FXML
    public void cliccaRichiediAstensione() {
        gestoreCarriera = ProcessThread.getGestoreCarriera();
        gestoreCarriera.mostraRichiediAstensione();
    }
}
