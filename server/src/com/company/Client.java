package com.company;






import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import models.admin;
import  org.json.simple.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class Client {


    private  String ip, port;
    private byte[] Key;
    private admin admin;

    private  boolean isloged;
    private boolean jobDone;
    private boolean insertdone = false;
    private  int tim;
     static boolean threadisDone = false;
     private String path;
    java.util.Timer timer = new Timer();
    private boolean timerStarte = false;

    public Client (String ip, String port)
    {
        this(ip, port,null);
    }
    public  Client (String ip, String port, byte[] key)
    {
        isloged = false;
        jobDone = false;
            tim = 15;
            this .ip= ip;
            this.port = port;
            setKey(key);



    }



    public void setTim(int i) {
        this.tim =i;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    public void setAdmin(admin adm){
        this.admin = adm;
    }
    public admin getAdmin()
    {
        return  this.admin;
    }

    private class myTimer extends TimerTask {

        @Override
        public void run() {
            if (tim == 0 )
            {
                jobDone = true;
                return ;
            }
           tim--;
        }
    }

    public SettersAndGetters getinstance(JSONObject object) {

        String request = (String) object.get("req");
        if (request != null )
        {
            if (request.equals("logout"))
                return (null);
            if(request.equals("check Password"))
                return(new PasswordChecker(this));
            else if(request.equals("push"))
                return (new databaseInserter(this, object));
            else if(request.equals("pull"))
                return (new DatabaseGetter(object, this));
            else if (request.equals("addAdmin") )
                return (new AdminPlus(object,this));
            else if (request.equals("getAdmin"))
                return (new AdminGetter( object,this));
            else if (request.equals("check username"))
                return(new usernameChecker(object, this));
            if(request.equals("sendmeEmail"))
            {

                System.out.println("here i can start mailing");
               return  new inserter( this);
            }

        }

        return(null);
    }

    public void setIp( String ip)
    {
        this.ip = ip;
    }

    public void setPort(String port)
    {
        this.port = port;
    }

    public void setIsloged(boolean id)
    {
        this .isloged = id;
    }
    public boolean getIsloged()
    {
        return this.isloged;
    }
    public void setKey(byte[] key)
    {
        this.Key = key;
    }

    public byte[] getKey()
    {
        return this.Key;
    }
    public String getIp()
    {
        return this.ip;
    }
    public String getPort()
    {
        return this.port;
    }

    public boolean isJobDone() {
        return jobDone;
    }

    public void setJobDone(boolean jobDone) {
        this.jobDone = jobDone;
    }

    public JSONArray Return(JSONArray array) {
        SettersAndGetters instance =  getinstance((JSONObject) ((JSONObject)array.get(0)).get("req"));
        JSONArray ara ;
        if (instance == null) {
          setIsloged(false);
          setJobDone(true);
            return null;
        }else {
           ara = instance.doFinal(array);
            System.out.println( ara.toJSONString());

            if (instance instanceof PasswordChecker) {
                CrypterDecrypter cr = new CrypterDecrypter();
                if (((PasswordChecker) instance).getClient().getIsloged())
                {
                    try {
                        if (!timerStarte) {
                            timer.schedule(new myTimer(), 0, 2000);
                        timerStarte = true;
                        }
                        setAdmin( (admin) cr.fromString(((String) (((JSONObject)((JSONObject) ((JSONArray) ara.get(1)).get(0)).get("stf")).get("stf")))));
                        path = "/root/toDo/"+getAdmin().getUsername()+"/";
                        System.out.println(path);
                        File pa =   new File(path);
                            pa.mkdirs();
                      File log =   new File("/root/logs/"+ getAdmin().getUsername()+"/");
                      log.mkdirs();

                      File aa =    new File("/root/logs/"+ getAdmin().getUsername()+"/AcceptedtoSend.txt");
                        aa.createNewFile();
                       File re = new File("/root/logs/"+ getAdmin().getUsername()+"/rejectedtoSend.txt");
                            re.createNewFile();
                    } catch (Exception e) {
                            e.printStackTrace();
                    }
                    this.setIsloged(true);
                }
            }
        }
            return(ara);
    }
}
