package com.agmg.carsparadise.GestionePersonale.Interface;

import com.agmg.carsparadise.GestioneCarriera.Objects.Stipendio;
import com.agmg.carsparadise.GestionePersonale.Control.GestorePersonale;
import com.agmg.carsparadise.GestionePersonale.Objects.PeriodoIntensivo;
import com.agmg.carsparadise.Util.ProcessThread;
import com.agmg.carsparadise.Util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Objects;

public class EliminaPeriodoIntensivo {

    private GestorePersonale gestorePersonale = ProcessThread.getGestorePersonale();
    @FXML
    private TableColumn<PeriodoIntensivo, String> colonnaDataFine;

    @FXML
    private TableColumn<PeriodoIntensivo, String> colonnaDataInizio;

    @FXML
    private TableView<PeriodoIntensivo> tabellaPeriodiIntensivo;

    @FXML
    void initialize(){
        tabellaPeriodiIntensivo.setItems(gestorePersonale.recuperaPeriodiIntensivi());
        colonnaDataInizio.setCellValueFactory(new PropertyValueFactory<PeriodoIntensivo, String>("dataInizio"));
        colonnaDataFine.setCellValueFactory(new PropertyValueFactory<PeriodoIntensivo, String>("dataFine"));
        tabellaPeriodiIntensivo.getColumns().setAll(colonnaDataInizio, colonnaDataFine);
        tabellaPeriodiIntensivo.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    @FXML
    void eliminaPeriodoIntensivo() {
        if(tabellaPeriodiIntensivo.getSelectionModel().isEmpty()){
            Utils.creaPannelloErrore("Nessun periodo selezionato");
            return;
        }
        PeriodoIntensivo periodo = new PeriodoIntensivo(tabellaPeriodiIntensivo.getSelectionModel().getSelectedItem().getDataInizio(), tabellaPeriodiIntensivo.getSelectionModel().getSelectedItem().getDataFine());
        if(gestorePersonale.eliminaPeriodoIntensivo(periodo) == 1){
            tabellaPeriodiIntensivo.getItems().remove(tabellaPeriodiIntensivo.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    void tornaAGestionePersonale() {
        gestorePersonale.mostraInterfacciaGestionePersonale();
    }

}
