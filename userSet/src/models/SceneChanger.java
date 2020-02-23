package models;

import java.io.IOException;
import java.util.ArrayList;

import models.Staff;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneChanger {

		public void changeScenes(Event event, String viewName, String title) throws IOException
	    {
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource(viewName));
	        Parent parent = loader.load();
	        
	        Scene scene = new Scene(parent);
	        
	        //get the stage from the event that was passed in
	        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	        
	        stage.setTitle(title);
	        stage.setScene(scene);
	        stage.show();
	    
	}
		
		public void changeScenes(Event event, String viewName, String title, ArrayList<Staff> staffs, ControllerClass controllerClass, admin admin) throws IOException
	    {
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource(viewName));
	        Parent parent = loader.load();
	        
	        Scene scene = new Scene(parent);
	        
	        //access the controller class and preloaded the volunteer data
	        controllerClass = loader.getController();
	        controllerClass.preloadData(staffs, admin);
	        
	        //get the stage from the event that was passed in
	        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	        
	        stage.setTitle(title);
	        stage.setScene(scene);
	        stage.show();
	    }
}
