package it.modelObjects;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by lorca on 16/05/2016.
 */


public class Sensors {

    private int id;
    private String description;
    private String ipaddress;
    private String macaddress;
    private int sensorTypeId;
    private String sensorTypeDescription;
    private float rangeLower;
    private float rangeHigher;
    private String unit;
    private float lastData;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public String getMacaddress() {
        return macaddress;
    }

    public void setMacaddress(String macaddress) {
        this.macaddress = macaddress;
    }

    public int getSensorTypeId() {
        return sensorTypeId;
    }

    public void setSensorTypeId(int sensorTypeId) {
        this.sensorTypeId = sensorTypeId;
    }

    public String getSensorTypeDescription() {
        return sensorTypeDescription;
    }

    public void setSensorTypeDescription(String sensorTypeDescription) {
        this.sensorTypeDescription = sensorTypeDescription;
    }

    public float getRangeLower() {
        return rangeLower;
    }

    public void setRangeLower(float rangeLower) {
        this.rangeLower = rangeLower;
    }

    public float getRangeHigher() {
        return rangeHigher;
    }

    public void setRangeHigher(float rangeHigher) {
        this.rangeHigher = rangeHigher;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public float getLastData() {
        return lastData;
    }

    public void setLastData(float lastData) {
        this.lastData = lastData;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsr() {
        return usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public ArrayList<Sensors> getAllSensors(){
        ArrayList<Sensors> list = new ArrayList<Sensors>();
        try{
            Connection conn = DriverManager.getConnection(url, usr,pwd);
            PreparedStatement pStmt;
            String query = "";
            pStmt = conn.prepareStatement(query);

            ResultSet rs = pStmt.executeQuery();
            while(rs.next()){
                Sensors tmp = new Sensors();

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


    public Sensors(int _id){
      /*  try {
           // Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
        this.id = _id;
        try{
            Connection conn = DriverManager.getConnection(url, usr, pwd);
            PreparedStatement pStmt;
            String query = "SELECT * FROM sensors WHERE idsensors = ?";
            pStmt = conn.prepareStatement(query);
            pStmt.setInt(1,this.id);

            ResultSet rs = pStmt.executeQuery();
            while(rs.next()){
                this.description = rs.getString("description");
                this.sensorTypeId = rs.getInt("sensor_type_idsensor_type");
                this.macaddress = rs.getString("macaddress");
                this.ipaddress = rs.getString("ipaddress");
               }



            PreparedStatement pStmt2;
            String query2 = "SELECT * FROM sensor_type WHERE idsensor_type = ?";
            pStmt2 = conn.prepareStatement(query2);
            pStmt2.setInt(1,this.sensorTypeId);
            ResultSet rs2 = pStmt2.executeQuery();
            while(rs2.next()){
                this.sensorTypeDescription = rs.getString("description");
                this.unit = rs.getString("unit");
                this.rangeLower = rs.getFloat("range_lower");
                this.rangeHigher = rs.getFloat("range_higher");

            }
            this.lastData = this.rangeHigher;
            conn.close();

        } catch (Exception e) {
            System.out.println("Errore di connessione: " + e.getMessage());
        }
    }


    public Sensors(){

    }


}
