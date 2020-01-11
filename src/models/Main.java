package models;
	
import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root= FXMLLoader.load(getClass().getResource("/view/login_frame.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Override
    public void stop() {
     File dir = new File ("./boneImages");
     File[] Images = dir.listFiles();
     
     for (File image : Images)
     {
    	 image.delete();
     }
      dir.delete();
    }
	
	public static void main(String[] args) {
		launch(args);
		
	}
}
