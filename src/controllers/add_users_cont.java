package controllers;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import org.omg.CORBA.SystemException;

import com.sun.javafx.css.converters.PaintConverter;
import com.sun.prism.paint.Paint;

import models.ControllerClass;
import models.MyExcelReader;
import models.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;


import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;

import javafx.stage.Stage;


public class add_users_cont implements ControllerClass {
	

	 @FXML
	    private Pane to_change_on;

	    @FXML
	    private Text text_to;

	    @FXML
	    private Button ChooseDir;

	    @FXML
	    private Button save_button;
	  
	    
	    @FXML
	    private TableView<Staff> tableView;
	    
	    @FXML
	    private Text error_text;
	  
	    @FXML
	    private Circle Staff_image;
	    private boolean isdone = false;
	    
	    private TableColumn<Staff, String> id = new TableColumn<Staff, String>("Matricule");
	    private TableColumn<Staff, String> birth_day= new TableColumn<Staff, String>("Date de naissance");
	    private TableColumn<Staff, String> birth_place= new TableColumn<Staff, String>("Lieu de naissance");
	    private TableColumn<Staff, Character> sex= new TableColumn<Staff, Character>("Code sex");
	    private TableColumn<Staff, String> cin= new TableColumn<Staff, String>("CIN");
	    private TableColumn<Staff, String>	sit_fam= new TableColumn<Staff, String>("Situation Familiale");
	    private TableColumn<Staff, String>	nationalite= new TableColumn<Staff, String>("Nationalite");
	    private TableColumn<Staff, String>	date_embauche= new TableColumn<Staff, String>("Date d'embauche");
	    private TableColumn<Staff, String>	grade= new TableColumn<Staff, String>("Grade");
	    private TableColumn<Staff, String>	functio_n= new TableColumn<Staff, String>("Function");
	    private TableColumn<Staff, String>	post= new TableColumn<Staff, String>("Post");
	    private TableColumn<Staff, String>categorie= new TableColumn<Staff, String>("Categorie");
	    private TableColumn<Staff, Integer>echelon= new TableColumn<Staff, Integer>("Echelon");
	    private TableColumn<Staff, String> ent_effect= new TableColumn<Staff, String>("Ent effect");
	    private TableColumn<Staff, String>section_analytique= new TableColumn<Staff, String>("Section Analytique");
	    private TableColumn<Staff, String>regime_retraite= new TableColumn<Staff, String>("Regime Retrait");
	    private TableColumn<Staff, Integer>affil_recore= new TableColumn<Staff, Integer>("Affil Recore");
	    private TableColumn<Staff, String>date_der_promo= new TableColumn<Staff, String>("Date dern promo");
	    private ObservableList<Staff> volunteers = FXCollections.observableArrayList();
	    
	    @SuppressWarnings("unused")
		private boolean DirChecker(final File dir) throws FileNotFoundException
	    {
	    	if(dir != null)
	    	{
	    		
	    		File[] dir_contents = dir.listFiles();
	    		if(dir_contents.length > 0)
				{
	    			for ( File file :dir_contents)
	    			{
	    				if(file.getName().matches("\\d*.(jpe?g|png)"))
	    					return(true);
	    			}
				}
				
	    		
	    	}
			return false;
	    	
	    }
	   private  class  waitt extends Thread{
		   
		   @Override
		    public void run()
		   {
			   while (true)
			   {
				   
				   for(int i = 0; i < 10000;i++ )
				   {
					   switch (i)
					   {
					   		case 0:
					   			text_to.setText("Wait.");

					   		break;
					   		case 1000:
					   			text_to.setText("Wait..");
					   			
					   		break;
					   		case 2000:
					   			text_to.setText("Wait...");
					   		
					   		break;
					   		case 3000:
					   			text_to.setText("Wait....");
					   		
					   		break;
					   		case 4000:
					   			text_to.setText("Wait......");
					   		break;
					   		case 5000:
					   			text_to.setText("Wait......");
					   		break;
					   		case 6000:
					   			text_to.setText("Wait........");
					   		break;
					   		case 8000:
					   			text_to.setText("Wait..........");
					   		break;
					   		case 9000:
					   			text_to.setText("Wait.............");
					   		break;
					   		case 9990:
					   			text_to.setText("Wait..................");
					   		break;
					   		
					   		
					   }
				   }
			   }
		   }
	   }
	    
