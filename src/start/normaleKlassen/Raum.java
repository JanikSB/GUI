 package start.normaleKlassen;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Raum {

    private int raumnummer;
    private int anzahlPlaetze;
    private boolean verfuegbarkeit = true;
    private String eigenschaften;
    String datumReservierungsende;


    public static Map<Integer, Raum> raumMap = new HashMap  <Integer, Raum>();


    public Raum(int raumnummer, int anzahlPlaetze, String eigenschaften, boolean verfuegbarkeit) {
        this.raumnummer = raumnummer;
        this.anzahlPlaetze = anzahlPlaetze;
        this.eigenschaften = eigenschaften;
        this.verfuegbarkeit = verfuegbarkeit;
    }




    public int getRaumnummer() {
        return raumnummer;
    }

    public void setRaumnummer(int raumnummer) {
        this.raumnummer = raumnummer;
    }



    public int getAnzahlPlaetze() {
        return anzahlPlaetze;
    }

    public void setAnzahlPlaetze(int anzahlPlaetze) {
        this.anzahlPlaetze = anzahlPlaetze;
    }



    public boolean isVerfuegbarkeit() {
        return verfuegbarkeit;
    }

    public void setVerfuegbarkeit(boolean verfuegbarkeit) {
        this.verfuegbarkeit = verfuegbarkeit;
    }



    public String getEigenschaften() {
        return eigenschaften;
    }

    public void setEigenschaften(String eigenschaften) {
        this.eigenschaften = eigenschaften;
    }



    public String getDatumReservierungsende() {
        return datumReservierungsende;
    }

    public void setDatumReservierungsende(String datumReservierungsende) {
        this.datumReservierungsende = datumReservierungsende;
    }

    //------------------------------Räume einlesen----------------------------------------------
    public static void hashmapRaumEinlesen(){

        int hashkey;
        int raumnummer;
        int anzahlPlaetze;
        String eigenschaften;
        boolean verfuegbarkeit;

        File RaumInfo = new File("src/start/resources/RaumDatei.txt");

        try {
            Scanner scan = new Scanner(RaumInfo);



            //--------Raum Instanzen erstellen-------------------------------------
            while(scan.hasNext()) {
                hashkey = scan.nextInt();
                raumnummer = scan.nextInt();
                anzahlPlaetze = scan.nextInt();
                eigenschaften = scan.next();
                verfuegbarkeit = true;

                Raum raum = new Raum(raumnummer, anzahlPlaetze, eigenschaften, verfuegbarkeit);



                raumMap.put(hashkey, raum);
            }
            //------------------------------------------------------------------------------------

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    //----------------------------------------------------------------------------------------------



}

