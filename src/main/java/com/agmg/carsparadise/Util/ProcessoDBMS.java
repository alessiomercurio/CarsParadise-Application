package com.agmg.carsparadise.Util;

import com.agmg.carsparadise.Entity.Impiegato;
import com.agmg.carsparadise.GestioneCarriera.Objects.Astensione;
import com.agmg.carsparadise.GestioneCarriera.Objects.Ritardo;
import com.agmg.carsparadise.GestioneCarriera.Objects.Stipendio;
import com.agmg.carsparadise.GestioneCarriera.Objects.Turno;
import com.agmg.carsparadise.GestionePersonale.Objects.PeriodoIntensivo;
import com.agmg.carsparadise.GestionePersonale.Objects.RecordImpiegato;
import com.agmg.carsparadise.GestionePresenza.Object.TurnoPresenza;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ProcessoDBMS {

    //aprire la connessione con il DB
    public static Connection connectToDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/CarsParadise", //url
                    "root", //username
                    "rootalex" //password
            );
        } catch (SQLException e) {
            ErroreDBMS.erroreGenericoDBMS(e);
            e.printStackTrace();
        }
        return conn;
    }

    //chiudere la connessione con il DB
    public static void closeConnectionToDatabase(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            ErroreDBMS.erroreGenericoDBMS(e);
        }
    }

    // Metodo per reuperare l'email dell'impiegato dopo aver effettuato il login
    public static String recuperaEmailImpiegato(int matricola) {
        String email = " ";
        try {
            Connection conn = connectToDatabase();
            String query = "SELECT Email" +
                    " FROM Impiegato" +
                    " WHERE Matricola = " + matricola;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                email = rs.getString("Email");
            }
            closeConnectionToDatabase(conn);
        } catch (SQLException e) {
            ErroreDBMS.erroreGenericoDBMS(e);
        }
        return email;
    }

    //recupera se l'impiegato ha ruolo di Impiegato (0) o Admin (1)
    public static int recuperaAdmin(int matricola) {
        int isAdmin = 0;
        try {
            Connection conn = connectToDatabase();
            String query = "SELECT is_Admin" +
                    " FROM Impiegato" +
                    " WHERE Matricola = " + matricola;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                isAdmin = rs.getInt("is_Admin");
            }
            closeConnectionToDatabase(conn);
        } catch (SQLException e) {
            ErroreDBMS.erroreGenericoDBMS(e);
        }
        return isAdmin;
    }

    //recupera la descrizione del ruolo
    public static String recuperaRuolo(int matricola) {
        String ruolo = " ";
        try {
            Connection conn = connectToDatabase();
            String query = "SELECT R.descrizione" +
                    " FROM Impiegato I, Ruolo R" +
                    " WHERE I.Matricola = " + matricola +
                    " AND I.Ref_Ruolo = R.id_Ruolo";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                ruolo = rs.getString("R.Descrizione");
            }
            closeConnectionToDatabase(conn);
        } catch (SQLException e) {
            ErroreDBMS.erroreGenericoDBMS(e);
        }
        return ruolo;
    }

    public static String getPassword(int matricola) {
        String password = " ";
        try {
            Connection conn = connectToDatabase();
            String query = "SELECT I.password" +
                    " FROM Impiegato I " +
                    " WHERE I.Matricola = " + matricola;

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                password = rs.getString("I.password");
            }
            closeConnectionToDatabase(conn);
        } catch (SQLException e) {
            ErroreDBMS.erroreGenericoDBMS(e);
        }
        return password;
    }

    // TODO: Dobbiamo lasciare solo le query
    public static int verificaCredenziali(int matricola, String password) throws SQLException {
        ProcessoDBMS.connectToDatabase();

        String query = "SELECT COUNT(1) " +
                " FROM Impiegato" +
                " WHERE matricola = " + matricola +
                " AND password = '" + password + "'";

        try {
            Statement st = ProcessoDBMS.connectToDatabase().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                if (rs.getInt(1) == 1) {
                    return 1;
                } else {
                    return 0;
                }
            }
        } catch (SQLException e) {
            ErroreDBMS.erroreGenericoDBMS(e);
        }
        return 0;
    }

    public static void aggiornaPassword(String password) {
        ProcessoDBMS.connectToDatabase();

        String query = "UPDATE Impiegato" +
                " SET Password = " + "'" + password + "'" +
                " WHERE Matricola = " + Impiegato.getMatricola();
        try {
            Statement st = ProcessoDBMS.connectToDatabase().prepareStatement(query);
            int result = st.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            ErroreDBMS.erroreGenericoDBMS(e);
        }
    }

    public static void aggiornaPassword(String password, int matricola) {
        ProcessoDBMS.connectToDatabase();

        String query = "UPDATE Impiegato" +
                " SET Password = " + "'" + password + "'" +
                " WHERE Matricola = " + matricola;
        try {
            Statement st = ProcessoDBMS.connectToDatabase().prepareStatement(query);
            int result = st.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            ErroreDBMS.erroreGenericoDBMS(e);
        }
    }

    public static int recuperaMatricola(int matricola) {
        int matricolaDb = -1;
        try {
            Connection conn = connectToDatabase();
            String query = "SELECT Matricola" +
                    " FROM Impiegato " +
                    " WHERE Matricola = " + matricola;

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                matricolaDb = rs.getInt("Matricola");
            }
            closeConnectionToDatabase(conn);
        } catch (SQLException e) {
            ErroreDBMS.erroreGenericoDBMS(e);
        }
        return matricolaDb;
    }

    public static ArrayList<String> recuperaDatiProfilo() {

        ArrayList<String> arrayDati = new ArrayList();
        String[] dati = new String[]{"Indirizzo", "Telefono", "IBAN"};

        try {
            Connection conn = connectToDatabase();
            String query = "SELECT Indirizzo, Telefono, IBAN" +
                    " FROM Impiegato " +
                    " WHERE Matricola = " + Impiegato.getMatricola();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                for (int i = 0; i < 3; ++i)
                    arrayDati.add(rs.getString(dati[i]));
            }
            closeConnectionToDatabase(conn);
        } catch (SQLException e) {
            ErroreDBMS.erroreGenericoDBMS(e);
        }
        return arrayDati;
    }

    public static void aggiornaImpiegato(String indirizzo, String telefono, String IBAN) {
        Connection conn = connectToDatabase();

        String query = "UPDATE Impiegato" +
                " SET Indirizzo = " + "'" + indirizzo + "'" + "," +
                " Telefono = " + "'" + telefono + "'" + "," +
                " IBAN = " + "'" + IBAN + "'" +
                " WHERE Matricola = " + Impiegato.getMatricola();
        try {
            Statement st = ProcessoDBMS.connectToDatabase().prepareStatement(query);
            int result = st.executeUpdate(query);
            closeConnectionToDatabase(conn);
        } catch (SQLException e) {
            ErroreDBMS.erroreGenericoDBMS(e);
        }
    }

    public static ObservableList<Stipendio> recuperaStipendi() {
        String data = "";
        double importo = 0.0;
        ObservableList<Stipendio> listaStipendi = FXCollections.observableArrayList();

        try {
            Connection conn = connectToDatabase();
            String query = " SELECT S.Importo, S.Data" +
                    " FROM Stipendio S, Impiegato I" +
                    " WHERE S.Ref_Impiegato_S = I.Matricola " +
                    " AND I.Matricola = " + Impiegato.getMatricola();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                importo = rs.getDouble("S.Importo");
                data = rs.getString("S.Data");
                listaStipendi.add(new Stipendio(data, importo));
            }
            closeConnectionToDatabase(conn);
        } catch (SQLException e) {
            ErroreDBMS.erroreGenericoDBMS(e);
        }
        return listaStipendi;
    }

    // metodo per il recupero password
    public String getEmail(int matricola) {
        String email = " ";
        try {
            Connection conn = connectToDatabase();
            String query = " SELECT Email" +
                    " FROM Impiegato" +
                    " WHERE Matricola = " + matricola;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                email = rs.getString("Email");
            }
            closeConnectionToDatabase(conn);
        } catch (SQLException e) {
            ErroreDBMS.erroreGenericoDBMS(e);
        }
        return email;
    }

    public static ObservableList<Ritardo> recuperaTurniConRitardo() {
        String data = "";
        String motivazioneRitardo = "";
        ObservableList<Ritardo> listaRitardi = FXCollections.observableArrayList();

        try {
            Connection conn = connectToDatabase();
            String query = " SELECT T.Data_Inizio, I2.Motivazione_Ritardo" +
                    " FROM Impiegato I, Ingresso I2, Turno T" +
                    " WHERE I.Matricola = I2.Ref_Impiegato " +
                    " AND I2.Ref_Impiegato = T.Ref_Impiegato " +
                    " AND I2.Ritardo = 1 " +
                    " AND I2.Ref_Turno = T.id_Turno " +
                    " AND I.Matricola = " + Impiegato.getMatricola();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                data = rs.getString("T.Data_Inizio");
                motivazioneRitardo = rs.getString("I2.Motivazione_Ritardo");
                listaRitardi.add(new Ritardo(data.split(" ")[0], motivazioneRitardo));
            }
            closeConnectionToDatabase(conn);
        } catch (SQLException e) {
            ErroreDBMS.erroreGenericoDBMS(e);
        }
        return listaRitardi;
    }

    public static int restituisciID_Ruolo(String ruolo){
       if(ruolo.equals("Venditore")){
           return 1;
       }else if(ruolo.equals("Noleggiatore")){
           return 2;
       }else if(ruolo.equals("Meccanico")){
           return 3;
       }else return 4;
    }

    public static boolean inserisciImpiegato(String nome, String cognome, String indirizzo, String telefono, String email, String password, String iban, String ruolo, boolean isAdmin) {
        int result = 0;
        try {
            Connection conn = connectToDatabase();
            String query = "INSERT INTO Impiegato(Nome, Cognome, Indirizzo, Telefono, IBAN, Email, Password, is_Admin, Ref_Ruolo)" +
                    " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, nome);
            stm.setString(2, cognome);
            stm.setString(3, indirizzo);
            stm.setString(4, telefono);
            stm.setString(5, iban);
            stm.setString(6, email);
            stm.setString(7, password);
            stm.setInt(8, (isAdmin ? 1 : 0));
            stm.setInt(9, restituisciID_Ruolo(ruolo));
            result = stm.executeUpdate();
            connectToDatabase().close();
        } catch (SQLException e) {
            ErroreDBMS.erroreGenericoDBMS(e);
            e.printStackTrace();
        } return result > 0;
    }

    //da fare nella control
    public static void licenziaImpiegato(String matricola){
        int matricolaDb = Integer.parseInt(matricola);

        try{
            Connection conn = connectToDatabase();
            String query = "DELETE FROM Impiegato" +
                             " WHERE Matricola = " + matricolaDb;
            PreparedStatement stmt = conn.prepareStatement(query);
            int success = stmt.executeUpdate();
            if(success != 0)
                Utils.creaPannelloInformazione("Impiegato licenziato.");
            else
                Utils.creaPannelloErrore("Nessun Impiegato trovato.");
        }catch (SQLException e){
            ErroreDBMS.erroreGenericoDBMS(e);
        }
    }

    public static ObservableList<RecordImpiegato> recuperaImpiegati(){
        int matricola = 0;
        String nome = "";
        String cognome = "";
        String indirizzo = "";
        String telefono = "";
        String iban = "";
        String email = "";
        String password = "";
        int isAdmin = 0;
        int idRuolo = 0;
        ObservableList<RecordImpiegato> listaImpiegati = FXCollections.observableArrayList();

        try {
            Connection conn = connectToDatabase();
            String query = " SELECT *" +
                    " FROM Impiegato";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                matricola = rs.getInt("Matricola");
                nome = rs.getString("Nome");
                cognome = rs.getString("Cognome");
                indirizzo = rs.getString("Indirizzo");
                telefono = rs.getString("Telefono");
                iban = rs.getString("Iban");
                email = rs.getString("Email");
                password = rs.getString("Password");
                isAdmin = (rs.getInt("is_Admin"));
                idRuolo = rs.getInt("Ref_Ruolo");
                listaImpiegati.add(new RecordImpiegato(matricola, nome, cognome, indirizzo, telefono, iban, email, password, isAdmin, idRuolo));
            }
            closeConnectionToDatabase(conn);
        } catch (SQLException e) {
            ErroreDBMS.erroreGenericoDBMS(e);
        }
        return listaImpiegati;
    }

    public static RecordImpiegato recuperaDatiProfiloImpiegato(String matricola) {
        String nome = "";
        String cognome = "";
        String indirizzo = "";
        String telefono = "";
        String iban = "";
        String email = "";
        String password = "";
        int isAdmin = 0;
        int idRuolo = 0;
        try {
            Connection conn = connectToDatabase();
            String query = "SELECT Nome, Cognome, Indirizzo, Telefono, IBAN, Email, Password, is_Admin, Ref_Ruolo" +
                    " FROM Impiegato " +
                    " WHERE Matricola = " + matricola;

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                nome = rs.getString("Nome");
                cognome = rs.getString("Cognome");
                indirizzo = rs.getString("Indirizzo");
                telefono = rs.getString("Telefono");
                iban = rs.getString("Iban");
                email = rs.getString("Email");
                password = rs.getString("Password");
                isAdmin = (rs.getInt("is_Admin"));
                idRuolo = rs.getInt("Ref_Ruolo");
            }
            closeConnectionToDatabase(conn);
        } catch (SQLException e) {
            ErroreDBMS.erroreGenericoDBMS(e);
        }
        return new RecordImpiegato(Integer.parseInt(matricola), nome, cognome, indirizzo, telefono, iban, email, password, isAdmin, idRuolo);
    }

    public static void aggiornaImpiegato(String matricola, String nome, String cognome, String indirizzo,
                                         String telefono, String IBAN, String email, String password,
                                         int isAdmin, int refRuolo) {
        int matricolaDb = Integer.parseInt(matricola);
        Connection conn = connectToDatabase();
        String query = "UPDATE Impiegato" +
                " SET Nome = " + "'" + nome + "'" + "," +
                " Nome = " + "'" + nome + "'" + "," +
                " Cognome = " + "'" + cognome + "'" + "," +
                " Indirizzo = " + "'" + indirizzo + "'" + "," +
                " Telefono = " + "'" + telefono + "'" + "," +
                " IBAN = " + "'" + IBAN + "'" + "," +
                " Email = " + "'" + email + "'" + "," +
                " Password = " + "'" + password + "'" + "," +
                " is_Admin = " + "'" + isAdmin + "'" + "," +
                " Ref_Ruolo = " + "'" + refRuolo + "'" +
                " WHERE Matricola = " + matricolaDb;
        try {
            Statement st = ProcessoDBMS.connectToDatabase().prepareStatement(query);
            int result = st.executeUpdate(query);
            closeConnectionToDatabase(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            ErroreDBMS.erroreGenericoDBMS(e);
        }
    }

    public static void inserisciPeriodoIntensivo(String dataInizio, String dataFine){
        try {
            Connection conn = connectToDatabase();
            String query = "INSERT INTO PeriodoIntensivo(Data_Inizio, Data_Fine, Ref_Impiegato)" +
                    " VALUES(?, ?, ?)";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, dataInizio);
            stm.setString(2, dataFine);
            stm.setInt(3, Impiegato.getMatricola());
            stm.executeUpdate();
            connectToDatabase().close();
            Utils.creaPannelloInformazione("Periodo intensivo inserito.");
        } catch (SQLException e) {
            ErroreDBMS.erroreGenericoDBMS(e);
        }
    }

    public static ObservableList<PeriodoIntensivo> richiediPeriodoIntensivo(){
        String dataInizio = "";
        String dataFine = "";
        ObservableList<PeriodoIntensivo> listaPeriodiIntensivi = FXCollections.observableArrayList();

        try {
            Connection conn = connectToDatabase();
            String query = " SELECT Data_Inizio, Data_Fine" +
                    " FROM PeriodoIntensivo";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                dataInizio = rs.getString("Data_Inizio");
                dataFine = rs.getString("Data_Fine");
                listaPeriodiIntensivi.add(new PeriodoIntensivo(dataInizio, dataFine));
            }
            closeConnectionToDatabase(conn);
        } catch (SQLException e) {
            ErroreDBMS.erroreGenericoDBMS(e);
        }
        return listaPeriodiIntensivi;
    }

    public static int eliminaPeriodoIntensivo(PeriodoIntensivo periodo){
        int success = 0;
        try{
            Connection conn = connectToDatabase();
            String query = "DELETE FROM PeriodoIntensivo" +
                    " WHERE Data_Inizio = " + "'" + periodo.getDataInizio() + "'" +
                    " AND Data_Fine = " + "'" + periodo.getDataFine() + "'";
            PreparedStatement stmt = conn.prepareStatement(query);
            success = stmt.executeUpdate();
            if(success != 0)
                Utils.creaPannelloInformazione("Periodo intensivo eliminato.");
            else
                Utils.creaPannelloInformazione("Nessun periodo intensivo trovato.");
        }catch (SQLException e){
            ErroreDBMS.erroreGenericoDBMS(e);
        }
        return success;
    }

    // recuperare i turni di un determinato giorno
    public static ArrayList<TurnoPresenza> recuperaTurni(String data){
        int idTurno = 0;
        String dataTurno = "";
        String orarioTurno = "";
        ArrayList<TurnoPresenza> listaTurni = new ArrayList<TurnoPresenza>();

        try {
            Connection conn = connectToDatabase();
            String query = " SELECT id_Turno, date(Data_Inizio) as Data,  time(Data_Inizio) as Orario" +
                    " FROM Turno T" +
                    " WHERE T.Ref_Impiegato = " + Impiegato.getMatricola() +
                    " AND date(Data_Inizio) = " + "'" + data + "'" ;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                idTurno = rs.getInt("id_Turno");
                dataTurno = rs.getString("Data");
                orarioTurno = rs.getString("Orario");
                listaTurni.add(new TurnoPresenza(idTurno, dataTurno, orarioTurno));
            }
            closeConnectionToDatabase(conn);
        } catch (SQLException e) {
            ErroreDBMS.erroreGenericoDBMS(e);
        } return listaTurni;
    }

    public static boolean registraMotivazioneRitardo(String motivazioneRitardo, String data, String orario, int idTurno){
        int success = 0;
        try {
            Connection conn = connectToDatabase();
            String query = "INSERT INTO Ingresso" +
                    " VALUES(?, ?, ?, ?)";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, Impiegato.getMatricola());
            stm.setInt(2, idTurno);
            stm.setInt(3, 1);
            stm.setString(4, motivazioneRitardo);
            success = stm.executeUpdate();
            connectToDatabase().close();
        } catch (SQLException e) {
            e.printStackTrace();
            ErroreDBMS.erroreGenericoDBMS(e);
        }
        return success > 0;
    }

    public static ObservableList<Turno> recuperaTurniTrimestre(String data){
        ObservableList<Turno> listaTurni = FXCollections.observableArrayList();
        int idTurno = 0;
        LocalDate dataOdierna = LocalDate.parse(data).plusMonths(3);

        String dataInizioTurno = "";
        String orarioInizioTurno = "";
        String dataFineTurno = "";
        String orarioFineTurno = "";
        String servizio = "";
        int isStraordinario = 0;
        try {
            Connection conn = connectToDatabase();
            String query = " SELECT id_Turno, date(Data_Inizio) as DataInizio,  time(Data_Inizio) as OrarioInizio," +
                    " date(Data_Fine) as DataFine, time(Data_Fine) as OrarioFine, Servizio, isStraordinario" +
                    " FROM Turno T" +
                    " WHERE T.Ref_Impiegato = " + Impiegato.getMatricola() +
                    " AND date(Data_Inizio) >= date(" + "'" + data + "')" + " AND date(Data_Inizio) <= " + "date('" + dataOdierna.toString() + "')";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                idTurno = rs.getInt("id_Turno");
                dataInizioTurno = rs.getString("DataInizio");
                orarioInizioTurno = rs.getString("OrarioInizio");
                dataFineTurno = rs.getString("DataFine");
                orarioFineTurno = rs.getString("OrarioFine");
                servizio = rs.getString("Servizio");
                isStraordinario = rs.getInt("isStraordinario");
                listaTurni.add(new Turno(idTurno, dataInizioTurno, dataFineTurno, orarioInizioTurno, orarioFineTurno, servizio, isStraordinario));
            }
            closeConnectionToDatabase(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            ErroreDBMS.erroreGenericoDBMS(e);
        } return listaTurni;
    }

    public static boolean inserisciPeriodoAstensione(String dataInzio, String dataFine, String motivazione){
        int success = 0;
        try {
            Connection conn = connectToDatabase();
            String query = "INSERT INTO Astensione(Data_Inizio, Data_Fine, Motivazione, Ref_Impiegato)" +
                    " VALUES(?, ?, ?, ?)";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, dataInzio);
            stm.setString(2, dataFine);
            stm.setString(3, motivazione);
            stm.setInt(4, Impiegato.getMatricola());
            success = stm.executeUpdate();
            closeConnectionToDatabase(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            ErroreDBMS.erroreGenericoDBMS(e);
        }
        return success > 0;
    }

    public static void eliminaTurni(String dataInizio, String dataFine, int matricola){
        try{
            Connection conn = connectToDatabase();
            String query = "DELETE FROM Turno" +
                    " WHERE date(Data_Inizio) BETWEEN " + "date('" + dataInizio + "') AND date('" + dataFine + "')" +
                    " AND ref_impiegato = " + matricola;
            PreparedStatement stmt = conn.prepareStatement(query);
            int success = stmt.executeUpdate();
            closeConnectionToDatabase(conn);
        }catch (SQLException e){
            e.printStackTrace();
            ErroreDBMS.erroreGenericoDBMS(e);
        }
    }
}