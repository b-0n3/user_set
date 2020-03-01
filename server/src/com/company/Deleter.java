package com.company;

import com.DBCon;
import models.Staff;
import org.json.simple.JSONArray;

import java.util.ArrayList;

public class Deleter implements SettersAndGetters {
    private  Client client;
    public Deleter(Client client) {
        this.client = client;
    }

    @Override
    public JSONArray doFinal(JSONArray js) {
        JsonFileReaderAndWriter jws = new JsonFileReaderAndWriter();
        JSONArray toRet = new JSONArray();
      try {
          DBCon con = new DBCon();
          ArrayList<Staff> staffA = jws.getArraylistOfStaffs(new ArrayList<Staff>(), js);
          staffA.forEach(stf -> {

             try{
                 con.Delete(stf);
             }catch (Exception e)
             {
                 toRet.add(jws.createHeader("Rejected"));
                 throw  new IllegalArgumentException("no connection");
             }

          });
      }catch (Exception e)
      {
          toRet.add(jws.createHeader("Rejected"));

          e.printStackTrace();
            return toRet;
      }
      toRet.add(jws.createHeader("Accepted"));
        return toRet;
    }
}
