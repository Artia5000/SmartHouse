package it.webApi;

/**
 * Created by lorca on 16/05/2016.
 */
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import it.modelObjects.Devices;
import static com.opensymphony.xwork2.Action.SUCCESS;

public class AuthAction extends ActionSupport {
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAuthorized() {
        return authorized;
    }

    public void setAuthorized(String authorized) {
        this.authorized = authorized;
    }

    private String authorized;
    private String uuid;
private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private InputStream inputStream;
    public InputStream getInputStream() {
        return inputStream;
    }



    public String isPhoneAuthorized() throws Exception{
        JSONObject obj = new JSONObject();
        if(uuid.isEmpty()){
            uuid = "1000";
        }
        Devices phone = new Devices(uuid);
        obj.put("response",phone.getAuthorized().toString());

        inputStream = new ByteArrayInputStream(obj.toString().getBytes());
        return "success";
    }

    public String requestPhoneAuthorization() throws Exception{
        Devices dev = new Devices(uuid);
        JSONObject obj = new JSONObject();
        dev.setDescription(description);
        if(dev.requestAuth()) {
            obj.put("response","OK");
            inputStream = new ByteArrayInputStream(obj.toString().getBytes());
            return "success";
        }else{
            obj.put("response","ERROR");
            inputStream = new ByteArrayInputStream(obj.toString().getBytes());
            return "error";
        }
    }


    public String getMobileDevicesList(){

        ArrayList<Devices> list = new Devices().getAllDevices();
        JSONArray jlist = new JSONArray();


        for(int i=0;i<list.size();i++){
            JSONObject tmp = new JSONObject();

            Devices dev = list.get(i);
            tmp.put("idDevice",dev.getId());
            tmp.put("description",dev.getDescription());
            tmp.put("uuid",dev.getUuid());
            tmp.put("timestamp", dev.getTimestamp().toString());
            tmp.put("status",dev.getAuthorized().toString());
            jlist.add(tmp);
        }

        inputStream = new ByteArrayInputStream(jlist.toString().getBytes());

        return "success";
    }

    public String grantAccessToDevice(){
        JSONObject obj = new JSONObject();
        Devices dev = new Devices(uuid);
        if(dev.grantAccess()){
            obj.put("response","OK");
            inputStream = new ByteArrayInputStream(obj.toString().getBytes());
            return "success";
        }else{
            obj.put("response","ERROR");
            inputStream = new ByteArrayInputStream(obj.toString().getBytes());
            return "error";
        }

    }

    public String denyAccessToDevice(){
        JSONObject obj = new JSONObject();
        Devices dev = new Devices(uuid);
        if(dev.denyAccess()){
            obj.put("response","OK");
            inputStream = new ByteArrayInputStream(obj.toString().getBytes());
            return "success";
        }else{
            obj.put("response","ERROR");
            inputStream = new ByteArrayInputStream(obj.toString().getBytes());
            return "error";
        }

    }


}
