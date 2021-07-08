package start.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import start.normaleKlassen.AlleReservierungen;
import start.normaleKlassen.Datenbank;
import start.normaleKlassen.Mitarbeiter;
import start.normaleKlassen.Szenenwechsel;

import java.io.*;
import java.sql.*;

public class ControllerAnmeldung {

    @FXML
    private Button registrierenScene;

    @FXML
    private Button anmeldenKnopf;

    @FXML
    public TextField mitarbeiterIDFeld;

    @FXML
    private PasswordField passwortfeld;

    @FXML
    private Label fehleranzeige;


    private Stage window;



    //------------------------------------Auf Szene Registrierung umswitchen-----------------------------
    @FXML
    void registrierenSwitch(ActionEvent event) throws IOException {
        Szenenwechsel.reservierungsScene(window, event, registrierenScene, getClass());
    }
    //------------------------------------------------------------------------------------------------------------



    //---------------------------------Anmeldungsprozess--------------------------------------------------

    //-------------Anmeldung prüfen-------------------------
    private int id;

    private void anmeldungPr(ActionEvent event) throws IOException {

        String idStr = mitarbeiterIDFeld.getText();

        if(idStr.matches("[0-9]+")) {

            id =  Integer.parseInt(idStr);
            String passwort = passwortfeld.getText();

            String passwortCheck = "";

            try (Connection con = DriverManager.getConnection(Datenbank.dbURL); Statement stmt = con.createStatement();) {
                String checken = ("SELECT Passwort FROM dbo.Mitarbeiter WHERE MitarbeiterID = "+id);
                ResultSet resultSet = stmt.executeQuery(checken);

                if (resultSet.next()) {
                    passwortCheck = resultSet.getString("Passwort");
                }
            } catch (SQLException e){
                e.printStackTrace();
            }

            if (passwort.matches(passwortCheck)) {
                angemeldet(event);
                idSpeichern();
            } else {
                fehleranzeige.setText("ID und Passwort stimmen nicht überein!");
            }
        } else fehleranzeige.setText("Die ID wurde falsch eingegeben!");
    }
    //------------------------------------------------------------


    //------------------ID in Datei speichern--------------
        private void idSpeichern(){
            Mitarbeiter.idZwischenspeicher.add(id);
        }
     //------------------------------------------------------

    //------------------Scene switch angemeldet---------------
    private void angemeldet(ActionEvent event) throws IOException {
        Szenenwechsel.uebersichtScene(window,event, getClass(), registrierenScene);
    }
    //--------------------------------------------------------------


    //----------------Knopf:---------------
    public void anmelden(ActionEvent actionEvent) throws IOException {
        anmeldungPr(actionEvent);
    }
    //--------------------------------------



    //------------------------------------------------------------------------------------------------------

}
