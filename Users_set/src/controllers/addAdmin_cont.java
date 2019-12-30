package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.channels.NoConnectionPendingException;

import com.sun.scenario.effect.Effect;

import Users_set.admin;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class addAdmin_cont {
	@FXML
    private TextField firsName;
	
	private TextField Username;

    @FXML
    private TextField lastname;

    @FXML
    private TextField post;

    @FXML
    private TextField phoneNumber;

    @FXML
    private PasswordField Pass;

    @FXML
    private PasswordField repass;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Circle imageV;

    @FXML
    private Circle uploadImage;

    @FXML
    private Button Go;

    @FXML
    private Button GoBack;

    @FXML
    private Text errText;
    
    
    @FXML
    public void initialize(){
    	
    	
    	imageV.setOpacity(100.0);
    	imageV.setStroke(Color.SEAGREEN); 
    	imageV.setFill(Color.SNOW);
    	GoBack.setOnAction(event->{
    		
    	});
    	
    	
    	Go.setOnAction(evet->{
    		admin admin = new admin();
    			
    		try{
    			if(Pass.getText().length() > 8)
    			{
    				if(Pass.getText().equals(repass.getText()))
    				{
    					admin.setPassword(Pass.getText());
    				}
    				else
    				{
//    					Pass.setStyle("-fx-background-color : #fa163f");
//    
//    					System.out.println("change");
//    					Pass.setStyle("-fx-background-color : #faf5e4");
    					throw new IllegalArgumentException("Passwords doesn't match");
    				}
    			}
    			else
    			{
    				throw new IllegalArgumentException("Password size must be atleast 8 characters or numbers");
    			}
    			if (firsName.getText().length() > 1 )
    				admin.setFirstname(firsName.getText()); 
    			else
    				throw new IllegalArgumentException("firstname can't be empty");
    			if(lastname.getText().length() > 1)
    				admin.setLastName(lastname.getText());
    			else 
    				throw new IllegalArgumentException("lastName can't be empty");
    			if(phoneNumber.getText().matches("[2-9]\\d{2}[-.]\\d{3}[-.]\\d{4}"))
    				admin.setPhoneNumber(phoneNumber.getText());
    			else
    				throw new IllegalArgumentException("phoneNumber incorrect  \nsyntax :  061-234-5678");
    			admin.setPost(post.getText().isEmpty() ? "NO VALUE": post.getText());
    			if(admin.checkDepUsername(Username.getText()))
    				admin.setUsername(Username.getText());
    			else
    				throw new IllegalArgumentException("username taken try another one");
    			System.out.println("hell");
    			
    			

    	}catch(Exception e)
    	{
    		errText.setText(e.getMessage());
    	}
    	});
    	
    	imageV.setEffect(new DropShadow(+50d, 0d, +5d, Color.DARKSEAGREEN));
 		try {
 			Image image = new Image(new FileInputStream(new File("./fDe.png")));
 			
 			imageV.setFill(new ImagePattern (image));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			errText.setText(e.getMessage());
		}
 		uploadImage.setOpacity(100.0);
 		uploadImage.setStroke(Color.SEAGREEN); 
    	uploadImage.setFill(Color.SNOW);
    	uploadImage.setEffect(new DropShadow(+50d, 0d, +5d, Color.DARKSEAGREEN));
 		try {
 			Image image = new Image(new FileInputStream(new File("./uploadimage.png")));
 			
 			uploadImage.setFill(new ImagePattern (image));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			errText.setText(e.getMessage());
		}
 		uploadImage.setOpacity(0.0);
 		uploadImage.setOnMouseEntered(event ->{
 			imageV.setOpacity(0.0);
 			uploadImage.setOpacity(100.0);
 		});
    	uploadImage.setOnMouseExited(event->{
    		uploadImage.setOpacity(0.0);
    		imageV.setOpacity(100.0);
    	});
    	
    	
    }
}
