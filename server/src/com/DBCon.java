package com;

import com.company.PasswordGenerator;
import models.Staff;
import models.admin;


import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class DBCon {
	private  Connection conn = null;
	private  PreparedStatement ps = null;
    private  ResultSet rs = null;

	
	public DBCon() throws SQLException {
		

     getCon();
	}

    public   boolean checkPass(String username, String password) throws  Exception{
        getCon();
	    ps =conn.prepareStatement("select * from admin where username=?");
        ps.setString(1 , username);
	    rs = ps.executeQuery();
	    String realpassword= null;
	    byte salt[] = null;
	    
	    while (rs.next())
        {
        	realpassword = rs.getString("password");
			Blob blob = rs.getBlob("salt");

			//convert into a byte array
			int blobLength = (int) blob.length();
			salt = blob.getBytes(1, blobLength);
        }
        rs.close();
	    ps.close();
	    conn.close();
        if (PasswordGenerator.getSHA512Password(password,salt).equals(realpassword))
        	return true;
        return false;
	}


    /*
	 *	get connection with the server  
	 */
	public   boolean getCon()
	{
		try {

            Class.forName("com.mysql.jdbc.Driver");
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn = DriverManager.getConnection("jdbc:mysql://ec2-3-16-11-100.us-east-2.compute.amazonaws.com:3306/user_set","root","Abdelait12.");

		} catch (Exception e) {

			e.printStackTrace();
			return (false);
		}
		return true;
	}

	public ArrayList<admin> getAdminArray(String username)throws  SQLException{
        getCon();
        ArrayList<admin> admins = new ArrayList<>();
        ps = conn.prepareStatement("select * from admin where username like '"+ username+ "%'");

        rs = ps.executeQuery();
        while (rs.next())
        {

            admins.add(new admin(
                    rs.getString("username")
                    ,rs.getString("firstname")
                    ,rs.getDate("birth_day").toLocalDate()
                    ,rs.getString("lastname")
                    ,rs.getString("phonNumber")
                    ,rs.getString("post")
                    ,new File(rs.getString("image"))
                    ,rs.getString("email")
            ));
        }
        conn.close();
        return  admins;
    }


    /*
     *	push an admin
     */

    public  synchronized boolean pushAdmin ( admin admin) throws SQLException
    {
        getCon();
        ps = conn.prepareStatement("insert into admin(username, firstname,lastname,phonNumber,birth_day,post,password,salt,image,email) values(?,?,?,?,?,?,?,?,?,?)");
        ps.setString(1, admin.getUsername());
        ps.setString(2, admin.getFirstname());
        ps.setString(3, admin.getLastName());
        ps.setString(4, admin.getPhoneNumber());
        Date db = Date.valueOf(admin.getBirthDay());
        ps.setDate(5, db);
        ps.setString(6, admin.getPost());
        ps.setString(7, admin.getPassword());
        ps.setBlob(8, new javax.sql.rowset.serial.SerialBlob(admin.getSalt()));

        ps.setString(9,admin.getImage().getPath() );
        ps.setString(10,admin.getEmail());
        ps.execute();
        conn.close();


        return false;
    }

	public ArrayList<Staff> getStaffArray(int id) throws  SQLException
	{
        getCon();
		ArrayList<Staff> staffs = new ArrayList<>();
        ps = conn.prepareStatement("select * from staff where id like '"+id +"%'");

        rs = ps.executeQuery();
        while (rs.next())
        {
            Staff stf = new Staff(rs.getInt("id"),
                    rs.getString("birth_day"),
                    rs.getString("birth_place")
                    ,rs.getString("sex").charAt(0)
                    ,rs.getString("sit_fam")
                    ,rs.getString("nationalite")
                    ,rs.getString("date_embauche")
                    ,rs.getString("grade")
                    ,rs.getString("functio_n")
                    ,rs.getString("post")
                    ,rs.getString("categorie")
                    ,rs.getInt("echelon")
                    ,rs.getString("ent_effect")
                    ,rs.getString("regime_retraite")
                    ,rs.getInt("affil_recore")
                    ,rs.getString("date_der_promo")
                    ,new File(rs.getString("image"))
                    ,rs.getString("cin")
                    ,rs.getString("section_analytique")
            );
            staffs.add(stf);
        }
        rs.close();
        ps.close();
        conn.close();
		return staffs;
	}


    public void InsertStaffs(ArrayList<Staff> arraylistOfStaffs) throws  Exception{
    for (Staff staff : arraylistOfStaffs)
	{

		    if (checkStaff(staff))
			    pushStaff(staff);


	}
	}

    public boolean checkStaff(Staff staff) throws  Exception {
        getCon();
        ps = conn.prepareStatement("select sit_fam from staff where id=?");
        ps.setInt(1, staff.getId());
        rs = ps.executeQuery();
        String  duplicate = null;
        while(rs.next()){
            duplicate = rs.getString("sit_fam");
        }
         if ( duplicate != null) {
            rs.close();
             ps.close();
             return false;
         }
        rs.close();
        ps.close();
        conn.close();
	    return  true;
    }

    public void pushStaff(Staff staff) throws  Exception{
        getCon();
		ps = conn.prepareStatement("insert into staff(id, birth_day,birth_place,sex,sit_fam,nationalite,date_embauche,grade,functio_n,post,categorie,echelon," +
                "ent_effect,regime_retraite,affil_recore,date_der_promo,image, cin, section_analytique) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		ps.setInt(1, staff.getId());
		ps.setString(2, staff.getBirth_day());
        ps.setString(3,staff.getBirth_place());
        ps.setString(4, String.valueOf(staff.getSex()));
        ps.setString(5,staff.getSit_fam());
        ps.setString(6,staff.getNationalite());
        ps.setString(7,staff.getDate_embauche());
        ps.setString(8,staff.getGrade());
        ps.setString(9,staff.getFunctio_n());
        ps.setString(10,staff.getPost());
        ps.setString(11,staff.getCategorie());
        ps.setInt(12, staff.getEchelon());
        ps.setString(13,staff.getEnt_effect());
        ps.setString(14,staff.getRegime_retraite());
        ps.setInt(15, staff.getAffil_recore());
        ps.setString(16,staff.getDate_der_promo());
        ps.setString(17,staff.getImage().getPath());
        ps.setString(18,staff.getCin());
        ps.setString(19,staff.getSection_analytique());
        ps.execute();
        ps.close();
        conn.close();
	}

	public void InsertAdmins(ArrayList<admin> AdminList) throws  Exception
	{
		for(admin ad : AdminList)
		{

			    if (checkAdmin(ad.getUsername()))
				    pushAdmin(ad);

		}
	}

    public boolean checkAdmin(String username) throws  Exception {
        getCon();
        ps = conn.prepareStatement("select * from staff where username=?");
        ps.setString(1, username);
        rs = ps.executeQuery();
        String  duplicate = null;
        while(rs.next()){
            duplicate = rs.getString("username");
        }

        if ( duplicate != null) {

            return false;
        }

        conn.close();
        return  true;

    }




}
