package com.company;




import com.DBCon;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sun.security.pkcs11.Secmod;


public class AdminGetter<T>  implements SettersAndGetters {
    private  JSONObject object;
    private Client client;

    public AdminGetter(JSONObject object, Client client) {
     setClient(client);
     setObject(object);
    }


    @Override
    public JSONArray doFinal(JSONArray js) {
        JsonFileReaderAndWriter jsw = new JsonFileReaderAndWriter();
        JSONArray array = new JSONArray();

        try {
            DBCon db = new DBCon();
            if ( client.getIsloged())
            {
             array.add(jsw.createHeader("Accepted"))  ;
             array.add(jsw.getadminArray(db.getAdminArray((String) object.get("usr"))));
            }else
                throw  new IllegalArgumentException("rejected");
        }
        catch ( Exception e)
        {
            array = new JSONArray();
            array.add(jsw.createHeader("Rejected"));
        }
        return array;
    }

    public JSONObject getObject() {
        return object;
    }

    public void setObject(JSONObject object) {
        this.object = object;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
