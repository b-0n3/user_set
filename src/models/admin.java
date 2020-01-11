package models;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;


public class admin {
	private String username;
	private String password;
	private String dapass;
	private String firstname;
	private String lastName;
	private String phoneNumber;
	private LocalDate 	birthDay;
	private String Post;
	private File image;
	
	
	public admin() {
		// TODO Auto-generated constructor stub
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public boolean passCorrect() throws SQLException {
		connect_and_getPass();
		if(this.password.equals(getDapass()))
			return true;
		return false;
	}
	
	private void connect_and_getPass() throws SQLException
	{
		 Connection conn = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        
		try{  
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			System.out.println("2");
			conn = DriverManager.getConnection("jdbc:mysql://ec2-3-17-56-24.us-east-2.compute.amazonaws.com:3306/user_set?zeroDateTimeBehavior=convertToNull&user=root&password=Abdelait12.");
			System.out.println("1");  
			ps = conn.prepareStatement("select password from admin where username='"+getUsername()+"'");
			System.out.println("3");
				rs = ps.executeQuery(); 
			
			while(rs.next()) 
			{
				setDapass(rs.getString(1));
				System.out.println("thie   " + getDapass());
			}
				
			conn.close();  
			}catch(Exception e){ 
				
				}
		finally {
			if(conn != null)
				conn.close();
			if(ps != null)
				ps.close();
			if(rs != null)
				rs.close();
		}
		
			
	}  
			  


	public String getDapass() {
		return dapass;
	}


	public void setDapass(String dapass) {
		this.dapass = dapass;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public LocalDate getBirthDay() {
		return birthDay;
	}


	public void setBirthDay(LocalDate birthDay) {
		this.birthDay = birthDay;
	}


	public String getPost() {
		return Post;
	}


	public void setPost(String post) {
		Post = post;
	}


	public boolean checkDepUsername(String text) throws SQLException {
		boolean isfound = false;
		

			Connection con = null;
			ResultSet rs = null;
			PreparedStatement ps = null;
			
			try{  
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());
				con = DriverManager.getConnection("jdbc:mysql://ec2-3-17-56-24.us-east-2.compute.amazonaws.com:3306/user_set?zeroDateTimeBehavior=convertToNull&user=root&password=Abdelait12.");
			
				ps = con.prepareStatement("select firstname from admin where username='"+text+"'");
				
				 rs = ps.executeQuery(); 
				if(!rs.next())
					return(false);
			
				con.close();  
				}catch(Exception e){ 
			e.printStackTrace();
					}
			finally {
				if(con != null)
					con.close();
				if(ps != null)
					ps.close();
				if(rs != null)
					rs.close();
			}
			
			
	
		return true;
	}


	public File getImage() {
		return image;
	}


	public void setImage(File image) {
		this.image = image;
	}
	

}
