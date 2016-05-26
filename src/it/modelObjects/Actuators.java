package it.modelObjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by lorca on 16/05/2016.
 */


public class Actuators {


    public ArrayList<Actuators> getAllRooms(){
        ArrayList<Actuators> list = new ArrayList<Actuators>();
        try{
            Connection conn = DriverManager.getConnection(url, usr,pwd);
            PreparedStatement pStmt;
            String query = "";
            pStmt = conn.prepareStatement(query);

            ResultSet rs = pStmt.executeQuery();
            while(rs.next()){
                Actuators tmp = new Actuators();

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


    public Actuators(int id){
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


    public Actuators(){

    }


}
