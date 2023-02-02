package com.agmg.carsparadise.GestioneCarriera.Interface;

import com.agmg.carsparadise.GestioneCarriera.Control.GestoreCarriera;
import com.agmg.carsparadise.Util.ProcessThread;
import com.agmg.carsparadise.Util.ProcessoDBMS;
import com.agmg.carsparadise.Util.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import com.agmg.carsparadise.GestioneCarriera.Objects.Ritardo;
import javafx.scene.control.cell.PropertyValueFactory;

public class TabellaRitardi {

    private GestoreCarriera gestoreCarriera = ProcessThread.getGestoreCarriera();

    @FXML
    private TableColumn<Ritardo, String> colonnaData;

    @FXML
    private TableColumn<Ritardo, String> colonnaMotivazione;

    @FXML
    private TableView<Ritardo> tabellaRitardi;

    @FXML
    void tornaAGestioneCarriera() {
        gestoreCarriera.mostraInterfacciaGestioneCarriera();
    }

    @FXML
    public void initialize(){
        tabellaRitardi.setItems(gestoreCarriera.recuperaTurniConRitardo());
        colonnaData.setCellValueFactory(new PropertyValueFactory<Ritardo, String>("data"));
        colonnaMotivazione.setCellValueFactory(new PropertyValueFactory<Ritardo, String>("motivazione"));
        tabellaRitardi.getColumns().setAll(colonnaData, colonnaMotivazione);
        tabellaRitardi.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
}
