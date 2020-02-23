package models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCon {
	private  Connection conn = null;
	private  PreparedStatement ps = null;
    private  ResultSet rs = null;
    private static boolean isConnected = false;
	
	public DBCon() throws SQLException {
		
	
		isConnected = getCon();
	}
	
	/*
	 *	get connection with the server  
	 */
	public boolean getCon()
	{
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn = DriverManager.getConnection("jdbc:mysql://ec2-3-134-114-56.us-east-2.compute.amazonaws.com:3306/user_set?zeroDateTimeBehavior=convertToNull&user=root&password=Abdelait12.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (false);
		}
		
		
		return true;
	}
	
	/*
	 *	push an admin  
	 */
	public  boolean pushAdmin ( admin admin) throws SQLException, FileNotFoundException
	{
		
			System.out.println("almost");
			ps = conn.prepareStatement("insert into admin(username, firstname,lastname,phonNumber,birth_day,post,password,salt,image) values(?,?,?,?,?,?,?,?,?)");
			ps.setString(1, admin.getUsername());
			ps.setString(2, admin.getFirstname());
			ps.setString(3, admin.getLastName());
			ps.setString(4, admin.getPhoneNumber());
			Date db = Date.valueOf(admin.getBirthDay());
			ps.setDate(5, db);
			ps.setString(6, admin.getUsername());
			ps.setString(7, admin.getUsername());
			ps.setBlob(8, new javax.sql.rowset.serial.SerialBlob(admin.getSalt()));
			InputStream in = new FileInputStream(admin.getImage());
			ps.setBlob(9, in);
			ps.execute();
			System.out.println("done");
			
	
		return false;
	}
	public boolean getAdmin(admin admin)
	{
		
		return false;
	}

}
