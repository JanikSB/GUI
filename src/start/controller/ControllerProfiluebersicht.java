package start.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import start.normaleKlassen.AlleReservierungen;
import start.normaleKlassen.Mitarbeiter;
import start.normaleKlassen.Szenenwechsel;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ControllerProfiluebersicht {

    @FXML
    private Button profilKnopfReservieren;

    @FXML
    private Button profilKnopfRaum;

    @FXML
    private Label profilDatum;

    @FXML
    private Label profilMiId;

    @FXML
    private Label profilPasswort;

    @FXML
    private Label profilEmail;

    @FXML
    private ChoiceBox<String> reservierungenAusklapp;

    @FXML
    private Hyperlink loeschKnopf;




    FXMLLoader loaderProfil = new FXMLLoader(getClass().getResource("/start/fxml/profilübersicht.fxml"));

    private  int id ;

    private Stage window;



    //-----------------------Auf Szene Raum reservieren umswitchen----------------------
    public void pRaumReSwitch(ActionEvent event) throws IOException {
        Szenenwechsel.raumReScene(window,event,getClass(),profilKnopfRaum);
    }
    //---------------------------------------------------------------------------------------------


    //----------------------------Auf Szene Raum Übersicht  umswitchen------------------
    public void pUebersichtSwitch(ActionEvent event) throws IOException {
        Szenenwechsel.uebersichtScene(window,event,getClass(),profilKnopfRaum);
    }
    //---------------------------------------------------------------------------------------------





    //------------------------------Datum einstellen-------------------------------------
    private void datumSetzen(){
        Date datumheute = new Date();
        SimpleDateFormat format  = new SimpleDateFormat("dd.MM.yyyy");

        String datum = format.format(datumheute);

        profilDatum.setText(datum);
    }
    //--------------------------------------------------------------------------------------


    private  void idBekommen(){
        File idInfo = new File("src/start/resources/id.txt");
        try {
            Scanner scan = new Scanner(idInfo);
            id = scan.nextInt();

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
    }


    public void label () throws FileNotFoundException {
        idBekommen();
        datumSetzen();
        labelSetzen();
        profilMiId.setText(idStr);
        profilEmail.setText(email);
        profilPasswort.setText(passwort);

        reservierungLaden();
    }





    //-----------------------------Label setzen---------------------------------------
    String idStr = "";
    String email = "";
    String passwort = "";


    private void labelSetzen(){
        idStr = String.valueOf(id);
        email = Mitarbeiter.mitarbeiterMap.get(id).getEmail();
        passwort = Mitarbeiter.mitarbeiterMap.get(id).getPasswort();
    }
    //----------------------------------------------------------------------------------





   //-----------------------------------Meine Reservierungen-----------------------------

    File idLesen = new File("src/start/resources/id.txt");


    ObservableList<String> reservierungenListe = FXCollections.observableArrayList();

    String datum;
    String von;
    String bis;

    Scanner scanID;
    {
        try {
            scanID = new Scanner(idLesen);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }




    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");

    private void reservierungLaden() throws FileNotFoundException {


        String idUeberfruefung = scanID.next();


        String raumnummer;
        String id;
        Date datum1 = new Date();




        for (int i = 0 ;i < AlleReservierungen.reservierungenArrayList.size(); i++){
            String hashkey = AlleReservierungen.reservierungenArrayList.get(i);

            if(AlleReservierungen.reservierungenHashMap.get(hashkey).getMitarbeiterId().equals(idUeberfruefung)){

                raumnummer = AlleReservierungen.reservierungenHashMap.get(hashkey).getRaumnummer();
                datum  = AlleReservierungen.reservierungenHashMap.get(hashkey).getDatum();
                von = AlleReservierungen.reservierungenHashMap.get(hashkey).getVon();
                bis = AlleReservierungen.reservierungenHashMap.get(hashkey).getBis();

                try {
                    datum1 = format.parse(datum);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String datum2 = format1.format(datum1);


                String zusammen = "Raum "+ raumnummer + " am: "+datum2 + " , um: "+ von+ " Uhr ,bis: "+bis;

                reservierungenListe.add(zusammen);
            }



        }
        reservierungenAusklapp.setItems(reservierungenListe);

    }


    //---------------------------Reservierung löschen---------

    String raumnummer;
    String datum1;
    String von1;
    String bis1;


    private void getSelected(ChoiceBox<String> reservierungenAusklapp) throws IOException {

        scanWort();

        String hashkey = raumnummer+idStr+datum1+von1+bis1;

        System.out.println(AlleReservierungen.reservierungenHashMap.get(hashkey).getRaumnummer()+" "+AlleReservierungen.reservierungenHashMap.get(hashkey).getMitarbeiterId()+" "+AlleReservierungen.reservierungenHashMap.get(hashkey).getDatum());
        AlleReservierungen.reservierungenHashMap.remove(hashkey);

        for (int i = 0; i< AlleReservierungen.reservierungenArrayList.size(); i++){
            if (AlleReservierungen.reservierungenArrayList.get(i).equals(hashkey)){
                AlleReservierungen.reservierungenArrayList.remove(i);
                reservierungenAusklapp.getItems().remove(hashkey);
            }
        }

        System.out.println(raumnummer+" "+idStr+" "+datum1+" "+von1+" "+bis1);
    }



    private void scanWort(){

        String datum;

        Date date = new Date();

        String reservierung = reservierungenAusklapp.getValue();

        Scanner scannerSelected = new Scanner(reservierung);
        scannerSelected.next();
        raumnummer = scannerSelected.next();
        scannerSelected.next();
        datum = scannerSelected.next();
        try {
            date = format1.parse(datum);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        datum1 = format.format(date);

        scannerSelected.next();
        scannerSelected.next();
        von1 = scannerSelected.next();
        scannerSelected.next();
        scannerSelected.next();
        bis1 = scannerSelected.next();

        scannerSelected.close();

    }

    //----------------------------------------------------------------

//------------------------------------------------------------------------------------------------


    @FXML
    void loeschenDruecken(ActionEvent event) throws IOException {
        getSelected(reservierungenAusklapp);
        Szenenwechsel.uebersichtScene(window,event,getClass(),profilKnopfRaum);

    }



}
