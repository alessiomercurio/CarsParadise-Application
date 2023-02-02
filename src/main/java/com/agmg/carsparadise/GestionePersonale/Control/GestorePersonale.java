package com.agmg.carsparadise.GestionePersonale.Control;

import com.agmg.carsparadise.Entity.Impiegato;
import com.agmg.carsparadise.GestionePersonale.Objects.PeriodoIntensivo;
import com.agmg.carsparadise.GestionePersonale.Objects.RecordImpiegato;
import com.agmg.carsparadise.Util.ProcessThread;
import com.agmg.carsparadise.Util.ProcessoDBMS;
import com.agmg.carsparadise.Util.Utils;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;


public class GestorePersonale {

    public GestorePersonale(){
        Utils.cambiaInterfaccia("GestionePersonale/InterfacciaGestionePersonale.fxml", "Gestione del Personale");
    }

    public void registraImpiegato(TextField nomeField, TextField cognomeField, TextField indirizzoField, TextField telField, TextField emailField, TextField passwordField, TextField ibanField, ChoiceBox choiceBoxRuolo, CheckBox checkBoxAmministratore){
        if (nomeField.getText().isBlank() || cognomeField.getText().isBlank() || indirizzoField.getText().isBlank()
                || telField.getText().isBlank() || emailField.getText().isBlank() || passwordField.getText().isBlank()
                || ibanField.getText().isBlank() || choiceBoxRuolo.getSelectionModel().isEmpty()) {
            Utils.creaPannelloErrore("I campi non possono essere vuoti!");
        }else {
            if(ProcessoDBMS.inserisciImpiegato(nomeField.getText().trim(), cognomeField.getText().trim(), indirizzoField.getText().trim(),
                    telField.getText().trim(), emailField.getText().trim(), passwordField.getText().trim(), ibanField.getText().trim(),
                    (String) choiceBoxRuolo.getValue(), checkBoxAmministratore.isSelected())) {
                Utils.creaPannelloInformazione("Impiegato inserito.");
            }
            else {
                Utils.creaPannelloErrore("Impiegato non inserito");
            }
        }
    }

    public void licenziaImpiegato(TextField matricola){
        if(matricola.getText().isBlank()){
            Utils.creaPannelloErrore("I campi non possono essere vuoti!");
        }else if(Integer.parseInt(matricola.getText().trim()) == Impiegato.getMatricola())
            Utils.creaPannelloErrore("Non puoi licenziare te stesso!");
        else{
            try {
                int test = Integer.parseInt(matricola.getText().trim());
                ProcessoDBMS.licenziaImpiegato(matricola.getText().trim());
            } catch (NumberFormatException e) {
                Utils.creaPannelloErrore("Il campo 'matricola' deve contenere solo numeri");
            }
        }
    }

    public void mostraInterfacciaGestionePersonale(){
        Utils.cambiaInterfaccia("GestionePersonale/InterfacciaGestionePersonale.fxml", "Gestione del Personale");
    }

    public ObservableList<RecordImpiegato> recuperaImpiegati(){
        return ProcessoDBMS.recuperaImpiegati();
    }

    public RecordImpiegato getImpiegato(TextField matricolaField){
        if(matricolaField.getText().isBlank()){
            Utils.creaPannelloErrore("I campi non possono essere vuoti!");
        }else{
            try {
                int test = Integer.parseInt(matricolaField.getText().trim());
            } catch (NumberFormatException e) {
                Utils.creaPannelloErrore("Il campo 'matricola' deve contenere solo numeri");
                return null;
            }
            RecordImpiegato recordImpiegato = ProcessoDBMS.recuperaDatiProfiloImpiegato(matricolaField.getText().trim());
            return recordImpiegato;
        }
        return null;
    }

