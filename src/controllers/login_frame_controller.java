package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.awt.event.KeyEvent;
import java.io.IOException;

import models.SceneChanger;
import models.admin;
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

	   
	    
	    @FXML
	    public void initialize() {
	    	
	    	register_but.setOnMousePressed(event->{
	    		
	    		
	    	});
	    	
	        pane.setStyle("-fx-background-color: #ffcccc");
	        sub_button.setOnMouseClicked(new EventHandler<MouseEvent>() {

	            @Override
	            public void handle(MouseEvent event) {
	            	
	            	sub_button.setDisable(true);
	            	pass_field.setDisable(true);
	            	admin = new admin();
	    	    	try {
	            	admin.setUsername(User_field.getText());
	    	    	admin.setPassword(pass_field.getText());
	    	    		if(admin.passCorrect())
	    	    		{
	    	    			drag_scene_cont con = new drag_scene_cont();
	    	    			SceneChanger sc = new SceneChanger();
	    	    			error_msg.setText("i'm here");
	    	    			try {
	    	    				sc.changeScenes(event, "/view/drag_scene.fxml", "Drag here");
	    	    			} catch (IOException e) {
	    					// TODO Auto-generated catch block
	    	    				error_msg.setText(e.getMessage());
	    	    				e.printStackTrace();
	    				}
	    	    	}
	    	    		else{
	    	    		error_msg.setText("incorrect password");
	    	    		sub_button.setDisable(false);
	    	    		pass_field.setDisable(false);
	    	    		pass_field.setText("");
	    	    	}
	    	    	}
	            catch ( Exception ex)
	            {
	            	error_msg.setText(ex.getMessage());
	            	ex.printStackTrace();
	            }
	            }});
	        
	        pass_field.setOnKeyPressed( event ->
	        {
	        	if(event.getCode().equals(KeyCode.ENTER))
	        	{
	        		sub_button.setDisable(true);
	        		pass_field.setDisable(true);
	            	admin = new admin();
	    	    	try {
	            	admin.setUsername(User_field.getText());
	    	    	admin.setPassword(pass_field.getText());
	    	    		if(admin.passCorrect())
	    	    		{
	    	    			drag_scene_cont con = new drag_scene_cont();
	    	    			SceneChanger sc = new SceneChanger();
	    	    			error_msg.setText("i'm here");
	    	    			try {
	    	    				sc.changeScenes(event, "/view/drag_scene.fxml", "Drag here");
	    	    			} catch (IOException e) {
	    					// TODO Auto-generated catch block
	    	    				error_msg.setText(e.getMessage());
	    	    				e.printStackTrace();
	    				}
	    	    	}
	    	    		else{
	    	    		error_msg.setText("incorrect password");
	    	    		sub_button.setDisable(false);
	    	    		pass_field.setDisable(false);
	    	    		pass_field.setText("");
	    	    	}
	    	    	}
	            catch ( Exception ex)
	            {
	            	error_msg.setText(ex.getMessage());
	            	ex.printStackTrace();
	            }
	        	}
	        });
	    
	    }
}
