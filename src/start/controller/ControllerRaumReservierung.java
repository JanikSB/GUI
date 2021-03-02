package start.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import start.normaleKlassen.Raum;
import start.normaleKlassen.AlleReservierungen;
import start.normaleKlassen.Szenenwechsel;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class ControllerRaumReservierung {

    @FXML
    private Button raumReKnopfRaum;

    @FXML
    private Button raumReKnopfProfil;

    @FXML
    private Label raumReDatum;

    @FXML
    private TableView<Raum> tabelleDaten;

    @FXML
    private TableColumn<Raum, Integer> reservierenRaumnummer;

    @FXML
    private TableColumn<Raum, String> tableReservierenAusstattung;

    @FXML
    private TableColumn<Raum, Integer> tableReservierenPlaetze;

    @FXML
    private DatePicker reservierenDatum;

    @FXML
    private Spinner<String> reservierenAbUhr;

    @FXML
    private Spinner<String> reservierenBisUhr;

    @FXML
    private Label fehlerDatum;

    @FXML
    private Button reservierenKnopf;

    @FXML
    private TableView<AlleReservierungen> tabelleAlleReservierungen;

    @FXML
    private TableColumn<AlleReservierungen, String> columnRaum;

    @FXML
    private TableColumn<AlleReservierungen, String> columnDatum;

    @FXML
    private TableColumn<AlleReservierungen, String> columnVon;

    @FXML
    private TableColumn<AlleReservierungen, String> columnBis;

    @FXML
    private Button okKnopf;

    @FXML
    private Button alleReservierungen;




    private Stage window;

    //----------------------------Auf Szene Raum Übersicht  umswitchen------------------------

    public void raumSwitch(ActionEvent event) throws IOException {
        Szenenwechsel.uebersichtScene(window,event,getClass(),alleReservierungen);
    }
    //-----------------------------------------------------------------------------------------


    //--------------------------------Auf Szene Profil  umswitchen-----------------------------

    public void profilSwitch(ActionEvent event) throws IOException {
        Szenenwechsel.profilScene(window,event,getClass(),alleReservierungen);
    }
    //------------------------------------------------------------------------------------------



    //-------------------------Start einstellen------------------------------------

    public void startRaumRe(){
        datumEinstellen();
        tabelleEinstellen();
        auswahlEinstellen();
    }
    


    private void datumEinstellen(){
        Date datumHeute = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        String datum = format.format(datumHeute);
        raumReDatum.setText(datum);
        tabelleEinstellen();
    }


    private void tabelleEinstellen(){
        reservierenRaumnummer.setSortable(false);
        tableReservierenAusstattung.setSortable(false);
        tableReservierenPlaetze.setSortable(false);

        reservierenRaumnummer.setCellValueFactory( new PropertyValueFactory<Raum, Integer>("raumnummer"));
        tableReservierenAusstattung.setCellValueFactory( new PropertyValueFactory<Raum, String>("eigenschaften"));
        tableReservierenPlaetze.setCellValueFactory( new PropertyValueFactory<Raum, Integer>("anzahlPlaetze"));

        tabelleDaten.setItems(getRaeume());



    }
    //----------------------------------------------------------------------------------------


    //---------------------------------------Tabelle konfigurieren--------------------------------





    private  ObservableList<Raum> getRaeume(){
        ObservableList<Raum> raeume = FXCollections.observableArrayList();
        raeume.add(new Raum(101,8,"Beamer und Whiteboard",true));
        raeume.add(new Raum(102,2,"Whiteboard",true));
        raeume.add(new Raum(103,5,"Beamer und Whiteboard",true));
        raeume.add(new Raum(104,4," Whiteboard",true));
        raeume.add(new Raum(105,4,"Whiteboard",true));
        raeume.add(new Raum(106,1,"Whiteboard",true));

        return  raeume;
    }


    //------------------------------------------------------------------------------------------------------




    //-------------------------Datum & Uhrzeit einstellen---------------------------------



    LocalDate datumReservierung;




    ObservableList<String> reservierenUhrzeitenAb = FXCollections.observableArrayList("07:00","08:00","09:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00");
    SpinnerValueFactory<String > uhrzeitenSpinnerAb = new SpinnerValueFactory.ListSpinnerValueFactory<String>(reservierenUhrzeitenAb);

    ObservableList<String> reservierenUhrzeitenBis = FXCollections.observableArrayList("08:00","09:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00");
    SpinnerValueFactory<String > uhrzeitenSpinnerBis = new SpinnerValueFactory.ListSpinnerValueFactory<String>(reservierenUhrzeitenBis);


    private void auswahlEinstellen(){
        reservierenAbUhr.setValueFactory(uhrzeitenSpinnerAb);
        reservierenBisUhr.setValueFactory(uhrzeitenSpinnerBis);
    }





    String abUhrzeit;       //später benutzt
    String bisUhrzeit;      //später benutzt
    String datumReStr;      //später benutzt

    String zeitAb;
    String zeitBis;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    Date datumAb = new Date();
    Date datumBis = new Date();
    Date datumJz = new Date();



    private void zeitZuweisen() throws ParseException {


        abUhrzeit = reservierenAbUhr.getValue();
        bisUhrzeit = reservierenBisUhr.getValue();
        datumReStr = datumReservierung.toString();

        zeitAb = datumReStr+" "+abUhrzeit;
        zeitBis = datumReStr+" "+bisUhrzeit;

        datumAb = dateFormat.parse(zeitAb);
        datumBis = dateFormat.parse(zeitBis);

        if(datumJz.before(datumAb)) {
            if(datumAb.before(datumBis)){
                fehlerDatum.setText("");
                checkRaumVerfuegbar(); //methode implementiert
            }else fehlerDatum.setText("Fehler: Die ''von'' Uhrzeit muss vor der ''bis'' Uhrzeit liegen!");

        } else fehlerDatum.setText("Fehler: Die Buchung liegt in der Vergangenheit!");
    }
//---------------------------------------------------------------------------------------------




    //---------------------------------------Reservieren Knopf--------------------------

    String raumnummerReservierungStr;

    private void raumAuswahl(){
        Raum raum = tabelleDaten.getSelectionModel().getSelectedItem();

        raumnummerReservierungStr = String.valueOf(raum.getRaumnummer());       //später benutzt
    }


    @FXML
    void reservierenOnAction(ActionEvent event) throws ParseException {
        datumReservierung = reservierenDatum.getValue();

        if (datumReservierung == null) {
            System.out.println(datumReservierung);
            fehlerDatum.setText("Bitte Datum auswählen");
        }else {
            raumAuswahl();
            zeitZuweisen();

        }
    }

    private void weiterReservieren() throws IOException { // -> in "checkVerfügbarkeit()" -> in "zeitZuweisen()"

        reservierungInHashmap();    //funktioniert (& in ArrayList hashkey speichern)
        alertBoxScene();            //alertBox bei erfolgreicher Registrierung (switch nur Scene)
    }
//----------------------------------------------------------------------------------------


    //-------------------------------------Alle Reservierungen--------------------------



    //Liste für alle Reservierungen erstellen

    private ObservableList<AlleReservierungen> getAlles(){
        String raumnummer = "";
        String datum = "";
        String von = "";
        String bis = "";
        String mitarbeiterID = "";

        ObservableList<AlleReservierungen> alle = FXCollections.observableArrayList();

        int i = 0;
        while (i < AlleReservierungen.reservierungenArrayList.size()){
            String hashkey = AlleReservierungen.reservierungenArrayList.get(i);
            raumnummer = AlleReservierungen.reservierungenHashMap.get(hashkey).getRaumnummer();
            datum = AlleReservierungen.reservierungenHashMap.get(hashkey).getDatum();
            von = AlleReservierungen.reservierungenHashMap.get(hashkey).getVon();
            bis = AlleReservierungen.reservierungenHashMap.get(hashkey).getBis();

            mitarbeiterID = AlleReservierungen.reservierungenHashMap.get(hashkey).getMitarbeiterId();
            alle.add(new AlleReservierungen(raumnummer, mitarbeiterID, datum, von, bis));

            i++;
        }
        return  alle;
    }



    private Stage stage1 = new Stage();

    @FXML
    void alleReservierungenAnsehen(ActionEvent event) {


        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/start/fxml/alleReservierungen.fxml"));
            loader.setController(this);
            Parent a = loader.load();



            columnRaum.setCellValueFactory( new PropertyValueFactory<AlleReservierungen, String>("raumnummer"));
            columnDatum.setCellValueFactory( new PropertyValueFactory<AlleReservierungen, String>("datum"));
            columnVon.setCellValueFactory( new PropertyValueFactory<AlleReservierungen, String>("von"));
            columnBis.setCellValueFactory( new PropertyValueFactory<AlleReservierungen, String>("bis"));
            tabelleAlleReservierungen.setItems(getAlles());



            stage1.setTitle("");
            stage1.setScene(new Scene(a));
            stage1.setResizable(false);

            stage1.showAndWait();



        } catch (Exception e){
            System.out.println("kann nicht laden!");
        }
    }

    //----------------------------------------------------------------------------------------------



    //-------------------------------Erfolgreiche Reservierung Scene-----------------------


    @FXML
    void okKnopfClick(ActionEvent event) throws IOException {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
        Szenenwechsel.uebersichtScene(window,event,getClass(),reservierenKnopf);
    }

    private Stage stage = new Stage();

    private void alertBoxScene() throws IOException {


        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/start/fxml/reservierungBestätigung.fxml"));
            loader.setController(this);
            Parent alertBoxScene = (Parent) loader.load();

            stage.setHeight(210);
            stage.setWidth(530);
            stage.setTitle("Geschafft!");
            stage.setScene(new Scene(alertBoxScene));
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();



        } catch (Exception e){
            System.out.println("kann nicht laden!");
        }


    }
    //------------------------------------------------------------------------------------------



    private void checkRaumVerfuegbar() throws ParseException {


        String check;
        String zeitDatum;
        String zeitBeginn;
        String zeitEnde;
        Date dateiDatumAb;
        Date dateiDatumBis;

        String hashkey;

        int i = 0;

        if (AlleReservierungen.reservierungenArrayList.size() == 0){
            try {
                weiterReservieren(); //methode implementiert
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (int j = 0; j < AlleReservierungen.reservierungenArrayList.size(); j++) {
            hashkey = AlleReservierungen.reservierungenArrayList.get(j);


            check = AlleReservierungen.reservierungenHashMap.get(hashkey).getRaumnummer();

            if (check.equals(raumnummerReservierungStr)) {
                zeitDatum = AlleReservierungen.reservierungenHashMap.get(hashkey).getDatum();
                zeitBeginn = AlleReservierungen.reservierungenHashMap.get(hashkey).getVon();
                zeitEnde = AlleReservierungen.reservierungenHashMap.get(hashkey).getBis();

                dateiDatumAb = dateFormat.parse(zeitDatum + " " + zeitBeginn);
                dateiDatumBis = dateFormat.parse(zeitDatum + " " + zeitEnde);

                if ((dateiDatumAb.after(datumAb) && dateiDatumAb.before(datumBis)) ||
                        (dateiDatumBis.after(datumAb) && dateiDatumBis.before(datumBis)) ||
                        (dateiDatumAb.before(datumAb) && dateiDatumBis.after(datumBis)) ||
                        dateiDatumAb.equals(datumAb) ||
                        dateiDatumBis.equals(datumBis)) {
                    fehlerDatum.setText("Der Raum ist zu diesem Zeitpunkt nicht verfügbar");
                    System.out.println("Klappt");
                    i++;
                    break;
                }
            }

        }
        if (i == 0) {
            fehlerDatum.setText("");
            try {
                weiterReservieren(); //methode implementiert
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void reservierungInHashmap() throws FileNotFoundException {
        String id;
        File idScan = new File("src/start/resources/id.txt");
        Scanner scan = new Scanner(idScan);

        id = scan.next();
        String hashkey = raumnummerReservierungStr+id+datumReStr+abUhrzeit+bisUhrzeit;

        AlleReservierungen alleReservierungen = new AlleReservierungen(raumnummerReservierungStr,id,datumReStr,abUhrzeit,bisUhrzeit);

        AlleReservierungen.reservierungenHashMap.put(hashkey, alleReservierungen);
        AlleReservierungen.reservierungenArrayList.add(hashkey);

        scan.close();
    }


}

