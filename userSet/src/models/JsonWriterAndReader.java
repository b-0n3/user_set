package models;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.ArrayList;

public class JsonWriterAndReader  {
    CrypterDecrypter cr;
    public  JsonWriterAndReader()
    {
        cr = new CrypterDecrypter();
    }


    /** req {request , usr ? , password ?}*/

    public JSONObject createHeader(String... req)
    {
        JSONObject object = new JSONObject();
        JSONObject objectHolder = new JSONObject();
       if(req != null) {
           object.put("req", req[0]);

           if (req[0].equals("check Password") || req[0].equals("pull") || req[0].equals("check username")|| req[0].equals("getAdmin"))
           {
                if (req[0].equals("pull"))
                {
                    object.put("id", req[1]);
                }
                else
                    {
                        object.put("usr", req[1]);
                        if (req[0].equals("check Password") )
                        {
                            object.put("password", req[2]);
                        }
                    }
           }
       }
             objectHolder.put("req", object);
        return objectHolder;
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
                    staffholder.put("stf",staf );
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
                    Staff staff = (Staff) cr.fromString((String) stf.get("stf"));
                    File img = new File("images/staffs/" + (String) stf.get("imgName"));
                    BufferedOutputStream bs = null;

                    try {
                        FileOutputStream fs = new FileOutputStream(img);
                        bs = new BufferedOutputStream(fs);
                        bs.write((byte[])cr.fromString((String)stf.get("img")));
                        bs.close();
                        bs = null;

                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        if (bs != null) try { bs.close(); } catch (Exception e) {}
                    }
                    staff.setImage(img);
                    staffList.add(staff);
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
                    File img = new File("images/admins/" + (String) stf.get("imgName"));
                    BufferedOutputStream bs = null;

                    try {
                        FileOutputStream fs = new FileOutputStream(img);
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
