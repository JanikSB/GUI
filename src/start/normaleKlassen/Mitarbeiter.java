package start.normaleKlassen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Mitarbeiter implements Serializable {


    private int id;
    private String email;
    private String passwort;
    private int raeume;

    public static Map<Integer, Mitarbeiter> mitarbeiterMap = new HashMap<Integer, Mitarbeiter>();


    public Mitarbeiter( int id, String email, String passwort) {

        this.id = id;
        this.email = email;
        this.passwort = passwort;
    }






    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }



    public int getRaeume() {
        return raeume;
    }

    public void setRaeume(int raeume) {
        this.raeume = raeume;
    }




    //---------------------Mitarbeiter einlesen---------------------------------------
    public static void hashmapMitarbeiterEinlesen(){

        int hashKey;
        int id;
        String email;
        String passwort;
        String reservierteRaeume;



        File mitarbeiterInfo = new File("src/start/resources/MitarbeiterDatei.txt");
        try {
            Scanner scan = new Scanner(mitarbeiterInfo);



            //--------Mitarbeiter Instanzen erstellen---------------------------
            while(scan.hasNext()) {
                hashKey = scan.nextInt();
                id = scan.nextInt();
                email = scan.next();
                passwort = scan.next();

                Mitarbeiter mitarbeiter = new Mitarbeiter(id,email,passwort);
                mitarbeiterMap.put(hashKey, mitarbeiter);
                scan.nextLine();
            }
            //-------------------------------------------------------------------

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    //-----------------------------------------------------------------------------------

}