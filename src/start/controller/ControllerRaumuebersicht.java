package start.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import start.normaleKlassen.Szenenwechsel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ControllerRaumuebersicht {

    @FXML
    private Button raumReservierenScene;

    @FXML
    private Button profilScene;

    @FXML
    private Label DatumHeute;

    @FXML
    private Button okayBtn;

    @FXML
    private Text raumUeberschrift;

    @FXML
    private Label anzahlPlatz;

    @FXML
    private Label eigenschaften;

    @FXML
    private Button raum3Info;

    @FXML
    private Button raum4Info;

    @FXML
    private Button Raum2Info;

    @FXML
    private Button raum5Info;

    @FXML
    private Button raum1Info;

    @FXML
    private Button raum6Info;



    private Stage window;


    //-----------------------Auf Szene Raum reservieren umswitchen-----------------------

    public void raumReSwitch(javafx.event.ActionEvent event) throws IOException {
        Szenenwechsel.raumReScene(window, event, getClass(), profilScene);
    }
    //---------------------------------------------------------------------------------------------

    //--------------------------------Auf Szene Profil  umswitchen---------------------------

    public void profilSwitch(ActionEvent event) throws IOException {
        Szenenwechsel.profilScene(window,event,getClass(),profilScene);
    }
    //---------------------------------------------------------------------------------------------



    //------------------------------Datum einstellen-------------------------------------
    Date datumheute = new Date();
    SimpleDateFormat format  = new SimpleDateFormat("dd.MM.yyyy");

    String datum = format.format(datumheute);
    //--------------------------------------------------------------------------------------



    //-----------------------------------------------------RÄUME-------------------------------------------------------------


    FXMLLoader loaderRaFe = new FXMLLoader(getClass().getResource("/start/fxml/raumFenster.fxml"));

//---Raum und Datum:---
    public void initialize(){
        DatumHeute.setText(datum);
        //raumOeffner();
    }
//---------------------------



    //---------------------------------Raumfenster öffnen-------------------------------
    private Stage stage = new Stage();


    @FXML
    void schließenAlert(ActionEvent event) {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }


    public void raumOeffner(){

        try {
            loaderRaFe.setController(this);                                                     //WARUM LÄD ER ZWEIMAL ???? (Beeinträchtigt nichts)
            Parent alertBoxScene = loaderRaFe.load();
            stage.setTitle("");
            stage.setScene(new Scene(alertBoxScene));
            stage.setWidth(600);
            stage.setHeight(430);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);

        } catch (IOException e) {
            System.out.println("Hier nochmal nachschauen -> Controller Raumübersicht raumOeffner()");
        }


    }
    //---------------------------------------------------------------------------------------

    private void raum (){
        stage.showAndWait();
    }

    private final int[] benutzt = new int[1];


    //--------------------------------------Raum 1------------------------------
    public void raum1Action(ActionEvent event) {

        if (benutzt [0] == 0){
            raumOeffner();
            benutzt [0] = 1;
        }


        raumUeberschrift.setText("Raum 1");
        anzahlPlatz.setText("8");
        eigenschaften.setText("Beamer und Whiteboard");
        raum();
    }
    //----------------------------------------------------------------------------


    //--------------------------------------Raum 2------------------------------
    public void raum2Action(ActionEvent event) {
        if (benutzt [0] == 0){
            raumOeffner();
            benutzt [0] = 1;
        }
        raumUeberschrift.setText("Raum 2");
        anzahlPlatz.setText("2");
        eigenschaften.setText("Whiteboard");
        raum();
    }
    //----------------------------------------------------------------------------


    //--------------------------------------Raum 3------------------------------
    public void raum3Action(ActionEvent event) {
        if (benutzt [0] == 0){
            raumOeffner();
            benutzt [0] = 1;
        }
        raumUeberschrift.setText("Raum 3");
        anzahlPlatz.setText("5");
        eigenschaften.setText("Beamer und Whiteboard");
        raum();
    }
    //----------------------------------------------------------------------------


    //--------------------------------------Raum 4------------------------------
    public void raum4Action(ActionEvent event) {
        if (benutzt [0] == 0){
            raumOeffner();
            benutzt [0] = 1;
        }
        raumUeberschrift.setText("Raum 4");
        anzahlPlatz.setText("4");
        eigenschaften.setText("Whiteboard");
        raum();
    }
    //----------------------------------------------------------------------------


    //--------------------------------------Raum 5------------------------------
    public void raum5Action(ActionEvent event) {
        if (benutzt [0] == 0){
            raumOeffner();
            benutzt [0] = 1;
        }
        raumUeberschrift.setText("Raum 5");
        anzahlPlatz.setText("4");
        eigenschaften.setText("Whiteboard");
        raum();
    }
    //----------------------------------------------------------------------------

    //--------------------------------------Raum 6------------------------------
    public void raum6Action(ActionEvent event) {
        if (benutzt [0] == 0){
            raumOeffner();
            benutzt [0] = 1;
        }
        raumUeberschrift.setText("Raum 6");
        anzahlPlatz.setText("1");
        eigenschaften.setText("Whiteboard");
        raum();
    }
    //----------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------------------------------------------------

}
