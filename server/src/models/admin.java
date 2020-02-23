package models;

import com.company.PasswordGenerator;

import java.io.File;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

import java.time.LocalDate;


public class admin implements Serializable{
	private String username;
	private String password;
	private static final long serialversionUID =
			1568630193381428614L;
	private String firstname;
	private String lastName;
	private String phoneNumber;
	private LocalDate 	birthDay;
	private String Post;
	private File image;
	private byte[] salt;
	private  String email;



	public admin()
	{

	}
	public admin(String username , String firstname ,LocalDate birthDay, String lastname, String phoneNumber, String post, File image, String email)
	{
		setUsername(username);

		setEmail(email);
		setFirstname(firstname);
		setLastName(lastname);
		setPhoneNumber(phoneNumber);
		setBirthDay(birthDay);
		setPost(post);
		setImage(image);
	}

	public admin(String username , String password, String firstname ,LocalDate birthDay, String lastname, String phoneNumber, String post, String email) throws NoSuchAlgorithmException{
		this(username, password, firstname,birthDay,lastname, phoneNumber,post, PasswordGenerator.getSalt(), new File("./mDe.png"),email);
		
		
	}
	public admin(String username , String password, String firstname , LocalDate birthDay,String lastname, String phoneNumber, String post,byte [] salt, File Image,String email){
		setUsername(username);
		setSalt(salt);
		setPassword(PasswordGenerator.getSHA512Password(password, this.getSalt()));
		setFirstname(firstname);
		setLastName(lastname);
		setPhoneNumber(phoneNumber);
		setBirthDay(birthDay);
		setPost(post);
		setImage(Image);
		setEmail(email);
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





	public File getImage() {
		return image;
	}


	public void setImage(File image) {
		this.image = image;
	}


	public byte[] getSalt() {
		return salt;
	}


	public void setSalt(byte[] salt2) {
		this.salt = salt2;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
