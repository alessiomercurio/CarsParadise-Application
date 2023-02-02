package com.agmg.carsparadise.GestioneCarriera.Interface;

import com.agmg.carsparadise.Util.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import com.agmg.carsparadise.GestioneCarriera.Objects.Turno;
import javafx.scene.control.cell.PropertyValueFactory;
import com.agmg.carsparadise.GestioneCarriera.Control.GestoreCarriera;
import com.agmg.carsparadise.Util.ProcessThread;

public class TabellaTurni {

    private GestoreCarriera gestoreCarriera = ProcessThread.getGestoreCarriera();
    @FXML
    private TableColumn<Turno, String> colData;

    @FXML
    private TableColumn<Turno, String> colOrarioFine;

    @FXML
    private TableColumn<Turno, String> coloOraInizio;

    @FXML
    private TableColumn<Turno, String> colServizio;

    @FXML
    private TableColumn<Turno, String> colStraordinario;

    @FXML
    private TableView<Turno> tabellaTurni;



    @FXML
    public void tornaAGestioneCarriera() {
        gestoreCarriera.mostraInterfacciaGestioneCarriera();
    }

    @FXML
    public void initialize(){
        tabellaTurni.setItems(gestoreCarriera.recuperaTurniTrimestre());
        colData.setCellValueFactory(new PropertyValueFactory<Turno, String>("inizioData"));
        coloOraInizio.setCellValueFactory(new PropertyValueFactory<Turno, String>("inizioOrario"));
        colOrarioFine.setCellValueFactory(new PropertyValueFactory<Turno, String>("fineOrario"));
        colServizio.setCellValueFactory(new PropertyValueFactory<Turno, String>("servizio"));
        colStraordinario.setCellValueFactory(new PropertyValueFactory<Turno, String>("isStraordinario"));
        tabellaTurni.getColumns().setAll(colData, coloOraInizio, colOrarioFine, colServizio, colStraordinario);
        tabellaTurni.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

}
