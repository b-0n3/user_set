package com.company;

import com.DBCon;
import models.Staff;
import models.admin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import sun.security.pkcs11.Secmod;

import javax.naming.spi.StateFactory;
import java.beans.Encoder;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class inserter implements SettersAndGetters
{


    private static ExecutorService Lunchers =  Executors.newFixedThreadPool(2);
    private  Client client;
    public inserter(Client client) {
        setClient(client);
    }

    @Override
    public JSONArray doFinal(JSONArray js) {
            JSONArray arr = new JSONArray();

            try {


            if (client.getIsloged())
            {
                if (new File(client.getPath()).exists())
                {
                    arr.add(new JsonFileReaderAndWriter().createHeader("Accepted"));
                   Runnable rn = new Runnable() {
                       @Override
                       public void run() {
                           String email = client.getAdmin().getEmail();
                           File[] files = new File(client.getPath()).listFiles();
                           JSONParser Parser = new JSONParser();
                           String username = client.getAdmin().getUsername();
                           for(File file :files)
                           {
                               try (FileReader reader = new FileReader(file.getPath()))
                               {
                                   //Read JSON file
                                   Object obj = Parser.parse(reader);

                                   JSONArray stafflist = (JSONArray) obj;
                                   CrypterDecrypter cr = new CrypterDecrypter();
                                   DBCon db = new DBCon();

                                   stafflist.forEach( emp-> {
                                       JSONObject stfs = (JSONObject)emp;
                                       JSONObject stf = (JSONObject)stfs.get("stf");
                                       try {
                                           Staff staff = (Staff) cr.fromString((String) stf.get("stf"));
                                           if (db.checkStaff(staff))
                                           {
                                               File    img = new File("/root/sss/images/staffs/"+ (String) stf.get("imgName"));
                                               BufferedOutputStream bs = null;

                                               try {
                                                   FileOutputStream fs = new FileOutputStream(new File(img.getPath()));
                                                   bs = new BufferedOutputStream(fs);
                                                   bs.write((byte[]) cr.fromString((String) stf.get("img")));
                                                   bs.close();
                                                   bs = null;

                                               } catch (Exception e) {
                                                   e.printStackTrace();
                                               }finally {
                                                   if (bs != null) try { bs.close(); } catch (Exception e) {}
                                               }
                                               staff.setImage(img);
                                               db.pushStaff(staff);
                                               try {
                                                   Files.write(Paths.get("/root/logs/"+username+"/AcceptedtoSend.txt"), (String.valueOf(staff.getId()) + "\n").getBytes(), StandardOpenOption.APPEND);
                                               }catch (IOException e) {
                                                   e.printStackTrace();
                                               }
                                           }
                                           else {
                                               try {
                                                   Files.write(Paths.get("/root/logs/"+username+"/rejectedtoSend.txt"), (String.valueOf(staff.getId()) + "\n").getBytes(), StandardOpenOption.APPEND);
                                               }catch (IOException e) {
                                                   e.printStackTrace();
                                               }
                                           }
                                       }catch (Exception e)
                                       {e.printStackTrace();}
                                   });
                               }catch (Exception e)
                               {
                                   e.printStackTrace();
                               }

                               file.delete();
                           }
                           try {
                               List<String> Accepted = readFileInList("/root/logs/"+username+"/AcceptedtoSend.txt");
                               List<String> rejected = readFileInList("/root/logs/"+username+"/rejectedtoSend.txt");
                               if (email != null) {
                                   mailSender ml = new mailSender(email);
                                   ml.sendAcc(Accepted);
                                   ml.sendRE(rejected);
                               }
                           }catch(Exception e)
                           {
                               e.printStackTrace();
                           }
                       }
                   };
                    Lunchers.execute(rn);
                }else
                    throw new IllegalArgumentException("rejected");
            }else
                throw new IllegalArgumentException("rejected");
            }
            catch(Exception e)
            {
                arr.add(new JsonFileReaderAndWriter().createHeader("rejected"));
            }
        return arr;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public static List<String> readFileInList(String fileName) throws IOException
    {

        List<String> lines ;

        lines =Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        File fl = new File(fileName);


        return lines;
    }
}
