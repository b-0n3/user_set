package controllers;

import java.io.IOException;

import models.SceneChanger;
import models.admin;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class bteweencont {
	 @FXML
	    private TextField userField;

	    @FXML
	    private PasswordField passField;

	    @FXML
	    private Text errtext;

	    @FXML
	    private Button login;
	   
	    @FXML
	    public void initialize() {
	    	
	    	
	    	
	        
	    	login.setOnMouseClicked(new EventHandler<MouseEvent>() {

	            @Override
	            public void handle(MouseEvent event) {
	            	
	            	login.setDisable(true);
	            	passField.setDisable(true);
	            	admin admin = new admin();
	    	    	try {
	            	admin.setUsername(userField.getText());
	    	    	admin.setPassword(passField.getText());
	    	    		if(admin.passCorrect())
	    	    		{
	    	    			drag_scene_cont con = new drag_scene_cont();
	    	    			SceneChanger sc = new SceneChanger();
	    	    			errtext.setText("i'm here");
	    	    			try {
	    	    				sc.changeScenes(event, "/frames/drag_scene.fxml", "Drag here");
	    	    			} catch (IOException e) {
	    					// TODO Auto-generated catch block
	    	    				errtext.setText(e.getMessage());
	    	    				e.printStackTrace();
	    				}
	    	    	}
	    	    		else{
	    	    			errtext.setText("incorrect password");
	    	    		login.setDisable(false);
	    	    		passField.setDisable(false);
	    	    		passField.setText("");
	    	    	}
	    	    	}
	            catch ( Exception ex)
	            {
	            	errtext.setText(ex.getMessage());
	            	ex.printStackTrace();
	            }
	            }});
	        
	    	passField.setOnKeyPressed( event ->
	        {
	        	if(event.getCode().equals(KeyCode.ENTER))
	        	{
	        		login.setDisable(true);
	        		passField.setDisable(true);
	            	admin admin = new admin();
	    	    	try {
	            	admin.setUsername(userField.getText());
	    	    	admin.setPassword(passField.getText());
	    	    		if(admin.passCorrect())
	    	    		{
	    	    			drag_scene_cont con = new drag_scene_cont();
	    	    			SceneChanger sc = new SceneChanger();
	    	    			errtext.setText("i'm here");
	    	    			try {
	    	    				sc.changeScenes(event, "/frames/drag_scene.fxml", "Drag here");
	    	    			} catch (IOException e) {
	    					// TODO Auto-generated catch block
	    	    				errtext.setText(e.getMessage());
	    	    				e.printStackTrace();
	    				}
	    	    	}
	    	    		else{
	    	    			errtext.setText("incorrect password");
	    	    		login.setDisable(false);
	    	    		passField.setDisable(false);
	    	    		passField.setText("");
	    	    	}
	    	    	}
	            catch ( Exception ex)
	            {
	            	errtext.setText(ex.getMessage());
	            	ex.printStackTrace();
	            }
	        	}
	        });
	    
	    }
}
