package com.agmg.carsparadise.GestionePersonale.Interface;

import com.agmg.carsparadise.GestionePersonale.Control.GestorePersonale;
import com.agmg.carsparadise.Util.ProcessThread;
import com.agmg.carsparadise.Util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.shape.Rectangle;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SelezionaPeriodoIntensivo {

    private GestorePersonale gestorePersonale = ProcessThread.getGestorePersonale();
    @FXML
    private DatePicker dataFine;

    @FXML
    private DatePicker dataInizio;

    @FXML
    private Button inserisciPeriodoIntensivoBtn;

    @FXML
    void inserisciPeriodoIntensivo() {
        gestorePersonale.inserisciPeriodoIntensivo(dataInizio, dataFine);
    }

    @FXML
    void tornaAGestionePersonale(ActionEvent event) {
        gestorePersonale.mostraInterfacciaGestionePersonale();
    }

}
