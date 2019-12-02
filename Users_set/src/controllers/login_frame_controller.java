package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import Users_set.admin;
public class login_frame_controller {

	
	   @FXML
	    private Pane pane;

	    @FXML
	    private TextField User_field;

	    @FXML
	    private PasswordField pass_field;

	    @FXML
	    private Circle sub_button;

	    @FXML
	    private Text error_msg;
	    
	    private admin admin;

	   
	    
	    @FXML
	    public void initialize() {
	        pane.setStyle("-fx-background-color: #ffcccc");
	        sub_button.setOnMouseClicked(new EventHandler<MouseEvent>() {

	            @Override
	            public void handle(MouseEvent event) {
	            	admin = new admin();
	    	    	admin.setUsername(User_field.getText());
	    	    	admin.setPassword(pass_field.getText());
	    	    	if(admin.passCorrect())
	    	    		error_msg.setText("password correct");
	    	    	else
	    	    		error_msg.setText("incorrect password");
	            }
	        });
	    }
}
