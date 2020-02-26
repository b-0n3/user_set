package controllers;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.channels.NoConnectionPendingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFileChooser;


import javafx.application.Platform;
import javafx.event.Event;
import models.*;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import org.apache.poi.ss.formula.functions.T;
import org.json.simple.JSONArray;

public class addAdmin_cont implements ControllerClass {
	@FXML
    private TextField firsName;
	@FXML
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
    private  admin realAdmin;
    private admin adm;
    File Image;
    	@FXML
    	public void goBacktoDash(Event event)
		{
			adminDashbordCont con = new adminDashbordCont();


			SceneChanger sc = new SceneChanger();
			System.out.println("done")  ;

				try {
					sc.changeScenes(event, "/view/adminDashbord.fxml", "Drag here", null, con, realAdmin);
				}catch(Exception e)
				{
					e.printStackTrace();
				}

		}
    @FXML
    public void initialize(){
		adm = new admin();
    	
    	imageV.setOpacity(100.0);
    	imageV.setStroke(Color.SEAGREEN); 
    	imageV.setFill(Color.SNOW);

    	GoBack.setOnAction(event->{
			goBacktoDash( event);
    	});
    	
    	
    	Go.setOnAction(evet->{
    	
    			
    		

    			
    			try {
					adm = new admin(Username.getText(), Pass.getText(), firsName.getText(),datePicker.getValue(), lastname.getText(), phoneNumber.getText(), post.getText().isEmpty() ? "NO VALUE": post.getText(), "hello");

					adm.setImage(Image);
    			} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

    			
    	
    		try {
				JsonWriterAndReader js = new JsonWriterAndReader();
				ArrayList<admin> aa = new ArrayList<>();
				aa.add(adm);
				JSONArray array = new JSONArray();
				array.add( js.createHeader("addAdmin"));
				array.add(js.getadminArray(aa));
				System.out.println(array);
			TCPClient Conn = new TCPClient();
				Conn.send(array, realAdmin.getUsername());
			JSONArray arr =Conn.rec();
				System.out.println(arr);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			goBacktoDash( evet);
    	
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
    	
    	uploadImage.setOnMouseClicked(event->{
    		
    		FileChooser fileChooser = new FileChooser();
    		
    		fileChooser.setTitle("choose image");
    		
    		ExtensionFilter ex = new ExtensionFilter("*.png", "*.png");
    		ExtensionFilter ex2 = new ExtensionFilter("*.jpeg", "*.jpeg");
    		ExtensionFilter ex3 = new ExtensionFilter("*.jpg", "*.jpg");
    		
    		fileChooser.getExtensionFilters().addAll(ex,ex2,ex3);
    	
    		
    		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		
    		File selectedFile = fileChooser.showOpenDialog(stage);
    		
    		if (selectedFile != null) {
    			Image = selectedFile;
    			adm.setImage(selectedFile);
    		//System.out.println(admin.getImage().getPath());
    		try {
     			Image image = new Image(new FileInputStream(adm.getImage()));
     			
     			imageV.setFill(new ImagePattern (image));
    		} catch (FileNotFoundException e) {
    			// TODO Auto-generated catch block
    			errText.setText(e.getMessage());
    		}}
    		else 
    			throw new IllegalArgumentException("invalid Image");
    	});
    	
    	
    }

	@Override
	public void preloadData(ArrayList<Staff> staff, admin admin) {
		this.realAdmin = admin;
	}
}
