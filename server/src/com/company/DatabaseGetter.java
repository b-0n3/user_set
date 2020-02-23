package com.company;



import com.DBCon;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;



public class DatabaseGetter <T> implements SettersAndGetters{
    private JSONObject RecObj;
    private  JSONArray object;
    private Client client;

    public DatabaseGetter(JSONObject object, Client client) {

        setRecObj(object);
        setClient(client);
    }


    @Override
    public JSONArray doFinal(JSONArray js) {
        JSONArray array = new JSONArray();
        JsonFileReaderAndWriter jsw = new JsonFileReaderAndWriter();


        int id = -1;
        if ( client.getIsloged())
        {
            id =  Integer.parseInt((String) getRecObj().get("id"));
            System.out.println("this sis id " + id );
            try{
                if ( id >= 0 ) {
                    DBCon db = new DBCon();
                    array.add(jsw.createHeader("Accepted"));
                    array.add(jsw.getstaffArray(db.getStaffArray(id)));
                } else
                    throw new IllegalArgumentException("rejected");

            }catch (Exception e)
            {
                e.printStackTrace();
                array = new JSONArray();
                array.add(jsw.createHeader("Rejected"));
            }
        }
        else
            array.add(jsw.createHeader("Rejected"));
        return array;
    }

    public JSONObject getRecObj() {
        return RecObj;
    }

    public void setRecObj(JSONObject recObj) {
        RecObj = recObj;
    }

    public JSONArray getObject() {
        return object;
    }

    public void setObject(JSONArray object) {
        this.object = object;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
