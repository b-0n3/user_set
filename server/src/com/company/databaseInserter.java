package com.company;



import com.DBCon;
import models.Staff;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class databaseInserter implements SettersAndGetters {
    private JSONArray object;
    private ArrayList<Staff> staffs;
    private Client client ;
    private JSONObject reqObject;
    private static ExecutorService ln =  Executors.newFixedThreadPool(10);
    private  String path;

    public databaseInserter( Client client, JSONObject reqObject) {
    setClient(client);
    setReqObject(reqObject);
        path = client.getPath();
        System.out.println(path);
    }



    @Override
    public JSONArray doFinal(JSONArray js) {
        JSONArray array  = new JSONArray();
        JsonFileReaderAndWriter jsw = new JsonFileReaderAndWriter();
        setObject((JSONArray)js.get(1));
        if (!client.getIsloged())
        {
            array.add(jsw.createHeader("Rejected"));
        }
        else
        {
            try {
                try (FileWriter file = new FileWriter(path + getUniqueFileName()+ ".json")) {

                    file.write(getObject().toJSONString());
                    file.flush();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                array.add(jsw.createHeader("Accepted"));
            }catch (Exception e)
            {
                e.printStackTrace();
                array.add(jsw.createHeader("Rejected"));
            }finally {
                System.gc();
            }
        }
   return array;
    }

    /**
     * This method will receive a String that represents a file name and return a
     * String with a random, unique set of letters prefixed to it
     */
    private String getUniqueFileName()
    {
        String newName;

        //create a Random Number Generator
        SecureRandom rng = new SecureRandom();

        //loop until we have a unique file name
        do
        {
            newName = "";

            //generate 32 random characters
            for (int count=1; count <=32; count++)
            {
                int nextChar;

                do
                {
                    nextChar = rng.nextInt(123);
                } while(!validCharacterValue(nextChar));

                newName = String.format("%s%c", newName, nextChar);
            }


        } while (!uniqueFileInDirectory(newName));

        return newName;
    }


    /**
     * This method will search the images directory and ensure that the file name
     * is unique
     */
    public boolean uniqueFileInDirectory(String fileName)
    {
        File directory = new File(path);

        File[] dir_contents = directory.listFiles();

        for (File file: dir_contents)
        {
            if (file.getName().equals(fileName))
                return false;
        }
        return true;
    }

    /**
     * This method will validate if the integer given corresponds to a valid
     * ASCII character that could be used in a file name
     */
    public boolean validCharacterValue(int asciiValue)
    {

        //0-9 = ASCII range 48 to 57
        if (asciiValue >= 48 && asciiValue <= 57)
            return true;

        //A-Z = ASCII range 65 to 90
        if (asciiValue >= 65 && asciiValue <= 90)
            return true;

        //a-z = ASCII range 97 to 122
        if (asciiValue >= 97 && asciiValue <= 122)
            return true;

        return false;
    }


    public ArrayList<Staff> getStaffs() {
        return staffs;
    }

    public void setStaffs(ArrayList<Staff> staffs) {
        this.staffs = staffs;
    }

    public void setObject(JSONArray object) {
        this.object = object;
    }
    public JSONArray getObject(){
        return  this.object;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public JSONObject getReqObject() {
        return reqObject;
    }

    public void setReqObject(JSONObject reqObject) {
        this.reqObject = reqObject;
    }
}
