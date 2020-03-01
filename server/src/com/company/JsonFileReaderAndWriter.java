package com.company;




import com.DBCon;
import models.Staff;
import models.admin;
import org.json.simple.*;



import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class JsonFileReaderAndWriter {
    CrypterDecrypter cr ;
    public JsonFileReaderAndWriter()
    {
        cr = new CrypterDecrypter();

    }

    /*
    create header for the json file
    */
    public  JSONObject createHeader(String status)
    {
        JSONObject header = new JSONObject();
        JSONObject headerHolder = new JSONObject();
        header.put("res",status);
        headerHolder .put("res", header);
        return headerHolder;
    }
    /*

  create JSONArray and fil it with staffs
  */
    public JSONArray getstaffArray(ArrayList<Staff> staffs)
    {
        JSONArray arry = new JSONArray();
        if (staffs != null)
        {
            for (Staff sta  : staffs){
            JSONObject staf = new JSONObject();
                JSONObject staffholder = new JSONObject();
            try {
                staf.put("stf", cr.toString(sta));
                byte[] img = Files.readAllBytes(sta.getImage().toPath());
                staf.put("img", cr.toString(img));
                staf.put("imgName",sta.getImage().getName());
                staffholder.put("stf",staf);
                arry.add(staffholder);
            }
            catch (Exception e )
            {

            }
            }
        }
        return  arry;
    }

    /*

 create JSONArray and fil it with admin
 */
    public JSONArray getadminArray(ArrayList<admin> staffs)
    {
        JSONArray arry = new JSONArray();
        if (staffs != null)
        {
            for (admin sta  : staffs){
                JSONObject staf = new JSONObject();
                JSONObject staffholder = new JSONObject();
                try {
                    staf.put("stf", cr.toString(sta));
                    byte[] img = Files.readAllBytes(sta.getImage().toPath());
                    staf.put("img", cr.toString(img));
                    staf.put("imgName", sta.getImage().getName());
                    staffholder.put("stf",staf);
                    arry.add(staffholder);
                }
                catch (Exception e )
                {
                        e.printStackTrace();
                }
            }
        }
        return  arry;
    }
    /*

    create arrayList  and fil it with staffs from th JsonArray
    */

    public  ArrayList<Staff> getArraylistOfStaffs(ArrayList<Staff> staffList, JSONArray object) {


       if (object != null && staffList != null)
       {
           object.forEach(emp->
           {

              JSONObject stfs = (JSONObject)emp;
               JSONObject stf = (JSONObject)stfs.get("stf");
              try {

                          DBCon db = new DBCon();;
                  Staff staff = (Staff) cr.fromString((String) stf.get("stf"));
                  File    img = new File("/root/sss/images/staffs/"+ (String) stf.get("imgName"));
                  if (img.exists())
                      img.delete();


                 staff.setImage(img);
                   staffList.add(staff);
                    staff = null;
                    img = null;
                    System.gc();
              }
              catch(Exception e){
                  e.printStackTrace();
           }


           });
       }

    return  staffList;

    }

    /*
            create arrayList of admins an fil it with admins from th JsonArray
    */
    public  ArrayList<admin> getArraylistOfAdmin(ArrayList<admin> adminsList, JSONArray object) {

        if (object != null && adminsList != null)
        {
            object.forEach(emp->
            {
                JSONObject stfs = (JSONObject)emp;
                JSONObject stf = (JSONObject)stfs.get("stf");
                try {
                    admin admin = (admin) cr.fromString((String) stf.get("stf"));


                   File img = new File("/root/sss/images/admins/"+ (String) stf.get("imgName"));
                    BufferedOutputStream bs = null;

                    try {
                        FileOutputStream fs = new FileOutputStream(new File(img.getPath()));
                        bs = new BufferedOutputStream(fs);
                        bs.write((byte[])cr.fromString((String)stf.get("img")));
                        bs.close();
                        bs = null;

                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        if (bs != null) try { bs.close(); } catch (Exception e) {}
                    }
                    admin.setImage(img);
                    adminsList.add(admin);
                }
                catch(Exception e){
                    e.printStackTrace();
                }


            });
        }

        return  adminsList;

    }



}
