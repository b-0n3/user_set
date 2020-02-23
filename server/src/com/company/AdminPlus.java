package com.company;



import com.DBCon;
import models.admin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class AdminPlus<T> implements SettersAndGetters{
    private Client client;
    private JSONObject object;

    public AdminPlus(JSONObject object, Client client) {
        setClient(client);
        setObject(object);
    }

    @Override
    public JSONArray doFinal(JSONArray js) {
        JSONArray array = new JSONArray();
        JsonFileReaderAndWriter jsw = new JsonFileReaderAndWriter();
        try {

            if (client.getIsloged())
            {
                DBCon db = new DBCon();
                db.InsertAdmins(jsw.getArraylistOfAdmin(new ArrayList<admin>(), (JSONArray) js.get(1)));
                array.add(jsw.createHeader("Accepted"));
            }else
                throw  new IllegalArgumentException("rejected");
        }catch(Exception e){

            array.add(jsw.createHeader("Rejected"));
        }
        return array;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public JSONObject getObject() {
        return object;
    }

    public void setObject(JSONObject object) {
        this.object = object;
    }
}





