package controllers;

import com.gluonhq.charm.glisten.control.ProgressIndicator;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Staff;
import models.admin;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Deletestaff {
    private Stage stage;

    @FXML
    private Button goBack;

    @FXML
    private TextField SearchField;

    @FXML
    private ProgressIndicator prog;

    @FXML
    private Button search;

    @FXML
    private VBox staffBox;

    @FXML
    private VBox BlackList;
    private ArrayList<Staff> staffs = new ArrayList<>();

    private  ArrayList<Staff> toDelete= new ArrayList<>();
     private admin realAdmin;


     /**
      * this class represent the blacklist list
      *
      **/

    public class Blacklist extends  VBox{



    public Blacklist(){
        
    }

    }

/** each element of the blacklist field will be an instance of this class */
public class blackcart extends VBox{
    private ImageView image;
    private Text       id;
    private Button Undo;
    private Button show;
    private Staff staff;

    public blackcart(Staff stf)
    {
        this.staff = stf;
        image =new ImageView();
        id = new Text();
       Undo = new Button();
        show = new Button();
    }

    public void doFinal()
    {

        this.setSpacing(30.0);
        this.setAlignment(Pos.CENTER);
        image.setFitHeight(200);
        image.setFitWidth(216);
        id.setText(String.valueOf(this.id));

        try {
            javafx.scene.image.Image image = new Image(new FileInputStream(staff.getImage()));

            this.image.setImage(image);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        show.setOnAction(event->{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/container.fxml"));
           try {
               Parent parent = loader.load();

               container cn = new container();
               cn = loader.getController();
               ArrayList<Staff> stf = new ArrayList<>();
               stf.add(this.staff);
               cn.preloadData(stf, realAdmin);
               Scene scene = new Scene(parent);
               stage.setScene(scene);
               stage.show();
               if (cn.isAdded)
               {
                   staffs.remove(this.staff);
                   toDelete.add(this.staff);
               }

           }catch(Exception e){e.printStackTrace();}
        });

        Undo.setOnAction(event->{
            toDelete.remove(this.staff);
            staffs .add(this.staff);

        });
    }

    }

    /** each element of the staff list will be an instance of this class*/

public class staffcart extends VBox{


        private ImageView image;
        private Text       id;
        private Button addToBlackList;
        private Button show;
        private Staff staff;

        public staffcart(Staff stf)
        {
            this.staff = stf;
            image =new ImageView();
            id = new Text();
            addToBlackList = new Button();
            show = new Button();
        }

        public void doFinal()
        {

            this.setSpacing(30.0);
            this.setAlignment(Pos.CENTER);
            image.setFitHeight(200);
            image.setFitWidth(216);
            id.setText(String.valueOf(this.id));

            try {
                javafx.scene.image.Image image = new Image(new FileInputStream(staff.getImage()));

                this.image.setImage(image);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            show.setOnAction(event->{
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/container.fxml"));
                try {
                    Parent parent = loader.load();

                    container cn = new container();
                    cn = loader.getController();
                    ArrayList<Staff> stf = new ArrayList<>();
                    stf.add(this.staff);
                    cn.preloadData(stf, realAdmin);
                    Scene scene = new Scene(parent);
                    stage.setScene(scene);
                    stage.show();
                    if (cn.isAdded)
                    {
                        staffs.remove(this.staff);
                        toDelete.add(this.staff);
                    }

                }catch(Exception e){e.printStackTrace();}
            });

            addToBlackList.setOnAction(event->{
                staffs.remove(this.staff);
                toDelete.add(this.staff);

            });

}

/** this is the class that will hold an array of three staff cards */



public class carHolder extends  VBox{

}




    public class holder {
        private ArrayList<Blacklist> blacklists;
        private ArrayList<carHolder> cartHolders;



        public void doFinal(ArrayList<Blacklist> blacklists,ArrayList<carHolder> cartHolders)
        {
            this.blacklists = blacklists;
            this.cartHolders = cartHolders;
            Platform.runLater(()->{
                staffBox.getChildren().remove(0, staffBox.getChildren().size());
                BlackList.getChildren().remove(0, BlackList.getChildren().size());

                for (carHolder cr :cartHolders)
                staffBox.getChildren().addAll(cr);
                for (Blacklist blc :blacklists)
                    BlackList.getChildren().add(blc);
            });

        }
    }

}
