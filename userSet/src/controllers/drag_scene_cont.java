package controllers;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import models.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class drag_scene_cont implements ControllerClass{

    @FXML
    private Pane pane;

    @FXML
    private ImageView Drag_field;

    @FXML
    private Button ret_button;

    @FXML
    private Text err_text;
    private File excelFile;
    
    private ArrayList<Staff> staffs;
    private boolean   isNotFound = false;
    private boolean   isnot = false;

    @FXML
    public void initialize() throws FileNotFoundException {
    	
    	Drag_field.setImage(new Image(new FileInputStream(new File ("./drophere.png"))));
    	ret_button.setDisable(true);
    	ret_button.setOpacity(0.0);
    	
    	ret_button.setOnAction(event->{
    		FileInputStream in = null;
    		File fl = new File ("./drophere.png");
		    	try {
					in = new FileInputStream(fl);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
		    	Drag_field.setImage(new Image(in));
		    	pane.setStyle("-fx-background-color:#f4eec7; -fx-background-radius : 20.0");
		    	Drag_field.setLayoutX(10);
		    	Drag_field.setLayoutY(25);
		    	err_text.setText("");
		    	ret_button.setDisable(true);
		    	ret_button.setOpacity(0.0);
		    	isNotFound = false;
    	});
    	
    	Drag_field.setOnDragOver(new EventHandler<DragEvent>() {

   		 @Override
	            public void handle(DragEvent event) {
   			System.out.println("the first one 4");
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
   			
   			FileInputStream in = null;
   			 	File fl = new File ("./OnDrag.png");
   		    	try {
   		    		in = new FileInputStream(fl);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
   		    	Drag_field.setImage(new Image(in));
	                    
   		    	event.consume();
	            }
   		 
   	});
    	
    	Drag_field.setOnDragExited(new EventHandler<DragEvent>() {
    		
   		 @Override
	            public void handle(DragEvent event) {
   			FileInputStream in = null;
   			
   			 	
	              if(isNotFound == false){
	            	 
   			 	File fl = new File ("./drophere.png");
   		    	try {
						in = new FileInputStream(fl);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
   		    	Drag_field.setImage(new Image(in));
	              
	              }
	              else {
	            	  File fl = new File ("./invalidFile.png");
						try {
							in = new FileInputStream(fl);
						} catch (FileNotFoundException ex) {
							// TODO Auto-generated catch block
							ex.printStackTrace();
							err_text.setText(ex.getMessage());
						} 
	            	  Drag_field.setImage(new Image(in));
	            	  
	              }
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
     					sc.changeScenes(event, "/view/add_users.fxml", "Filter", staffs, con, null);
     				} catch (IOException e) {
     					// TODO Auto-generated catch block
     					e.printStackTrace();
     				}
                	}
                     else
                     {
                    	 File fl = new File ("./drophere.png");
 	    		    	try {
 							in = new FileInputStream(fl);
 						} catch (FileNotFoundException e) {
 							// TODO Auto-generated catch block
 							err_text.setText(e.getMessage());
 						} 
 	    		    	isNotFound = true;
 	    		    	Drag_field.setImage(new Image(in));
                    	 throw new IllegalArgumentException("invalid File");
                     }
                	}catch (Exception e)
                	{
                		err_text.setOpacity(100.0);
                		err_text.setDisable(false);
                		err_text.setText(e.getMessage());
                		ret_button.setDisable(false);
                		ret_button.setOpacity(100.0);
                		
                		pane.setStyle("-fx-background-color:#eb4d55; -fx-background-radius : 20.0");
						
						e.printStackTrace();
						Drag_field.setImage(new Image(in));
						Drag_field.setLayoutX(10.0);
						Drag_field.setLayoutY(45.0);
						
						ret_button.setText("Retry");
						ret_button.setStyle("-fx-background-color:#f8b400; -fx-background-radius : 7.0");
						return ;
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
			// TODO Auto-generated method stub
			
		}
}
