package com.agmg.carsparadise.GestioneCarriera.Interface;

import com.agmg.carsparadise.GestioneCarriera.Control.GestoreCarriera;
import com.agmg.carsparadise.GestioneCarriera.Objects.Stipendio;
import com.agmg.carsparadise.Util.ProcessThread;
import com.agmg.carsparadise.Util.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TabellaStipendi {

    @FXML
    TableView<Stipendio> tabellaStipendi;
    @FXML
    TableColumn<Stipendio, Double> colonnaStipendio;
    @FXML
    TableColumn<Stipendio, String> colonnaData;
    private GestoreCarriera gestoreCarriera = ProcessThread.getGestoreCarriera();

    @FXML
    public void tornaAGestioneCarriera() {
        gestoreCarriera.mostraInterfacciaGestioneCarriera();
    }

    @FXML
    public void initialize() {
        tabellaStipendi.setItems(gestoreCarriera.recuperaStipendi());
        colonnaData.setCellValueFactory(new PropertyValueFactory<Stipendio, String>("data"));
        colonnaStipendio.setCellValueFactory(new PropertyValueFactory<Stipendio, Double>("stipendio"));
        tabellaStipendi.getColumns().setAll(colonnaData, colonnaStipendio);
        tabellaStipendi.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
}