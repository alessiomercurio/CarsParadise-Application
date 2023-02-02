module com.agmg.carsparadise {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.net.http;
    requires java.mail;


    opens com.agmg.carsparadise to javafx.fxml;
    exports com.agmg.carsparadise;
    exports com.agmg.carsparadise.GestioneAccount.Interface;
    exports com.agmg.carsparadise.GestioneCarriera.Interface;
    exports com.agmg.carsparadise.GestionePersonale.Interface;
    exports com.agmg.carsparadise.GestioneCarriera.Objects;
    exports com.agmg.carsparadise.GestionePersonale.Objects;
    exports com.agmg.carsparadise.GestionePresenza.Interface;

    opens com.agmg.carsparadise.GestioneAccount.Interface to javafx.fxml;
    opens com.agmg.carsparadise.GestioneCarriera.Interface to javafx.fxml;
    opens com.agmg.carsparadise.GestioneCarriera.Objects to javafx.fxml;
    opens com.agmg.carsparadise.GestionePersonale.Interface to javafx.fxml;
    opens com.agmg.carsparadise.GestionePersonale.Objects to javafx.fxml;
    opens com.agmg.carsparadise.GestionePresenza.Interface to javafx.fxml;
}