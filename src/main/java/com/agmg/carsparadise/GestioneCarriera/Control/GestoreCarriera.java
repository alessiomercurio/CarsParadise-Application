package com.agmg.carsparadise.GestioneCarriera.Control;

import com.agmg.carsparadise.Entity.Impiegato;
import com.agmg.carsparadise.GestioneCarriera.Objects.Ritardo;
import com.agmg.carsparadise.GestioneCarriera.Objects.Stipendio;
import com.agmg.carsparadise.GestioneCarriera.Objects.Turno;
import com.agmg.carsparadise.GestionePersonale.Objects.PeriodoIntensivo;
import com.agmg.carsparadise.GestionePersonale.Objects.RecordImpiegato;
import com.agmg.carsparadise.GestionePresenza.Object.TurnoPresenza;
import com.agmg.carsparadise.Util.ProcessThread;
import com.agmg.carsparadise.Util.ProcessoDBMS;
import com.agmg.carsparadise.Util.Utils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class GestoreCarriera {

    public GestoreCarriera() {
        Utils.cambiaInterfaccia("GestioneCarriera/InterfacciaGestioneCarriera.fxml", "Gestione Carriera");
    }

    public ObservableList<Stipendio> recuperaStipendi() {
        return ProcessoDBMS.recuperaStipendi();
    }

    public ObservableList<Turno> recuperaTurniTrimestre(){
        String data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return ProcessoDBMS.recuperaTurniTrimestre(data.split(" ")[0]);
    }
    public ObservableList<Ritardo> recuperaTurniConRitardo(){
        return ProcessoDBMS.recuperaTurniConRitardo();
    }

    /*
     * Verificare se l'inizio e la fine di una richiesta, almeno un giorno è contenuto nel periodo intensivo
     *
     * Astensione
     *                                   | ------- |
     *                    |----------|
     * 10 - 15
     * 12 - 14
     *
     * CONTROLLO CHE fineAstensione > InizioAStensione
     * 1) fineAstensione < inzioIntensivo
     * 2) inizioAstensione > fineIntensivo
     *
     * */
    public void inserisciPeriodoAstensione(DatePicker dataInizio, DatePicker dataFine, String motivazione) {
        if (dataInizio.getEditor().getText().isBlank() || dataFine.getEditor().getText().isBlank() || motivazione.isBlank()) {
            Utils.creaPannelloErrore("I campi non possono essere vuoti");
            return;
        }
        else if(dataInizio.getValue() == null || dataFine.getValue() == null){
            Utils.creaPannelloErrore("Non è possibile inserire stringhe di testo");
        }

        String dataInizioAstensione = dataInizio.getValue().format(DateTimeFormatter.ofPattern("YYYY-MM-DD"));
        System.out.println(dataInizioAstensione);
        System.out.println(dataInizio.getValue().toString());
        String dataFineAstensione = dataFine.getValue().format(DateTimeFormatter.ofPattern("YYYY-MM-DD"));

        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date inizioAstensione = sdf.parse(dataInizioAstensione);
            Date fineAstensione = sdf.parse(dataFineAstensione);
            if(inizioAstensione.after(fineAstensione)){
                Utils.creaPannelloInformazione("Errore di inserimento nelle date");
            }
            else if(dataInizio.getValue().format(DateTimeFormatter.ofPattern("YYYY-MM-DD")).equals(LocalDate.now().format(DateTimeFormatter.ofPattern("YYYY-MM-DD")))) {
                Utils.creaPannelloErrore("Non si può inserire una astensione per il giorno stesso");
            }else if (motivazione.equals("Ferie")) {
                    ObservableList<PeriodoIntensivo> listaPeriodi = ProcessoDBMS.richiediPeriodoIntensivo();
                    for (PeriodoIntensivo periodo : listaPeriodi) {
                        Date inizioPeriodo = sdf.parse(periodo.getDataInizio());
                        Date finePeriodo = sdf.parse(periodo.getDataFine());

                        if (fineAstensione.before(inizioPeriodo) || inizioAstensione.after(finePeriodo)) {
                            if (ProcessoDBMS.inserisciPeriodoAstensione(dataInizioAstensione, dataFineAstensione, motivazione)) {
                                ProcessoDBMS.eliminaTurni(dataInizioAstensione, dataFineAstensione, Impiegato.getMatricola());
                                Utils.creaPannelloInformazione("Richiesta di ferie effettuata");
                            } else {
                                Utils.creaPannelloErrore("Non è stato possibile inserire la richiesta di ferie");
                            }
                        } else {
                            Utils.creaPannelloErrore("Non puoi richiedere delle ferie durante un periodo intensivo");
                        }
                    }
                } else {
                    if(ProcessoDBMS.inserisciPeriodoAstensione(dataInizioAstensione, dataFineAstensione, motivazione)){
                        ProcessoDBMS.eliminaTurni(dataInizioAstensione, dataFineAstensione, Impiegato.getMatricola());
                        Utils.creaPannelloInformazione("Periodo di astensione inserito");
                    }else{
                        Utils.creaPannelloErrore("Errore nell'inserire il periodo di astensione");
                    }
                }
        }catch (ParseException p) {
            p.printStackTrace();
            Utils.creaPannelloErrore("Errore nell'inserire il periodo di astensione");
        }
    }

    public void mostraInterfacciaGestioneCarriera() {
        Utils.cambiaInterfaccia("GestioneCarriera/InterfacciaGestioneCarriera.fxml", "Gestione Carriera");
    }

    public void mostraVisStipendi() {
        Utils.cambiaInterfaccia("GestioneCarriera/TabellaStipendi.fxml", "Tabella stipendi");
    }

    //mostraVisTurni
    public void cliccaVisTurni() {
        Utils.cambiaInterfaccia("GestioneCarriera/TabellaTurni.fxml", "Tabella turni");
    }

    public void mostraVisRitardi(){
        Utils.cambiaInterfaccia("GestioneCarriera/TabellaRitardi.fxml", "Tabella Ritardi");
    }

    public void mostraRichiediAstensione() {
        Utils.cambiaInterfaccia("GestioneCarriera/SelezionePeriodoAstensione.fxml" ," Selezione periodo astensione");
    }

}




