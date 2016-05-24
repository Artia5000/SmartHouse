package it.modelObjects;

import com.opensymphony.xwork2.ActionSupport;

import java.sql.*;
import java.util.*;

/**
 * Created by lorca on 16/05/2016.
 */


public class Devices {
    private String uuid;
    private Integer authorized;
    private String description;
    private Timestamp timestamp;
    private Integer id;

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }


    public ArrayList<Devices> getAllDevices(){
        ArrayList<Devices> list = new ArrayList<Devices>();
        try{
            Connection conn = DriverManager.getConnection(url, usr, pwd);
            PreparedStatement pStmt;
            String query = "SELECT * FROM auth_devices";
            pStmt = conn.prepareStatement(query);

            ResultSet rs = pStmt.executeQuery();
            while(rs.next()){
                Devices tmp = new Devices();
                tmp.id = rs.getInt("idauth_devices");
                tmp.description = rs.getString("description");
                tmp.uuid = rs.getString("uuid");
                tmp.authorized = rs.getInt("granted");
                tmp.timestamp = rs.getTimestamp("timestamp");
                list.add(tmp);

            }
            conn.close();

        } catch (Exception e) {
            System.out.println("Errore di connessione: " + e.getMessage());
        }
        return list;

    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String url = "jdbc:mysql://localhost:3306/smarthome";
    private String usr = "root";
    private String pwd = "password";

    public Integer getAuthorized() {
        return authorized;
    }

    public void setAuthorized(Integer authorized) {
        this.authorized = authorized;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Devices(String _uuid){
        this.authorized = 0;
      /*  try {
           // Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
        this.uuid = _uuid;
        try{
            Connection conn = DriverManager.getConnection(url, usr, pwd);
            PreparedStatement pStmt;
            String query = "SELECT * FROM auth_devices WHERE uuid LIKE ?";
                pStmt = conn.prepareStatement(query);
                pStmt.setString(1, this.uuid);

            ResultSet rs = pStmt.executeQuery();
            while(rs.next()){
                authorized = rs.getInt("granted");
            }
            conn.close();

        } catch (Exception e) {
            System.out.println("Errore di connessione: " + e.getMessage());
        }
    }


    public Devices(){

    }

    public boolean requestAuth(){
        authorized = 1;

        if(!uuid.isEmpty() && !description.isEmpty()){
            try{

                Connection conn = DriverManager.getConnection(url, usr, pwd);


                PreparedStatement pStmt;
                String query = "INSERT INTO auth_devices (description, uuid, granted, timestamp) VALUES (?,?,?,CURRENT_TIMESTAMP )";

                pStmt = conn.prepareStatement(query);
                pStmt.setString(1, description);
                pStmt.setString(2, uuid);
                pStmt.setInt(3, authorized);
                pStmt.executeUpdate();
                conn.close();
                return true;
            } catch (Exception e) {
                System.out.println("Errore di connessione: " + e.getMessage());
                return false;
            }
        }else{
            return false;
        }
    }


}
