package start.normaleKlassen;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import start.controller.ControllerProfiluebersicht;
import start.controller.ControllerRaumReservierung;
import start.controller.ControllerRaumuebersicht;

import java.io.IOException;

public class Szenenwechsel {


    public static void reservierungsScene(Stage window, ActionEvent event, Button button, Class a) throws IOException {
        Parent registrierungScene = FXMLLoader.load(a.getResource("/start/fxml/registrierung.fxml"));
        Scene registrierung = new Scene(registrierungScene);

        window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(registrierung);
    }



    public static void uebersichtScene(Stage window, ActionEvent event, Class a, Button button) throws IOException {
        window =(Stage) button.getScene().getWindow();

        FXMLLoader load = new FXMLLoader(a.getResource("/start/fxml/raumübersicht.fxml"));
        Parent angemeldet = load.load();
        Scene angemeldetScene = new Scene(angemeldet);

        ControllerRaumuebersicht controllerRaumuebersicht = load.getController();

        window.setScene(angemeldetScene);
    }



    public static void profilScene(Stage window, ActionEvent event, Class a, Button button) throws IOException {
        window = (Stage)((Node)event.getSource()).getScene().getWindow();
        ControllerProfiluebersicht controllerProfiluebersicht = new ControllerProfiluebersicht();

        FXMLLoader loaderProfil = new FXMLLoader(a.getResource("/start/fxml/profilübersicht.fxml"));
        loaderProfil.setController(controllerProfiluebersicht);

        Parent profilScene = loaderProfil.load();
        controllerProfiluebersicht.label();

        Scene profil = new Scene(profilScene);

        window.setScene(profil);
    }



    public static void raumReScene(Stage window, ActionEvent event, Class a, Button button) throws IOException {
        window = (Stage)((Node)event.getSource()).getScene().getWindow();
        ControllerRaumReservierung controllerRaumReservierung = new ControllerRaumReservierung();

        FXMLLoader loaderRaumRe = new FXMLLoader(a.getResource("/start/fxml/raumReservierung.fxml"));
        loaderRaumRe.setController(controllerRaumReservierung);

        Parent raumReScene = loaderRaumRe.load();

        controllerRaumReservierung.startRaumRe();

        window.setScene(new Scene(raumReScene));
    }


}
