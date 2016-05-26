package it.webApi;

/**
 * Created by lorca on 16/05/2016.
 */

import com.opensymphony.xwork2.ActionSupport;
import it.modelObjects.Devices;
import it.modelObjects.Sensors;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class SensorAction extends ActionSupport {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    private InputStream inputStream;
    public InputStream getInputStream() {
        return inputStream;
    }



    public String getSensorInfo() throws Exception{
        JSONObject obj = new JSONObject();
        Sensors sens = new Sensors(id);

        obj.put("idSensor",sens.getId());
        obj.put("idSensorType",sens.getSensorTypeId());
        obj.put("description",sens.getDescription());
        obj.put("ipaddress",sens.getIpaddress());
        obj.put("macaddress",sens.getMacaddress());
        obj.put("lastData",sens.getLastData());
        obj.put("rangeHigher",sens.getRangeHigher());
        obj.put("rangeLower",sens.getRangeLower());
        obj.put("unit",sens.getUnit());
        obj.put("sensorTypeDescription",sens.getSensorTypeDescription());

        inputStream = new ByteArrayInputStream(obj.toString().getBytes());
        return "success";
    }


}
