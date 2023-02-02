package com.agmg.carsparadise.GestioneCarriera.Interface;

import com.agmg.carsparadise.GestioneCarriera.Control.GestoreCarriera;
import com.agmg.carsparadise.Util.ProcessThread;
import com.agmg.carsparadise.Util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class SelezionePeriodoAstensione {

    private GestoreCarriera gestoreCarriera = ProcessThread.getGestoreCarriera();
    @FXML
    private Label emailImpiegato;

    @FXML
    private Button gestionePersonaleBtn;

    @FXML
    private Label labelAmministratore;

    @FXML
    private AnchorPane paneGestioneAccount;

    @FXML
    private Label ruoloImpiegato;

    @FXML
    private ImageView vi;

    @FXML
    private ImageView vi1;

    @FXML
    private DatePicker dataInizio;

    @FXML
    private DatePicker dataFine;

    @FXML
    private ChoiceBox<String> motivazioneChoiceBox;

    @FXML
    public void initialize(){
        motivazioneChoiceBox.getItems().add("Ferie");
        motivazioneChoiceBox.getItems().add("Permesso");
        motivazioneChoiceBox.getItems().add("Malattia");
        motivazioneChoiceBox.getItems().add("Sciopero");
        motivazioneChoiceBox.getItems().add("Congedo parentale");
    }

    @FXML
    public void inserisciPeriodoAstensione(){
        gestoreCarriera.inserisciPeriodoAstensione(dataInizio, dataFine, motivazioneChoiceBox.getValue());
    }

    @FXML
    public void tornaAGestioneCarriera(){
        gestoreCarriera.mostraInterfacciaGestioneCarriera();
    }

}
