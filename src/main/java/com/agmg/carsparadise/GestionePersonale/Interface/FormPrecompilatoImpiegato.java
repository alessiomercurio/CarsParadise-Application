package com.agmg.carsparadise.GestionePersonale.Interface;

import com.agmg.carsparadise.GestionePersonale.Control.GestorePersonale;
import com.agmg.carsparadise.GestionePersonale.Objects.RecordImpiegato;
import com.agmg.carsparadise.Util.ProcessThread;
import com.agmg.carsparadise.Util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.w3c.dom.Text;

public class FormPrecompilatoImpiegato {

    private GestorePersonale gestorePersonale = ProcessThread.getGestorePersonale();

    @FXML
    private CheckBox checkBoxAmministratore;

    @FXML
    private ChoiceBox<String> choiceBoxRuolo;

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
    private TextField matricolaField;

    @FXML
    private TextField nomeField;

    @FXML
    private TextField passwordField; //cambiare in passwordField

    @FXML
    private TextField telField;

    @FXML
    public void initialize(){
        choiceBoxRuolo.getItems().add("Venditore");
        choiceBoxRuolo.getItems().add("Noleggiatore");
        choiceBoxRuolo.getItems().add("Meccanico");
        choiceBoxRuolo.getItems().add("Addetto al lavaggio");
    }

    @FXML
    void cercaImpiegato() {
        RecordImpiegato impiegato = gestorePersonale.getImpiegato(matricolaField);
        nomeField.setText(impiegato.getNome());
        cognomeField.setText(impiegato.getCognome());
        indirizzoField.setText(impiegato.getIndirizzo());
        telField.setText(impiegato.getTelefono());
        ibanField.setText(impiegato.getIban());
        emailField.setText(impiegato.getEmail());
        passwordField.setText(impiegato.getPassword());
        checkBoxAmministratore.setSelected(impiegato.getIsAdmin().equals("Si") ? true : false);
        choiceBoxRuolo.setValue(impiegato.getRuolo());
    }

    @FXML
    void modificaDatiImpiegato(){
        gestorePersonale.modificaDatiImpiegato(matricolaField, nomeField, cognomeField, indirizzoField, telField, ibanField, emailField, passwordField, checkBoxAmministratore, choiceBoxRuolo);
    }

    @FXML
    void tornaAGestionePersonale(ActionEvent event) {
        gestorePersonale.mostraInterfacciaGestionePersonale();
    }

}
