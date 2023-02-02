package com.agmg.carsparadise.GestionePersonale.Interface;

import com.agmg.carsparadise.GestionePersonale.Control.GestorePersonale;
import com.agmg.carsparadise.GestionePersonale.Objects.RecordImpiegato;
import com.agmg.carsparadise.Util.ProcessThread;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TabellaImpiegati {

    private GestorePersonale gestorePersonale = ProcessThread.getGestorePersonale();

    @FXML
    private TableColumn<RecordImpiegato, String> colCognome;

    @FXML
    private TableColumn<RecordImpiegato, String> colEmail;

    @FXML
    private TableColumn<RecordImpiegato, String> colIban;

    @FXML
    private TableColumn<RecordImpiegato, String> colIndirizzo;

    @FXML
    private TableColumn<RecordImpiegato, String> colIsAdmin;

    @FXML
    private TableColumn<RecordImpiegato, Integer> colMatricola;

    @FXML
    private TableColumn<RecordImpiegato, String> colNome;

    @FXML
    private TableColumn<RecordImpiegato, String> colPassword;

    @FXML
    private TableColumn<RecordImpiegato, String> colRuolo;

    @FXML
    private TableColumn<RecordImpiegato, String> colTelefono;

    @FXML
    private TableView<RecordImpiegato> tabellaImpiegati;

    @FXML
    void tornaAGestionePersonale() {
        gestorePersonale.mostraInterfacciaGestionePersonale();
    }

    @FXML
    public void initialize(){
        tabellaImpiegati.setItems(gestorePersonale.recuperaImpiegati());
        colMatricola.setCellValueFactory(new PropertyValueFactory<RecordImpiegato, Integer>("matricola"));
        colNome.setCellValueFactory(new PropertyValueFactory<RecordImpiegato, String>("nome"));
        colCognome.setCellValueFactory(new PropertyValueFactory<RecordImpiegato, String>("cognome"));
        colIndirizzo.setCellValueFactory(new PropertyValueFactory<RecordImpiegato, String>("indirizzo"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<RecordImpiegato, String>("telefono"));
        colIban.setCellValueFactory(new PropertyValueFactory<RecordImpiegato, String>("iban"));
        colEmail.setCellValueFactory(new PropertyValueFactory<RecordImpiegato, String>("email"));
        colPassword.setCellValueFactory(new PropertyValueFactory<RecordImpiegato, String>("password"));
        colIsAdmin.setCellValueFactory(new PropertyValueFactory<RecordImpiegato, String>("isAdmin"));
        colRuolo.setCellValueFactory(new PropertyValueFactory<RecordImpiegato, String>("ruolo"));
        tabellaImpiegati.getColumns().setAll(colMatricola, colNome, colCognome, colIndirizzo, colTelefono, colIban, colEmail, colPassword, colIsAdmin, colRuolo);
        tabellaImpiegati.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

}
