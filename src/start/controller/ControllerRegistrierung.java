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
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import start.normaleKlassen.Mitarbeiter;

import java.io.*;
import java.util.Scanner;

public class ControllerRegistrierung {

    @FXML
    private Button registrierenKnopf;

    @FXML
    private TextField emailFeld;

    @FXML
    private PasswordField passwortFeld;

    @FXML
    private PasswordField passwortBeFeld;

    @FXML
    private Label passwortFehler;

    @FXML
    private Label passwortFehler1;

    @FXML
    private Label emailFehler;

    @FXML
    private Text idNum;

    @FXML
    private Button okKnopf;

    @FXML
    private Button anmeldenScene;


    private Stage window;


    private static int id;
    public static int getId() {
        return id;
    }

    //--------------------Auf Szene Anmeldung umswitchen---------------------

    @FXML
    void anmeldenSwitch(ActionEvent event) throws IOException {
        Parent anmeldungScene = FXMLLoader.load(getClass().getResource("/start/fxml/anmeldung.fxml"));
        Scene anmeldung = new Scene(anmeldungScene);

        window =(Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(anmeldung);

    }
    //-------------------------------------------------------------------------------



    //-----------------------------Registrierungsprozess---------------------------------------------

    //-------------------------Passwörter prüfen-------------------------------------
    private static String email;
    private static String passwort;
    String passwortBe;

    private boolean  registrierungsPruefen() throws IOException {
        boolean ok = true;

        email = emailFeld.getText();
        passwort = passwortFeld.getText();
        passwortBe = passwortBeFeld.getText();

        if (!passwort.equals(passwortBe)){
            passwortFehler.setText("Passwörter stimmen nicht überein!");
            ok = false;
        }else if (!email.contains("@") || !email.contains(".")){
            emailFehler.setText("Ungültige Eingabe der Email-Adresse!");
            ok = false;
        } else if(passwort.length() <8 ){
            passwortFehler1.setText("Das Passwort muss mindestens 8 Zeichen lang sein!");
            ok = false;
        }

        else{
            zusammengefasst();

        }
        return ok;
    }
    //----------------------------------------------------------------------------------


    //------------------Scene switch registriert---------------
    private void registriert() throws IOException {
        Parent angemeldet = FXMLLoader.load(getClass().getResource("/start/fxml/raumübersicht.fxml"));
        Scene angemeldetScene = new Scene(angemeldet);


        window.setScene(angemeldetScene);
    }
    //--------------------------------------------------------------


    //----------------Knopf:---------------
    @FXML
    void registrieren(ActionEvent event) throws IOException {
        window = (Stage)((Node)event.getSource()).getScene().getWindow();
      if(registrierungsPruefen()){
          alertBoxScene();
      }
    }
    //--------------------------------------

    //----------------------------------------------------------------------------------------------------------


    //-------------
    private void zusammengefasst(){
        idRandom();
        mitarbeiterErstellen();
        mitarbeiterInTextdatei();
        idSpeichern();
    }
    //-------------

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


    //---------------------Random Mitarbeiter ID generieren-----------------
    private static void idRandom() {

        do {
            id = (int) (Math.random() * 99999999 - 10000000) + 10000000;
        }while (IdUeberpruefen().equals(false));


    }
    private static Boolean IdUeberpruefen(){
        File mitarbeiter = new File("src/start/resources/MitarbeiterDatei.txt");
        Scanner scan = null;
        try {
            scan = new Scanner(mitarbeiter);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int ueberpruefung ;
        boolean antwort = true;

        while(scan.hasNextLine()) {
            ueberpruefung = scan.nextInt();
            scan.nextLine();

            if(ueberpruefung == id){
                antwort = false;
            }
        }

        return  antwort;
    }
    //----------------------------------------------------------------------------

    //----------------------neue Mitarbeiterinstanz per Konsole--------------
    private  static void mitarbeiterErstellen() {

        Mitarbeiter mitarbeiter = new Mitarbeiter(id, email, passwort);
        Mitarbeiter.mitarbeiterMap.put(mitarbeiter.getId(), mitarbeiter);
    }
    //-----------------------------------------------------------------------------


    //-------------------Mitarbeiter Info in txt Datei schreiben------------------
    private static void mitarbeiterInTextdatei(){

        File mitarbeiterDatei = new File("src/start/resources/MitarbeiterDatei.txt");

        try {
            mitarbeiterDatei.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileWriter writer = null;

        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new BufferedWriter(new FileWriter("src/start/resources/MitarbeiterDatei.txt", true)));
            printWriter.println(id+" "+id+" "+email+" "+passwort+" ");
            printWriter.flush();
            printWriter.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //-------------------------------------------------------------------------------




    //-----------Alert Box--------------------


    @FXML
    void okKnopfClick(ActionEvent event) throws IOException {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    private Stage stage = new Stage();

    private void alertBoxScene() throws IOException {


        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/start/fxml/idAlert.fxml"));
            loader.setController(this);
            Parent alertBoxScene = (Parent) loader.load();

            idNum.setText(String.valueOf(id));

            stage.setTitle("Wilkommen!");
            stage.setScene(new Scene(alertBoxScene));
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();



        } catch (Exception e){
            System.out.println("kann nicht laden!");
        }

        registriert();
    }

    //----------------------------------------





}
