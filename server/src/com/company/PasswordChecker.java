package com.company;


import com.DBCon;

import models.admin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;


public class  PasswordChecker  implements SettersAndGetters  {
    private JSONArray object;

    private Client client;
        private DBCon db;
    public PasswordChecker( Client client){

    setClient(client);
    }




    public boolean  check()  {
    try {
        CrypterDecrypter cr = new CrypterDecrypter();
        String password = ((String)(((JSONObject)((JSONObject)getObject().get(0)).get("req")).get("password")));

        String username = ( String) ((((JSONObject)((JSONObject)getObject().get(0)).get("req")).get("usr")));
        System.out.println("user is  + "  + username   + "pass is " + password);
          db = new DBCon();
        if (password != null && username != null) {
            if (db.checkPass(username, password)) {

                return true;
            }
        }
    }catch (Exception e)
    {
        e.printStackTrace();
    }
        return false;
    }

    private JSONArray getObject() {
        return  this.object;
    }


    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return this.client;
    }


    @Override
    public JSONArray doFinal(JSONArray js)  {
        setObject(js);
        JSONArray array = new JSONArray();
        JsonFileReaderAndWriter jwr = new JsonFileReaderAndWriter();
        if ( check())
        {
            try {
            array.add(jwr.createHeader("Accepted"));
            array.add(jwr.getadminArray(db.getAdminArray(( String) ((((JSONObject)((JSONObject)getObject().get(0)).get("req")).get("usr"))))));
            if ( array.size() <= 1) {
                this.client.setIsloged(false);
                array = new JSONArray();
                array.add(jwr.createHeader("Rejected"));
                return (array);
            }
            else
                this.client.setIsloged(true);

             }catch(Exception e){
                array = new JSONArray();
            array.add(jwr.createHeader("Rejected"));
            }
        }
        else
        {
            array = new JSONArray();
            array.add(jwr.createHeader("Rejected"));
        }

        return array;
    }

    private void setObject(JSONArray js) {
        this.object= js ;
    }
}