    public void modificaDatiImpiegato(TextField matricolaField, TextField nomeField, TextField cognomeField, TextField indirizzoField, TextField telefonoField , TextField ibanField, TextField emailField, TextField passwordField, CheckBox amministratore, ChoiceBox ruolo){
        int idRuolo = 0;
        if (matricolaField.getText().isBlank() || nomeField.getText().isBlank() || cognomeField.getText().isBlank() || indirizzoField.getText().isBlank()
                || telefonoField.getText().isBlank() || emailField.getText().isBlank() || passwordField.getText().isBlank()
                || ibanField.getText().isBlank() || ruolo.getSelectionModel().isEmpty()) {
            Utils.creaPannelloErrore("I campi non possono essere vuoti!");
        }else
            if(ruolo.getValue().equals("Venditore"))
                idRuolo = 1;
            else if(ruolo.getValue().equals("Noleggiatore"))
                idRuolo = 2;
            else if(ruolo.getValue().equals("Meccanico"))
                idRuolo = 3;
            else
                idRuolo = 4;

            ProcessoDBMS.aggiornaImpiegato(matricolaField.getText().trim(), nomeField.getText().trim(), cognomeField.getText().trim(), indirizzoField.getText().trim(),
                    telefonoField.getText().trim(), ibanField.getText().trim(), emailField.getText().trim(), passwordField.getText().trim(),
                    (amministratore.isSelected() ? 1 : 0), idRuolo);
            if(Impiegato.getMatricola() == Integer.parseInt(matricolaField.getText().trim())){
                Impiegato.setIsAdmin(ProcessoDBMS.recuperaAdmin(Integer.parseInt(matricolaField.getText().trim())));
                Impiegato.setRuolo(ProcessoDBMS.recuperaRuolo(Integer.parseInt(matricolaField.getText().trim())));
                Impiegato.setEmail(ProcessoDBMS.recuperaEmailImpiegato(Integer.parseInt(matricolaField.getText().trim())));
            }
            Utils.creaPannelloInformazione("Dati aggiornati");
            if(Impiegato.getIsAdmin() == 0)
                ProcessThread.getGestoreAccount().mostraInterfacciaAccount();
    }
    public void inserisciPeriodoIntensivo(DatePicker dataInizioFormat, DatePicker dataFineFormat){
        if(dataInizioFormat.getEditor().getText().isBlank() || dataFineFormat.getEditor().getText().isBlank()){
            Utils.creaPannelloErrore("I campi non possono essere vuoti");
        }else if(dataInizioFormat.getValue() == null || dataFineFormat.getValue() == null){
            Utils.creaPannelloErrore("Non è possibile inserire stringhe di testo");
        }else{
            try{
                String dataInizio = dataInizioFormat.getValue().format(DateTimeFormatter.ofPattern("YYYY-MM-DD"));
                String dataFine = dataFineFormat.getValue().format(DateTimeFormatter.ofPattern("YYYY-MM-DD"));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date inizio = sdf.parse(dataInizio);
                Date fine = sdf.parse(dataFine);
                Date oggi = sdf.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
               //  || !(fine.equals(oggi))
                if(inizio.before(oggi) || inizio.after(fine) || fine.before(oggi) || (inizio.equals(oggi) || (fine.equals(oggi)))) {
                    Utils.creaPannelloErrore("Errore nell'inserimento del periodo");
                    return;
                }
                ProcessoDBMS.inserisciPeriodoIntensivo(dataInizio, dataFine);
            }catch(ParseException e){
                Utils.creaPannelloErrore("Errore di sistema");
            }
        }
    }

    public ObservableList<PeriodoIntensivo> recuperaPeriodiIntensivi(){
        return ProcessoDBMS.richiediPeriodoIntensivo();
    }

    public int eliminaPeriodoIntensivo(PeriodoIntensivo periodo){
        return ProcessoDBMS.eliminaPeriodoIntensivo(periodo);
    }

    public void mostraFormModificaDatiImpiegati(){
        Utils.cambiaInterfaccia("GestionePersonale/FormPrecompilatoImpiegato.fxml", "Modifica dati impiegato");
    }
    public void mostraInserisciPeriodoIntensivo(){
        Utils.cambiaInterfaccia("GestionePersonale/SelezionePeriodo.fxml", "Seleziona Periodo Intensivo");
    }
    public void mostraEliminaPeriodoIntensivo(){
        Utils.cambiaInterfaccia("GestionePersonale/EliminaPeriodo.fxml", "Elimina Periodo Intensivo");
    }
    public void mostraFormInserisciImpiegato(){
        Utils.cambiaInterfaccia("GestionePersonale/FormRegistrazionePersonale.fxml", "Inserisci nuovo impiegato");
    }
    public void mostraFormLicenziaImpiegato(){
        Utils.cambiaInterfaccia("GestionePersonale/LicenziamentoImpiegato.fxml", "Licenziamento Impiegato");
    }
    public void mostraImpiegati(){
        Utils.cambiaInterfaccia("GestionePersonale/TabellaImpiegati.fxml", "Tabella Impiegati");
    }
}