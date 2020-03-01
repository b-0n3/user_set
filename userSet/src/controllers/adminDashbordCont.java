package controllers;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;

import javafx.scene.Node;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import models.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class adminDashbordCont implements ControllerClass
{
	@FXML
    private Circle image;

    @FXML
    private Text fullName;

    @FXML
    private Text userName;

    @FXML
    private Text phoneNumber;

    @FXML
    private Text Post;

    @FXML
    private Pane Drag_field;

    @FXML
    private Text textTo;

    @FXML
    private Button addsinglstaff;

    @FXML
    private Button dellStaff;
	@FXML
	private Button addAdmin;

	@FXML
	private Button logout;

    @FXML
    private Button findStaff;
    private File excelFile;
    
    private ArrayList<Staff> staffs;
    private boolean   isNotFound = false;
    private boolean   isnot = false;
    private  admin realadmin;
    @FXML
    public void initialize() throws FileNotFoundException 
    {
		dellStaff.setOnAction(event -> {
			Deletestaff df = new Deletestaff();
			SceneChanger sc = new SceneChanger();
			try {
				sc.changeScenes(event , "/view/deletestaff.fxml","delete Staffs" , null, df, realadmin);
			}catch (Exception e)
			{e.printStackTrace();}

		});
addAdmin.setOnAction(event->{
	addAdmin_cont con = new addAdmin_cont();
	SceneChanger sc = new SceneChanger();
	try {
		sc.changeScenes(event, "/view/addAdmin.fxml", "add Admin", null, con, realadmin);
	}catch (Exception e)
	{
		e.printStackTrace();
	}
});
logout.setOnAction(event -> {
	Main mn = new Main();
	mn.stop();
});


    	findStaff.setOnAction(event ->{
        Search_cont con = new Search_cont();
        SceneChanger sc = new SceneChanger();
        try {
            sc.changeScenes(event, "/view/Search.fxml", "search", null, con, realadmin);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    });
    	Drag_field.setOnDragOver(new EventHandler<DragEvent>() {

      		 @Override
   	            public void handle(DragEvent event) {
      			
   	                if (event.getGestureSource() != Drag_field
   	                        && event.getDragboard().hasFiles()) {
   	            
   	                	
   	                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
   	                  
   	                    
   	                }
   	                event.consume();
   	            }
          });
       	
       	Drag_field.setOnDragEntered(new EventHandler<DragEvent>() {
       		
      		 @Override
   	            public void handle(DragEvent event) {
      			
      			   
      		    	event.consume();
   	            }
      		 
      	});
       	
       	Drag_field.setOnDragExited(new EventHandler<DragEvent>() {
       		
      		 @Override
   	            public void handle(DragEvent event) {




      		    	event.consume();
   	               
   	            }
      	});
       	
       	
       	Drag_field.setOnDragDropped(new EventHandler<DragEvent>() {


       		FileInputStream in = null;
   			@Override
   			public void handle(DragEvent event) {
   				
   				Dragboard db = event.getDragboard();
                   boolean success = false;
                   
                   if (db.hasFiles()) {
                	   
                   	Workbook bc = new XSSFWorkbook();
                   	
                   	try {
                   		
                   	if(db.getFiles().get(0).getName().matches("[a-z A-Z () 0-9]+\\.xlsx"))
                   	{
                        setExcelFile(new File(db.getFiles().get(0).getPath()));
                        MyExcelReader my = null;
         	    		
                        staffs = new ArrayList<Staff>();
        	    		
        	    		try {
        					my = new  MyExcelReader(excelFile);
        				} catch (IOException e) {
        					// TODO Auto-generated catch block
        					e.printStackTrace();
        				}
        	    		staffs = my.ReadAndGetList();
        		    	add_users_cont con = new add_users_cont();
        		    	SceneChanger sc = new SceneChanger();
        		    	try {
        					sc.changeScenes(event, "/view/add_users.fxml", "Filter", staffs, con, realadmin);
        				} catch (IOException e) {
        					// TODO Auto-generated catch block
        					e.printStackTrace();
        				}
                   	}
                        else
                        {
                       	 
    	    		    	isNotFound = true;
    	    		    	
                       	 throw new IllegalArgumentException("invalid File");
                        }
                   	}catch (Exception e)
                   	{
                   		
                   		
                   	}
                   	
                   	event.consume();
                   	
   				
   			}}
       		
       	});
       	
    }
	public File getExcelFile() {
		return excelFile;
	}
	public void setExcelFile(File excelFile) {
		this.excelFile = excelFile;
	}

	@Override
	public void preloadData(ArrayList<Staff> staff, admin admin) {
		this.realadmin = admin;

		fullName.setText(realadmin.getFirstname() + realadmin.getLastName());
		phoneNumber.setText(realadmin.getPhoneNumber());
		Post.setText(realadmin.getPost());
		userName.setText(realadmin.getUsername());

		try {
			Image image = new Image(new FileInputStream(realadmin.getImage()));

			this.image.setFill(new ImagePattern(image));
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
	}
}
