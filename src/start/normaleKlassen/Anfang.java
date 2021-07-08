package start.normaleKlassen;

import java.io.*;

public class Anfang {

//    public static void anfang(){
//        //Datenbank.datenbankVerbinden();
////        hashmaps();
//    }

//    private static void hashmaps(){
//        Mitarbeiter.hashmapMitarbeiterEinlesen();
//        Raum.hashmapRaumEinlesen();
//        AlleReservierungen.hashmapReservierungEinlesen();
//
//
//    }

//    public static void idLoeschen() throws FileNotFoundException {
//        PrintWriter writer= new PrintWriter("src/start/resources/id.txt");
//        writer.print("");
//        writer.close();
//    }

//    public static void hashmapInDatei(){
//
//        File reservierungsListe = new File("src/start/resources/ReservierungenDatei.txt");
//
//        try {
//            PrintWriter writer = new PrintWriter(reservierungsListe);
//
//            writer.print("");
//            writer.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        for (int i = 0;i < AlleReservierungen.reservierungenArrayList.size(); i++){
//            String hashkey = AlleReservierungen.reservierungenArrayList.get(i);
//
//            String raumnummer;
//            String id;
//            String datum;
//            String von;
//            String bis;
//
//            raumnummer = AlleReservierungen.reservierungenHashMap.get(hashkey).getRaumnummer();
//            id = AlleReservierungen.reservierungenHashMap.get(hashkey).getMitarbeiterId();
//            datum = AlleReservierungen.reservierungenHashMap.get(hashkey).getDatum();
//            von = AlleReservierungen.reservierungenHashMap.get(hashkey).getVon();
//            bis = AlleReservierungen.reservierungenHashMap.get(hashkey).getBis();
//
//
//
//            try {
//                reservierungsListe.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            FileWriter writer = null;
//
//            PrintWriter printWriter = null;
//            try {
//                printWriter = new PrintWriter(new BufferedWriter(new FileWriter("src/start/resources/ReservierungenDatei.txt", true)));
//                printWriter.println(raumnummer+" "+id+" "+datum+" "+von+" "+bis);
//                printWriter.flush();
//                printWriter.close();
//
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }
}
