package models;
	
import java.io.File;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import org.apache.poi.ss.formula.functions.T;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {

			Parent root= FXMLLoader.load(getClass().getResource("/view/addAdmin.fxml"));
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
		TCPClient con = new TCPClient();
		if(con.loged){
     File dir = new File ("./boneImages");
		File[] Imagesad;
		File[] Imagesstf;
     if ( dir != null) {
		 File[] Images = dir.listFiles();

		 deleteImages(Images);
	 }
		 File dirf = new File ("./images/admins");
		 if (dirf !=null) {
			 Imagesad = dirf.listFiles();
			 deleteImages(Imagesad);
		 }
		 File dirstf = new File ("./images/staffs");
		 if (dirstf != null) {
			 Imagesstf = dirstf.listFiles();
			 deleteImages(Imagesstf);
		 }
		 dir.delete();
try{

	JSONObject obj = new JsonWriterAndReader().createHeader("log out");
	JSONArray arr = new JSONArray();
	arr.add(obj);
	con.send(arr ,con.getUu());
}catch(Exception e){e.printStackTrace();
}}


try {


			System.exit(0);
		}catch (Exception e)
		{

		}

	}
	public static void main(String[] args) {
		launch(args);
		
	}

	public void deleteImages(File Images[])
	{
		if (Images != null)
			for (File image : Images)
			{
				image.delete();
			}

	}

}

