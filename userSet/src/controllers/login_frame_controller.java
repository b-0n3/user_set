package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;

import javafx.stage.Stage;
import models.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class login_frame_controller {

	
	   @FXML
	    private Pane pane;

	    @FXML
	    private TextField User_field;

	    @FXML
	    private PasswordField pass_field;

	    @FXML
	    private Button sub_button;

	    @FXML
	    private Text error_msg;
	    
	    private admin admin;
	    
	    @FXML
	    private Text register_but;

	   public class coonn implements  Runnable{
	   	@Override
	   	public void run()
		{

		}
	   }
	    
	    @FXML
	    public void initialize() {
	    	
	    	register_but.setOnMousePressed(event->{
	    		
	    		
	    	});
	    	
	        pane.setStyle("-fx-background-color: #ffcccc");
	        sub_button.setOnMouseClicked(new EventHandler<MouseEvent>(){

	            @Override
	            public void handle(MouseEvent event) {
					Platform.runLater(()->{
	            	sub_button.setDisable(true);
	            	pass_field.setDisable(true);

	    	    	try {

	    	    	adminDashbordCont con = new adminDashbordCont();
						JsonWriterAndReader jsw = new JsonWriterAndReader();

	    	    			SceneChanger sc = new SceneChanger();
							CrypterDecrypter cr = new CrypterDecrypter();
	    	    			try {
								JSONArray arr = new JSONArray();
									arr.add(	jsw.createHeader("check Password",User_field.getText(), pass_field.getText()));
								TCPClient cons = new TCPClient();
								cons.send(arr, User_field.getText());
								JSONArray adm = cons.rec();

								if (((String)((JSONObject)((JSONObject)adm.get(0)).get("res")).get("res")).equals("Accepted"))
								{
									admin = jsw.getArraylistOfAdmin(new ArrayList<admin>(),(JSONArray) adm.get(1)).get(0);
									if (admin != null) {
										cons.loged = true;
										admin.setImage(new File("./images/admins/" + ((JSONObject) ((JSONObject) ((JSONArray) adm.get(1)).get(0)).get("stf")).get("imgName")));


										Pinger pin = new Pinger(admin, (Stage) ((Node) event.getSource()).getScene().getWindow(), cons);
										java.util.Timer timer = new Timer();
										timer.schedule(pin, 0, 5000);
										sc.changeScenes(event, "/view/adminDashbord.fxml", "Drag here", null, con, admin);
									}
									else
										throw new IllegalArgumentException("incorrect password or Username");
								}
								else
									throw new IllegalArgumentException("incorrect password or Username");

	    	    			} catch (IOException e) {
	    					// TODO Auto-generated catch block
	    	    				error_msg.setText(e.getMessage());
	    	    				e.printStackTrace();
	    				}



	    	    		sub_button.setDisable(false);
	    	    		pass_field.setDisable(false);
	    	    		pass_field.setText("");

	    	    	}
	            catch ( Exception ex)
	            {
	            	error_msg.setText(ex.getMessage());
	            	ex.printStackTrace();
					sub_button.setDisable(false);
					pass_field.setDisable(false);
	            }
	            });}});
	        
	        pass_field.setOnKeyPressed( event ->
	        {
	        	if(event.getCode().equals(KeyCode.ENTER))
	        	{
					Platform.runLater(()->{
						sub_button.setDisable(true);
						pass_field.setDisable(true);

						try {

							adminDashbordCont con = new adminDashbordCont();
							JsonWriterAndReader jsw = new JsonWriterAndReader();

							SceneChanger sc = new SceneChanger();
							CrypterDecrypter cr = new CrypterDecrypter();
							try {
								JSONArray arr = new JSONArray();
								arr.add(	jsw.createHeader("check Password",User_field.getText(), pass_field.getText()));
								TCPClient cons = new TCPClient();
								cons.send(arr, User_field.getText());
								JSONArray adm = cons.rec();

								if (((String)((JSONObject)((JSONObject)adm.get(0)).get("res")).get("res")).equals("Accepted"))
								{
									admin = jsw.getArraylistOfAdmin(new ArrayList<admin>(),(JSONArray) adm.get(1)).get(0);
									if (admin != null) {
										cons.loged = true;
										admin.setImage(new File("./images/admins/" + ((JSONObject) ((JSONObject) ((JSONArray) adm.get(1)).get(0)).get("stf")).get("imgName")));


										Pinger pin = new Pinger(admin, (Stage) ((Node) event.getSource()).getScene().getWindow(), cons);
										java.util.Timer timer = new Timer();
										timer.schedule(pin, 0, 5000);
										sc.changeScenes(event, "/view/adminDashbord.fxml", "Drag here", null, con, admin);
									}
									else
										throw new IllegalArgumentException("incorrect password or Username");
								}
								else
									throw new IllegalArgumentException("incorrect password or Username");

							} catch (IOException e) {
								// TODO Auto-generated catch block
								error_msg.setText(e.getMessage());
								e.printStackTrace();
							}



							sub_button.setDisable(false);
							pass_field.setDisable(false);
							pass_field.setText("");

						}
						catch ( Exception ex)
						{
							error_msg.setText(ex.getMessage());
							ex.printStackTrace();
							sub_button.setDisable(false);
							pass_field.setDisable(false);
						}
					});
	        	}
	        });
	    
	    }
}
