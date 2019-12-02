package Users_set;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class admin {
	private String username;
	private String password;
	private String dapass;
	
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


	public boolean passCorrect() {
		connect_and_getPass();
		if(this.password.equals(getDapass()))
			return true;
		return false;
	}
	
	private void connect_and_getPass()
	{
		 Connection conn = null;
	        PreparedStatement ps = null;
	        ResultSet resultSet = null;
		try{  
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			System.out.println("2");
			conn = DriverManager.getConnection("jdbc:mysql://ec2-3-17-56-24.us-east-2.compute.amazonaws.com:3306/user_set?zeroDateTimeBehavior=convertToNull&user=root&password=Abdelait12.");
			System.out.println("1");  
			ps = conn.prepareStatement("select password from admin where username='"+getUsername()+"'");
			System.out.println("3");
			//ps.setString(1, getUsername());
			ResultSet rs = ps.executeQuery(); 
			
			while(rs.next()) 
			{
				setDapass(rs.getString(1));
				System.out.println("thie   " + getDapass());
			}
				//System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
			conn.close();  
			}catch(Exception e){ System.out.println(e.getMessage() + "    fsdfdsf");} 
			
	}  
			  


	public String getDapass() {
		return dapass;
	}


	public void setDapass(String dapass) {
		this.dapass = dapass;
	}
	

}
