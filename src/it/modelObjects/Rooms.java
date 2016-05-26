package it.modelObjects;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by lorca on 16/05/2016.
 */


public class Rooms {
    

    public ArrayList<Rooms> getAllRooms(){
        ArrayList<Rooms> list = new ArrayList<Rooms>();
        try{
            Connection conn = DriverManager.getConnection(url, usr,pwd);
            PreparedStatement pStmt;
            String query = "";
            pStmt = conn.prepareStatement(query);

            ResultSet rs = pStmt.executeQuery();
            while(rs.next()){
                Rooms tmp = new Rooms();

            }
            conn.close();

        } catch (Exception e) {
            System.out.println("Errore di connessione: " + e.getMessage());
        }
        return list;

    }


    private String url = "jdbc:mysql://localhost:3306/smarthome";
    private String usr = "root";
    private String pwd = "";


    public Rooms(int id){
      /*  try {
           // Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
        try{
            Connection conn = DriverManager.getConnection(url, usr, pwd);
            PreparedStatement pStmt;
            String query = "";
                pStmt = conn.prepareStatement(query);

            ResultSet rs = pStmt.executeQuery();
            while(rs.next()){
               }
            conn.close();

        } catch (Exception e) {
            System.out.println("Errore di connessione: " + e.getMessage());
        }
    }


    public Rooms(){

    }


}
