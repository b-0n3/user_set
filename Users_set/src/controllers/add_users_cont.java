package controllers;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import Users_set.ControllerClass;
import excelReader.MyExcelReader;
import excelReader.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;


public class add_users_cont implements ControllerClass {
	

	    @FXML
	    private Button save_button;

	  
	    
	    @FXML
	    private TableView<Staff> tableView;
	  
	    @FXML
	    private Circle Staff_image;
	    
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
	    		System.out.println("this is the image = " + tableView.getSelectionModel().getSelectedItem().getImage().getName() +"this is the bp   " +tableView.getSelectionModel().getSelectedItem().getBirth_place());
	    	});
	    	
	    	
	    		
	    		
	    		
				
	 
	    	
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

