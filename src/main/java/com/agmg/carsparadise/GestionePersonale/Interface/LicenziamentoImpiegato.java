package com.agmg.carsparadise.GestionePersonale.Interface;

import com.agmg.carsparadise.GestionePersonale.Control.GestorePersonale;
import com.agmg.carsparadise.Util.ProcessThread;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LicenziamentoImpiegato {

    private GestorePersonale gestorePersonale = ProcessThread.getGestorePersonale();

    @FXML
    private Button licenziamentoBtn;

    @FXML
    private TextField textFieldMatricola;

    @FXML
    void licenziaImpiegato() {
        gestorePersonale.licenziaImpiegato(textFieldMatricola);
    }

    @FXML
    void tornaAGestionePersonale(){
        gestorePersonale.mostraInterfacciaGestionePersonale();
    }
}
