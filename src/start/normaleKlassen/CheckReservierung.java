package start.normaleKlassen;

import javafx.concurrent.Task;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CheckReservierung extends Task {
     int j= 0;

    @Override
    protected Object call() throws Exception {
        while (true){

            for (int i = 0; i < AlleReservierungen.reservierungenArrayList.size(); i++){
                String hashkey = AlleReservierungen.reservierungenArrayList.get(i);

                Date jetzt = new Date();
                Date date = new Date();

                String datum = AlleReservierungen.reservierungenHashMap.get(hashkey).getDatum() + " "+AlleReservierungen.reservierungenHashMap.get(hashkey).getBis();
                date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(datum);

                if (date.before(jetzt)){
                    AlleReservierungen.reservierungenArrayList.remove(i);
                    AlleReservierungen.reservierungenHashMap.remove(hashkey);
                    System.out.println("Eine Reservierung geloescht");
                }
                if (i == AlleReservierungen.reservierungenArrayList.size()){
                    i=0;
                }
            }
            j++;
            System.out.println("funktioniert "+j);
            Thread.sleep(5000);
        }
    }
}
