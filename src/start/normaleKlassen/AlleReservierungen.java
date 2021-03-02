package start.normaleKlassen;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AlleReservierungen {
    private String raumnummer;
    private String mitarbeiterId;
    private String datum;
    private String von;
    private String bis;




    //Array List um alle reservierungen ohne key auszugeben
    public static ArrayList<String> reservierungenArrayList = new ArrayList<>();

    //Hashmap um auf bestimmte Reservierungen zuzugreifen
    public static HashMap<String, AlleReservierungen> reservierungenHashMap = new HashMap<>();




    public AlleReservierungen(String raumnummer, String mitarbeiterId, String datum, String von, String bis) {
        this.raumnummer = raumnummer;
        this.mitarbeiterId = mitarbeiterId;
        this.datum = datum;
        this.von = von;
        this.bis = bis;
    }

    public String getRaumnummer() {
        return raumnummer;
    }

    public void setRaumnummer(String raumnummer) {
        this.raumnummer = raumnummer;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getVon() {
        return von;
    }

    public void setVon(String von) {
        this.von = von;
    }

    public String getBis() {
        return bis;
    }

    public void setBis(String bis) {
        this.bis = bis;
    }

    public String getMitarbeiterId() {
        return mitarbeiterId;
    }

    public void setMitarbeiterId(String mitarbeiterId) {
        this.mitarbeiterId = mitarbeiterId;
    }




    public static void hashmapReservierungEinlesen(){

        String raumnummer;
        String mitarbeiterId;
        String datum;
        String von;
        String bis;

        String hashkey;





        //Reservierungen einlesen

        File reservierungsListe = new File("src/start/resources/ReservierungenDatei.txt");

        try {
            Scanner scan = new Scanner(reservierungsListe);



            //Reservierungsinstanzen erstellen
            while (scan.hasNextLine()){
                if (scan.hasNext()) {
                    raumnummer = scan.next();
                    mitarbeiterId = scan.next();
                    datum = scan.next();
                    von = scan.next();
                    bis = scan.next();
                    scan.hasNextLine();

                    hashkey = raumnummer + mitarbeiterId + datum + von + bis;


                    AlleReservierungen alleReservierungen = new AlleReservierungen(raumnummer, mitarbeiterId, datum, von, bis);


                    reservierungenArrayList.add(hashkey);
                    reservierungenHashMap.put(hashkey, alleReservierungen);
                } else break;
            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
