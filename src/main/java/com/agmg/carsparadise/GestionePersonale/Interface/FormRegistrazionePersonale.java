package com.agmg.carsparadise.GestionePersonale.Interface;

import com.agmg.carsparadise.GestionePersonale.Control.GestorePersonale;
import com.agmg.carsparadise.Util.ProcessThread;
import com.agmg.carsparadise.Util.ProcessoDBMS;
import com.agmg.carsparadise.Util.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class FormRegistrazionePersonale {

    private GestorePersonale gestorePersonale = ProcessThread.getGestorePersonale();

    @FXML
    private TextField cognomeField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField ibanField;

    @FXML
    private TextField indirizzoField;

    @FXML
    private AnchorPane inserisciImpiegato;

    @FXML
    private TextField nomeField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField telField;

    @FXML
    ChoiceBox<String> choiceBoxRuolo;

    @FXML
    CheckBox checkBoxAmministratore;

    @FXML
    public void initialize(){
        choiceBoxRuolo.getItems().add("Venditore");
        choiceBoxRuolo.getItems().add("Noleggiatore");
        choiceBoxRuolo.getItems().add("Meccanico");
        choiceBoxRuolo.getItems().add("Addetto al lavaggio");
    }

    @FXML
    public void tornaAGestionePersonale(){
        gestorePersonale.mostraInterfacciaGestionePersonale();
    }

    @FXML
    public void registraNuovoImpiegato(){
        gestorePersonale.registraImpiegato(nomeField, cognomeField, indirizzoField, telField, emailField, passwordField, ibanField, choiceBoxRuolo, checkBoxAmministratore);
    }


}
