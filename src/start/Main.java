package start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import start.normaleKlassen.Anfang;
import start.normaleKlassen.CheckReservierung;

public class Main extends Application {

    //---------------------------------Anfang der App--------------------------------------
    @Override
    public void init() throws Exception {
        Anfang.anfang();
    }
    //----------------------------------------------------------------------------------------

    CheckReservierung checkReservierung = new CheckReservierung();
    Thread t =  new Thread(checkReservierung);

    //--------------------------------------------------------------------------------------

    @Override
    public void start(Stage primaryStage) throws Exception{

       t.start();

        Parent root = FXMLLoader.load(getClass().getResource("/start/fxml/anmeldung.fxml"));

        primaryStage.setTitle("Reservierungssystem");
        primaryStage.setScene(new Scene(root, 1286, 750));
        primaryStage.setResizable(false);
        primaryStage.show();

    }
    //--------------------------------------------------------------------------------------


    public static void main(String[] args) {

        launch(args);

    }


    @Override
    public void stop() throws Exception {
        Anfang.idLoeschen();
        Anfang.hashmapInDatei();
       t.interrupt();
    }
}


