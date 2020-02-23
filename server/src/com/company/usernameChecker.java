package com.company;

import com.DBCon;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class usernameChecker implements SettersAndGetters {

    private  JSONObject object;
    private Client client;

    public usernameChecker(JSONObject object, Client client) {
        setClient(client);
        setObject(object);
    }

    @Override
    public JSONArray doFinal(JSONArray js) {
        JSONArray array = new JSONArray();
        JsonFileReaderAndWriter jsw = new JsonFileReaderAndWriter();
        try {
            DBCon db = new DBCon();

            if (db.checkAdmin((String) object.get("usr")))
            {
                array.add(jsw.createHeader("Accepted"));
            }else
                throw  new IllegalArgumentException("rejected");
        }catch (Exception e)
        {
            array = new JSONArray();
            array.add(jsw.createHeader("Rejected"));
        }
        return null;
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
