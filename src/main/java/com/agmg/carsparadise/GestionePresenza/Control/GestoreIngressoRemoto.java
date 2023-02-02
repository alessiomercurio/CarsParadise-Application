package com.agmg.carsparadise.GestionePresenza.Control;

import com.agmg.carsparadise.GestionePresenza.Object.TurnoPresenza;
import com.agmg.carsparadise.Util.ProcessoDBMS;
import com.agmg.carsparadise.Util.Utils;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;

public class GestoreIngressoRemoto {

    public GestoreIngressoRemoto(){
        Utils.cambiaInterfaccia("GestionePresenza/FormIngressoDaRemoto.fxml", "Ingresso da remoto");
    }

    //2023-01-10 12:35:05
    public void registraMotivazione(String motivazione, String dataEOrario){// splittare la data con uno spazio
        String ore = dataEOrario.split(" ")[1].split(":")[0];
        String minuti = dataEOrario.split(" ")[1].split(":")[1];
        StringBuilder oreEMinuti = new StringBuilder();
        oreEMinuti.append(ore);
        oreEMinuti.append(":");
        oreEMinuti.append(minuti);

        int idTurno = controllaOrarioTurno(dataEOrario.split(" ")[0], oreEMinuti.toString());

        if(motivazione.isBlank())
            Utils.creaPannelloErrore("Il campo non può essere vuoto");
        //aggiungere controllo per vedere se il campo ha caratteri speciali
        else if(idTurno != -1){
            if(ProcessoDBMS.registraMotivazioneRitardo(motivazione, dataEOrario.split(" ")[0], oreEMinuti.toString(), idTurno)){
                Utils.creaPannelloInformazione("Ritardo registrato");
            }else{
                Utils.creaPannelloErrore("Errore nel registrare il turno");
            }
        }else
            Utils.creaPannelloErrore("Non e' possibile timbrare");
    }
    
    public int controllaOrarioTurno(String data, String oraEMinuti){
        ArrayList<TurnoPresenza> listaTurni = ProcessoDBMS.recuperaTurni(data);

        for (TurnoPresenza turnoPresenza : listaTurni){
            StringBuilder orarioEMinutiTurno = new StringBuilder();
            orarioEMinutiTurno.append(turnoPresenza.getOrario().split(":")[0]);
            orarioEMinutiTurno.append(":");
            orarioEMinutiTurno.append(turnoPresenza.getOrario().split(":")[1]);
            // HH:mm
            // Dobbiamo confrontare se l'impiegato sta registrando la presenza dopo 10 min
            //orarioEMinutiTurno.toString().equals(oraEMinuti)
            LocalTime inizioTurno = LocalTime.parse(orarioEMinutiTurno);
            LocalTime registroIngressoTurno = LocalTime.parse(oraEMinuti);
            Duration duration = Duration.between( inizioTurno, registroIngressoTurno );
            // Se va oltre i 10 minuti, allora duration sarà +10
            if(duration.toMinutes() > 10 && duration.toMinutes() < 60){
                return turnoPresenza.getIdTurno();
            }
        }
        return -1;
    }
}