	    @FXML
	    public void initialize() throws FileNotFoundException {
	    	
	    	
	    	
	    	tableView.setOnMouseClicked(event->
	    	{
	    		try {
 	    			Image image = new Image(new FileInputStream(tableView.getSelectionModel().getSelectedItem().getImage()));
 	    			
					Staff_image.setFill(new ImagePattern (image));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		
	    	});
	    	
	    	ChooseDir.setOnAction(event->{
	    		error_text.setText("");
	    	 DirectoryChooser directoryChooser = new DirectoryChooser();
	    		directoryChooser.setTitle("Select Some Directories");
	    		directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
	    		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    		File dir = directoryChooser.showDialog(stage);
	    		try {
	    			try {
						if(DirChecker(dir))
						{
							setImagesFrom_dir(dir);
						}
						else 
							throw new IllegalArgumentException("invalid Folder");
					} 
	    			catch (Exception e) {
						// TODO Auto-generated catch block
						error_text.setText(e.getMessage());
					}
	    		}catch (Exception e) {
						// TODO Auto-generated catch block
						error_text.setText(e.getMessage());
					}
	    		
	    	
	    		
	    	});
	    	
	    	to_change_on.setOnDragEntered(new EventHandler<DragEvent>() {

				@Override
				public void handle(DragEvent event) {
					error_text.setText("");
					to_change_on.setStyle("-fx-background-color:#fffdf9;-fx-background-radius: 20;");
					text_to.setText("Drop Folder here");
					text_to.setFill(new Color(0.85, 0.58, 0.58, 1));
					
				}
	    		
	    	});
	    	to_change_on.setOnDragExited(new EventHandler<DragEvent>() {

				@Override
				public void handle(DragEvent event) {
				
					error_text.setText("");
					to_change_on.setStyle("-fx-background-color:#5eb7b7;-fx-background-radius: 20;");
					if(!isdone)
						text_to.setText("Drag Folder here");
					text_to.setFill(new Color(0, 0, 0, 1));
					
				}
	    		
	    	});
	    	
	    	to_change_on.setOnDragOver(new EventHandler<DragEvent>() {

	      		 @Override
	   	            public void handle(DragEvent event) {
	      			System.out.println("the first one 4");
	   	                if (event.getGestureSource() != to_change_on
	   	                        && event.getDragboard().hasFiles()) {
	   	            
	   	                	
	   	                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
	   	                  
	   	                    
	   	                }
	   	                event.consume();
	   	            }
	          });
	    	
	    	to_change_on.setOnDragDropped(new EventHandler<DragEvent>() {

				
				
				@Override
				public void handle(DragEvent event) {
					
					try {
						Thread.sleep(2);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println("dasdasd");
					error_text.setText("");
					to_change_on.setStyle("-fx-background-color:#396362;-fx-background-radius: 20;");
				
					text_to.setFill(new Color(0.1, 0.74, 1, 1));
					
				
					
				Dragboard db = event.getDragboard();
					try {
					if (db.hasFiles())
					{
						File dir = db.getFiles().get(0);
						
							try {
				    			try {
									if(DirChecker(dir))
									{
										setImagesFrom_dir(dir);
									}
									else 
										throw new IllegalArgumentException("invalid Folder");
								} 
				    			catch (Exception e) {
									// TODO Auto-generated catch block
									error_text.setText(e.getMessage());
								}
				    		}catch (Exception e) {
									// TODO Auto-generated catch block
									error_text.setText(e.getMessage());
								}
							
						}
						else 
							throw new IllegalArgumentException("invalid File ");
					
					}catch (Exception e)
					{
						error_text.setText(e.getMessage());
					}
					
				
					System.gc();
					to_change_on.setStyle("-fx-background-color:#5eb7b7;-fx-background-radius: 20;");
					text_to.setText("well done !!!!!");
					isdone = true;
					text_to.setFill(Color.GOLD);
				}
	    		
				

	    	});
	    	
//	    	save_button.setOnAction(event->{
//	    		 File Image;
//	   		  File dir= new File("./boneImages/");
//	   		 
//	   		  	dir.mkdir();
//	   		  	
//	   		  Iterator<Staff> iter = volunteers.iterator();
// 	   		  
//	   		  while(iter.hasNext())
//	   		  {
//	   			 for(int i = 0; i < 4;i++)
//	   			 {
//	   				 switch (i)
//	   				 {
//		   				 case 0:
//		   				 {
//		   					 Image = new File("./bonedd.jpg");
//		   				//create a new Path to copy the image into a local directory
//		   			        Path sourcePath = Image.toPath();
//		   			        
//		   			       
//		   			        
//		   			        Path targetPath = Paths.get(dir.getPath()+ "/"+iter.next().getId()+".jpg");
//		   			        
//		   			        //copy the file to the new directory
//		   			        try {
//								Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
//							} catch (IOException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//		   					 
//		   					 break;
//		   				 }
//		   				 case 3:
//		   				 {
//		   					 Image = new File("./defaultPerson.png");
//		   				//create a new Path to copy the image into a local directory
//		   			        Path sourcePath = Image.toPath();
//		   			        
//		   			       
//		   			        
//		   			        Path targetPath = Paths.get(dir.getPath()+ "/"+iter.next().getId()+".png");
//		   			        System.out.println(dir.getPath()+iter.next().getId());
//		   			        
//		   			        //copy the file to the new directory
//		   			        try {
//								Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
//							} catch (IOException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//		   					 
//		   					 break;
//		   				 }
//		   				 case 1:
//		   				 {
//		   					 Image = new File("./mDe.png");
//		   				//create a new Path to copy the image into a local directory
//		   			        Path sourcePath = Image.toPath();
//		   			        
//		   			       
//		   			        
//		   			        Path targetPath = Paths.get(dir.getPath()+ "/"+iter.next().getId()+".png");
//		   			        
//		   			        //copy the file to the new directory
//		   			        try {
//								Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
//							} catch (IOException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//		   					 
//		   					 break;
//		   				 }
//		   				 case 2:
//		   				 {
//		   					 Image = new File("./fDe.png");
//		   				//create a new Path to copy the image into a local directory
//		   			        Path sourcePath = Image.toPath();
//		   			        
//		   			       
//		   			        
//		   			        Path targetPath = Paths.get(dir.getPath()+ "/"+iter.next().getId()+".png");
//		   			        
//		   			        //copy the file to the new directory
//		   			        try {
//								Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
//							} catch (IOException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//		   					 
//		   					 break;
//		   				 }
//	   				 
//	   				 }
//	   			 }
//	   		  }
//	   		  
//	   		  
//	    	});
	    		
	    		
				
	 
	    	
	    }
	    	

	    	
	    	
	    	
	    public void copyImageFile(File Image) throws IOException
	    {
	    	
	        //create a new Path to copy the image into a local directory
	        Path sourcePath = Image.toPath();
	        
	        
	        
	        Path targetPath = Paths.get("./boneImages/" + Image.getName());
	        
	        //copy the file to the new directory
	        Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
	        
	        
	    }   	
	    	
	    	
	    
	 
	  private void setImagesFrom_dir(File dir) throws IOException 
	  {
			Hashtable<Integer ,File > hash = new Hashtable<Integer, File>();
			MyExcelReader my = new MyExcelReader();
			File[] files = dir.listFiles();
			
			File dirs = new File("./boneImages/");
	   		dirs.mkdir();
	   		
	   		for (File file :files)
			{
				copyImageFile(file);
				
			}
	   		
	   		files = null;
	   		System.gc();
	   		File[] ima = dirs.listFiles();
	   		
	   		for (File file : ima)
			{
			
				hash.put(my.atoi(file.getName()), file);
			}
	   		
			for(Staff staff : volunteers) {
				
				if(hash.get(staff.getId()) != null)
				{
					staff.setImage(hash.get(staff.getId()));
				}
			}
		}

	public ArrayList<Staff>  SetImagesPath(ArrayList <Staff> staffs)
	  {
		  
	    	if (staffs != null)
	    	{
	    		for (Staff staff: staffs)
	    		{
	    			if (staff.getSex() == 'M')
	    			{
	    				staff.setImage(new File ("./mDe.png"));
	    			}
	    			else if ( staff.getSex() == 'F')
	    			{
	    				staff.setImage(new File ("./fDe.png"));
	    			}
	    		}
	    		return staffs;
	    	}
	    	return null;
	    }

		
		@Override
		public void preloadData(ArrayList<Staff> staff) {
			Iterator<Staff> cellIterator = staff.iterator();
	    		staff = SetImagesPath(staff);
	    		while (cellIterator.hasNext())
	    		{
	    			Staff stafff = cellIterator.next();
	    			System.out.println(stafff);
	    			volunteers.add(stafff);
	    		}
	    		
	    		tableView.setDisable(false);
	    		tableView.setEditable(true);
	    		
	    		id.setCellValueFactory( new PropertyValueFactory<Staff, String>("id"));
	    		birth_day.setCellValueFactory(new PropertyValueFactory<Staff, String>("birth_day"));
	    		birth_place.setCellValueFactory(new PropertyValueFactory<Staff, String>("birth_place"));
	    		sex.setCellValueFactory(new PropertyValueFactory<Staff, Character>("sex"));
	    		cin.setCellValueFactory(new PropertyValueFactory<Staff, String>("cin"));
	    		sit_fam.setCellValueFactory(new PropertyValueFactory<Staff, String>("sit_fam"));
	    		nationalite.setCellValueFactory(new PropertyValueFactory<Staff, String>("nationalite"));
	    		date_embauche.setCellValueFactory(new PropertyValueFactory<Staff,String>("date_embauche"));
	    		grade.setCellValueFactory(new PropertyValueFactory<Staff, String>("grade"));
	    		functio_n.setCellValueFactory(new PropertyValueFactory<Staff,String>("functio_n"));
	    		post.setCellValueFactory(new PropertyValueFactory<Staff, String>("post"));
	    		categorie.setCellValueFactory(new PropertyValueFactory<Staff, String>("categorie"));
	    		echelon.setCellValueFactory(new PropertyValueFactory<Staff, Integer>("echelon"));
	    		ent_effect.setCellValueFactory(new PropertyValueFactory<Staff, String>("ent_effect"));
	    		section_analytique.setCellValueFactory(new PropertyValueFactory<Staff, String>("section_analytique"));
	    		regime_retraite.setCellValueFactory(new  PropertyValueFactory<Staff, String>("regime_retraite"));
	    		affil_recore.setCellValueFactory(new PropertyValueFactory<Staff, Integer>("affil_recore"));
	    		date_der_promo.setCellValueFactory(new PropertyValueFactory<Staff, String>("date_der_promo"));
	    		tableView.getColumns().addAll(id, birth_day, birth_place,sex,cin,sit_fam,nationalite
	    				,date_embauche,grade,functio_n,post, categorie,echelon, ent_effect, section_analytique,
	    				regime_retraite,affil_recore,date_der_promo);
	    		
	    		tableView.getItems().addAll(volunteers);
	    		Staff_image.setOpacity(100.0);
	    		Staff_image.setStroke(Color.SEAGREEN); 
 	    		Staff_image.setFill(Color.SNOW);
 	    		Staff_image.setEffect(new DropShadow(+50d, 0d, +5d, Color.DARKSEAGREEN));
 	    	
 	    		try {
 	    			Image image = new Image(new FileInputStream(staff.get(1).getImage()));
 	    			
					Staff_image.setFill(new ImagePattern (image));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
}

