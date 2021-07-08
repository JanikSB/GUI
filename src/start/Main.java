package start;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import start.normaleKlassen.Anfang;
import start.normaleKlassen.CheckReservierung;

import java.io.IOException;

public class Main extends Application {

    //---------------------------------Anfang der App--------------------------------------
    @Override
    public void init() throws Exception {
//        Anfang.anfang();
    }
    //----------------------------------------------------------------------------------------


    //--------------------------------------------------------------------------------------

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/start/fxml/anmeldung.fxml"));

        primaryStage.setTitle("Reservierungssystem");
        primaryStage.setScene(new Scene(root, 1286, 750));
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Platform.exit();
                System.exit(0);
            }
        });

    }
    //--------------------------------------------------------------------------------------


    public static void main(String[] args) {

        CheckReservierung chRes = new CheckReservierung();
        new Thread(chRes).start();
        launch(args);

    }

}


