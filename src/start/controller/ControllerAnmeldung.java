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
import start.normaleKlassen.Mitarbeiter;
import start.normaleKlassen.Szenenwechsel;

import java.io.*;

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

            if (Mitarbeiter.mitarbeiterMap.get(id).getPasswort().matches(passwort)) {
                angemeldet(event);
                idSpeichern();
            } else {
                fehleranzeige.setText("E-Mail Adresse und Passwort stimmen nicht überein!");
            }
        } else fehleranzeige.setText("Die ID wurde falsch eingegeben!");
    }
    //------------------------------------------------------------


    //------------------ID in Datei speichern--------------
        private void idSpeichern(){
            File idDatei = new File("src/start/resources/id.txt");

            try {
                idDatei.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            FileWriter writer = null;

            PrintWriter printWriter = null;
            try {
                printWriter = new PrintWriter(new BufferedWriter(new FileWriter("src/start/resources/id.txt", true)));
                printWriter.println(id);
                printWriter.flush();
                printWriter.close();


            } catch (IOException e) {
                e.printStackTrace();
            }
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
