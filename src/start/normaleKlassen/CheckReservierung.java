package start.normaleKlassen;

import javafx.concurrent.Task;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CheckReservierung extends Thread {
     int j= 0;

public void run(){


            while (true) {

                try (Connection connection = DriverManager.getConnection(Datenbank.dbURL); Statement statement = connection.createStatement();) {


                String query = "SELECT * " +
                        "FROM dbo.Reservierungen";

                SimpleDateFormat zuDatum = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                ResultSet resultSet = statement.executeQuery(query);



                while (resultSet.next()) {
                    Date jetzt = new Date();

                    String vonTime = resultSet.getString("UhrzeitVon");
                    String bisTime = resultSet.getString("UhrzeitBis");


                    Date von = zuDatum.parse(vonTime);
                    Date bis = zuDatum.parse(bisTime);

                    int reservierungsID = resultSet.getInt("ReservierungsID");



                    if (bis.before(jetzt)) {

                        PreparedStatement delstmt = connection.prepareStatement(("DELETE FROM dbo.Reservierungen WHERE ReservierungsID = " + reservierungsID));
                        delstmt.executeUpdate();


                        System.out.println("Eine Reservierung geloescht");
                    }
                }

//                for (int i = 0; i < AlleReservierungen.reservierungenArrayList.size(); i++){
//                    String hashkey = AlleReservierungen.reservierungenArrayList.get(i);
//
//                    Date jetzt = new Date();
//                    Date date = new Date();
//
//                    String datum = AlleReservierungen.reservierungenHashMap.get(hashkey).getDatum() + " "+AlleReservierungen.reservierungenHashMap.get(hashkey).getBis();
//                    date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(datum);
//
//                    if (date.before(jetzt)){
//                        AlleReservierungen.reservierungenArrayList.remove(i);
//                        AlleReservierungen.reservierungenHashMap.remove(hashkey);
//                        System.out.println("Eine Reservierung geloescht");
//                    }
//                    if (i == AlleReservierungen.reservierungenArrayList.size()){
//                        i=0;
//                    }
//                }

                Thread.sleep(1000);
                }
                catch (SQLException e){
            e.printStackTrace();
                    System.out.println("1");
        } catch (ParseException e) {
                    e.printStackTrace();
                    System.out.println("2");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("3");

                }

    }
}}
