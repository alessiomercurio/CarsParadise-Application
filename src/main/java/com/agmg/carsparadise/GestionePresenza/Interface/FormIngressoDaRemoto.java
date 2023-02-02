package com.agmg.carsparadise.GestionePresenza.Interface;

import com.agmg.carsparadise.Entity.Impiegato;
import com.agmg.carsparadise.GestioneAccount.Control.GestoreAccount;
import com.agmg.carsparadise.GestioneCarriera.Control.GestoreCarriera;
import com.agmg.carsparadise.GestionePersonale.Control.GestorePersonale;
import com.agmg.carsparadise.GestionePresenza.Control.GestoreIngressoRemoto;
import com.agmg.carsparadise.Util.ProcessThread;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormIngressoDaRemoto {

    private GestoreIngressoRemoto gestoreIngressoRemoto;

    @FXML
    private Label emailImpiegato;

    @FXML
    private Button gestionePersonaleBtn;

    @FXML
    private Label labelAmministratore;

    @FXML
    private Label labelOrario;

    @FXML
    private Label ruoloImpiegato;

    @FXML
    private TextArea textAreaMotivazione;

   @FXML
    public void initialize() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                labelOrario.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
        };
        timer.start();
       gestionePersonaleBtn.setVisible(Impiegato.getIsAdmin() != 0);
       labelAmministratore.setVisible(Impiegato.getIsAdmin() != 0);
       emailImpiegato.setText(Impiegato.getEmail());
       ruoloImpiegato.setText(Impiegato.getRuolo());
    }

    @FXML
    public void registraMotivazione() {
       //l'oggetto viene istanziato qui perché non intoltra a nessuna pagina aggiuntiva
       gestoreIngressoRemoto = ProcessThread.getGestoreIngressoRemoto();
       gestoreIngressoRemoto.registraMotivazione(textAreaMotivazione.getText().trim(), labelOrario.getText());
    }

    @FXML
    public void vaiAGestioneAccount() {
        GestoreAccount gestoreAccount = new GestoreAccount();
        ProcessThread.setGestoreAccount(gestoreAccount);
    }

    @FXML
    public void vaiAGestioneCarriera() {
        GestoreCarriera gestoreCarriera = new GestoreCarriera();
        ProcessThread.setGestoreCarriera(gestoreCarriera);
    }

    @FXML
    public void vaiAGestionePersonale() {
        GestorePersonale gestorePersonale = new GestorePersonale();
        ProcessThread.setGestorePersonale(gestorePersonale);
    }

}
