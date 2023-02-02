package com.agmg.carsparadise.GestioneAccount.Control;


import com.agmg.carsparadise.Util.ProcessoDBMS;
import com.agmg.carsparadise.Util.ServerMail;
import com.agmg.carsparadise.Util.Utils;
import javafx.scene.control.TextField;

import java.util.Random;

public class GestoreRecuperoPassword {

    public GestoreRecuperoPassword(){
        Utils.cambiaInterfaccia("GestioneAccount/RecuperoPassw.fxml", "Recupero password");
    }
    public void recuperaPassword(TextField matricola) {
        if (matricola.getText().trim().isBlank()) {
            Utils.creaPannelloErrore("Il campo matricola non può essere vuoto!");
            return;
        }

        try {
            int matr = Integer.parseInt(matricola.getText().trim());
        } catch (NumberFormatException e) {
            Utils.creaPannelloErrore("Il campo 'matricola' deve contenere solo numeri");
            return;
        }
        // Verifichiamo la matricola se corrisponde
        int matricolaDb = ProcessoDBMS.recuperaMatricola(Integer.parseInt(matricola.getText().trim()));

        if (confrontaMatricole(matricolaDb, Integer.parseInt(matricola.getText().trim()))) {
            String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
            StringBuilder rndPassw = new StringBuilder();
            Random rnd = new Random();
            while (rndPassw.length() < 18) { // length of the random string.
                int index = (int) (rnd.nextFloat() * CHARS.length());
                rndPassw.append(CHARS.charAt(index));
            }
            ProcessoDBMS.aggiornaPassword(rndPassw.toString(), Integer.parseInt(matricola.getText().trim()));
            //effettuare metodo invio email qui
            // richiamare il metodo per inviare l`email MailServer.sendEmail()
            ServerMail.sendEmail(ProcessoDBMS.recuperaEmailImpiegato(matricolaDb), "La password provvisoria e': " + rndPassw.toString(), "Password aggiornata");
            Utils.creaPannelloInformazione("È stata inviata una email con la password provvisoria");
        } else {
            Utils.creaPannelloErrore("Impiegato non trovato");
        }

    }

    private boolean confrontaMatricole(int matricolaDb, int matricola) {
        return (matricolaDb == matricola); //1 uguali, 0 diversi
    }

    public void mostraSchermataLogin(){
        Utils.cambiaInterfaccia("GestioneAccount/Login.fxml", "Login");
    }
}
